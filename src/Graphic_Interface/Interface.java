package Graphic_Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import A_Star.Graphe;
import A_Star.Noeud;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;


/**
 * c'est la classe qui affiche le tableau
 * 
 */
public class Interface {
	//private static int x=0;
	//private static int y=0;
	//Wall_J Wj; 
    private int s;
	private boolean bombe=false;
	
	public Interface(Graphe g) {
		 Application.run(Color.GREEN, context -> {
			    
			 ScreenInfo screenInfo = context.getScreenInfo();
		      int width =(int)(screenInfo.getWidth());
		      int height =(int)screenInfo.getHeight();
		      System.out.println("size of the screen (" + width + " x " + height + ")"+context.getScreenInfo());
		      int w=(width-10)/g.getHeight();
			  int h=(height-10)/g.getWidth();
		      
				graph_display(context, g.getGraphe(),h,w);
				Noeud n1 = null;
				Noeud n2 = null ;
		        
				Event event;
				Action action;
				screenInfo = context.getScreenInfo();
			       
			      System.out.println("size of the screen (" + width + " x " + height + ")"+context.getScreenInfo());
			     
		      for(;;) {
		    	  
		    	 event = context.pollOrWaitEvent(10);

		         if (event == null) {  // no event
		          continue;
		        }
		        action = event.getAction();
		   	 Point2D.Float location = event.getLocation();
		        if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
		        	if(event.getKey().toString().equals("UNDEFINED")) {
		        		System.out.println(",," +event.getKey());
		        		bombe=true;s++;
		        	
		        	continue;}
		        	else {
		          System.out.println("abort abort !" +event.getKey());
		          context.exit(0);
		         return;}
		          
		        }
		        if(location.x<w*g.getHeight() && location.y< h*g.getWidth()) { 
		        n2=g.getNoeud((int)((location.y)/h),(int)((location.x)/w));
		      
		        try {
					g.Shortest_path(n1,n2);
					
		       	 path_display(context,g,n2.getPath(),h,w);
		       	n1=n2;
		       	} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Wall-j ne peut pas aller a cette position", "Attention", JOptionPane.WARNING_MESSAGE);

				}}
		          
		      }
		      
		    });
	}
	
	
	/**
	  *une méthode qui supprime un rectangle h x w
	  * @param
	  */
	
	public void clear(ApplicationContext context,Noeud n,int h,int w) {
		 
		 context.renderFrame(graphics  -> {
			 graphics.clearRect(n.getY()*h,n.getX()*w, h, w);
				 
		 });
		 }
	
	 /**
	  * c'est une méthode qui affiche wall fair à mesure et les bombe si on a déposé une
	  * @param g c'est le tableau de jeu
	  * @param path c'est le chemin
	  */
	public void path_display(ApplicationContext context,Graphe g,ArrayList<Noeud> path,int w,int h) {
	    	String imageFileNameWall_j = Paths.get(System.getProperty("user.dir"),"Image","wall_J.png").toString();
		   
	    	context.renderFrame(graphics  -> {
	        graphics.setColor(Color.GREEN);
	        BufferedImage imgW = null ;
	        try {
	        	 
	        imgW = ImageIO.read(((new File(imageFileNameWall_j)).toURI()).toURL());	} 
	        catch (IOException e) {
   	          System.out.println("Image could not be read");
   	          System.exit(1);
   	      }
	        if(path!=null) {
	        	
	        	System.out.println("a:"+path);
	        	try { 
	        	for (Noeud noeud : path) {
	        		graphics.drawImage(imgW,noeud.getY()*h,noeud.getX()*w, h, w, null);
	   			     
						Thread.sleep(100);
						clear(context, noeud, h, w); 
						
	        	}} catch (InterruptedException e) {e.printStackTrace();}
				
	        	 graphics.drawImage(imgW,path.get(path.size()-1).getY()*h,path.get(path.size()-1).getX()*w, h, w, null);
	  		   
	        	
	        }
	      });
	       
	      if(bombe) { display_bomb(context,path.get(0).getY()*h,path.get(0).getX()*w,  h, w);
	      g.setBomb(path.get(0).getX(), path.get(0).getY(), s/2);
	      System.out.println(g);}} 
	 
	/**
	 * c'est une methode qui affiche sur laplication les wall, trash, garbage
	 * @param g c'est le tableau de jeu
	 * @param width d'une case
	 * @param height d'une case
	 */
	private void graph_display(ApplicationContext context, Noeud [][] g,int width,int height) {
	    	String imageFileNameWall = Paths.get(System.getProperty("user.dir"),"Image","wall.png").toString();
		    String imageFileNameTrash = Paths.get(System.getProperty("user.dir"),"Image","trash.png").toString();
		    String imageFileNameGarbage = Paths.get(System.getProperty("user.dir"),"Image","garbage.png").toString();
		    try {
		    BufferedImage imgW = ImageIO.read(((new File(imageFileNameWall)).toURI()).toURL());
		    BufferedImage imgT = ImageIO.read(((new File(imageFileNameTrash)).toURI()).toURL());
		    BufferedImage imgG = ImageIO.read(((new File(imageFileNameGarbage)).toURI()).toURL());
		   
		    context.renderFrame(graphics  -> {
	       
	            
	        for (int i=0;i< g.length;i++) {
	        	
				for (int j=0;j<g[0].length;j++) {
					switch (g[i][j].getNom()) {
					case 'W':
						graphics.drawImage(imgW,j*height  , i*width, height+4, width+5, null);  
					break;
					case 'T':
						graphics.drawImage(imgT,j*height  , i*width, height, width, null);  
					break;
					case 'G':
						graphics.drawImage(imgG,j*height  , i*width, height, width, null);  
					break;
					
					
					}
					
				}
			     
			}
	       
	    	 
	      });
		    } catch (IOException e) {
		          System.out.println("Image could not be read");
		          System.exit(1);
		      }
	    }
	/**
	 * c'est la metode qui affiche les bombe
	 * @param context
	 * @param i
	 * @param j
	 * @param h
	 * @param w
	 */
   
	public void display_bomb(ApplicationContext context, int i, int j, int h, int w) {
	    	// 
	    	 BufferedImage imgW ;
	    	  String imag = Paths.get(System.getProperty("user.dir"),"Image","bomb.png").toString();//"amazigh.png";
	    	  URL imageSrc;
	    	  
	         try {
	    		imageSrc=((new File(imag)).toURI()).toURL();
	    	
	    	 
	    		imgW = ImageIO.read(imageSrc);
	    		context.renderFrame(graphe -> {
	    	    	graphe.drawImage(imgW,i,j, h, w, null); 
	    	    	graphe.setColor(Color.BLACK);
	    	    	graphe.setFont(new Font("impact", Font.LAYOUT_LEFT_TO_RIGHT, 11)); 
	    	    	
	    	    	graphe.drawString(s/2+"",i+(h-15)/2,j+(w+15)/2 );
	    	    	//graphe.clearRect(i+(h-18)/2,j+(w-3)/2, 11, 11);
	    	    	
	    		}); 
	    	} catch (IOException e) {
	    		
	    		e.printStackTrace();
	    	}
	         
	    	bombe=false;
	    	s=0;
	    }

}
