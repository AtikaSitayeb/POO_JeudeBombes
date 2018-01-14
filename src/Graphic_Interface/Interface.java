package Graphic_Interface;


/**
 * @author atika
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
//import java.awt.geom.Rectangle2D;
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

public class Interface {
	//private static int x=0;
	//private static int y=0;
	//Wall_J Wj; 
    private static int s;
	private static boolean bombe=false;
	/**
	  *une methode qui 
	  * @param
	  */
	
	private void clear(ApplicationContext context,Noeud n,int h,int w) {
		 
		 context.renderFrame(graphics  -> {
			 graphics.clearRect(n.getY()*h,n.getX()*w, h, w);
				 
		 });
		 }
	 
	private void path_display(ApplicationContext context,Graphe g,ArrayList<Noeud> path,int w,int h) {
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
	      g.setBomb(path.get(0).getX(), path.get(0).getY(), s);
	      System.out.println(g);}} 
	  
	private void graph_display(ApplicationContext context, Noeud [][] g,int ww,int hh) {
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
						graphics.drawImage(imgW,j*hh  , i*ww, hh+4, ww+5, null);  
					break;
					case 'T':
						graphics.drawImage(imgT,j*hh  , i*ww, hh, ww, null);  
					break;
					case 'G':
						graphics.drawImage(imgG,j*hh  , i*ww, hh, ww, null);  
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
	    	    	graphe.setColor(Color.RED);
	    	    	graphe.setFont(new Font("impact", Font.ROMAN_BASELINE, 11)); 
	    	    	
	    	    	graphe.drawString(s+"",i+(h-20)/2,j+(w+15)/2 );
	    	    	graphe.clearRect(i+(h-20)/2,j+(w+15)/2, 11, 11);
	    	    	graphe.drawString(9+"",i+(h-20)/2,j+(w+15)/2 );
	    	    	
	    		}); 
	    	} catch (IOException e) {
	    		
	    		e.printStackTrace();
	    	}
	         
	    	bombe=false;
	    	s=0;
	    }
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
}
