/*Version 0.0.0.1*/
package Project_java_l3_IMI_2.demo.fr.umlv.zen5.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
public class Demo {
	public static int x=0;
	public static int y=0;
	public static int s;
	public static boolean bombe=false;
	 
    static class Area {
    	 void clear(ApplicationContext context,Noeud n,int h,int w) {
    		 context.renderFrame(graphics  -> {
    			 graphics.clearRect(n.getY()*h,n.getX()*w, h, w);
    				 
    		 });
    		 }
	   
 private static String imageFileNameWall_j = Paths.get(System.getProperty("user.dir"),"Image","wall_J.png").toString();
	    
	    private static URL imageSrc;//=((new File(imageFileNameWall_j)).toURI()).toURL();;
	  
	    
	    void draw(ApplicationContext context,ArrayList<Noeud> path, float xx, float yy,int w,int h) {
	      context.renderFrame(graphics  -> {
	        graphics.setColor(Color.GREEN);
	        BufferedImage imgW = null ;
	        try {
	        imageSrc=((new File(imageFileNameWall_j)).toURI()).toURL();
	        imgW = ImageIO.read(imageSrc);	} 
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
	       /* System.out.println("a:azertyuio:"+xx+","+yy);
	        	graphics.setColor(Color.RED);
	        	ellipse = new Ellipse2D.Float(yy*h,xx*w ,h, w);
	        graphics.fill(ellipse);*/
	     
	        else  graphics.drawImage(imgW,(int)yy*h,(int)xx*w, h, w, null);
		     
	        
	      });
	       
	      if(bombe) bomb(context,path.get(0).getY()*h,path.get(0).getX()*w,  h, w); } 
	     
	  }
  static class Area2 {
	  
	    private static String imageFileNameWall = Paths.get(System.getProperty("user.dir"),"Image","wall.png").toString();//"amazigh.png";
	    private static String imageFileNameTrash = Paths.get(System.getProperty("user.dir"),"Image","trash.png").toString();//"amazigh.png";
	    private static String imageFileNameGarbage = Paths.get(System.getProperty("user.dir"),"Image","garbage.png").toString();//"amazigh.png";
	    
	    private static URL imageSrc;
	  
	   
	    void draw(ApplicationContext context, Noeud [][] g,int ww,int hh) {
	      context.renderFrame(graphics  -> {
	       
	        try {
		  	     
		          BufferedImage imgW ;
		          
	        for (int i=0;i< g.length;i++) {
	        	
				for (int j=0;j<g[0].length;j++) {
					switch (g[i][j].getNom()) {
					case 'W':
					
						 imageSrc=((new File(imageFileNameWall)).toURI()).toURL();
				         imgW = ImageIO.read(imageSrc);	
				     graphics.drawImage(imgW,j*hh  , i*ww, hh+4, ww+5, null);  
					break;
					case 'T':
						imageSrc=((new File(imageFileNameTrash)).toURI()).toURL();
				         imgW = ImageIO.read(imageSrc);
				         graphics.drawImage(imgW,j*hh  , i*ww, hh, ww, null);  
					break;
					case 'G':
						imageSrc=((new File(imageFileNameGarbage)).toURI()).toURL();
				         imgW = ImageIO.read(imageSrc);
				         graphics.drawImage(imgW,j*hh  , i*ww, hh, ww, null);  
					break;
					
					
					}
					
				}
			     
						}
	        } catch (IOException e) {
		          System.out.println("Image could not be read");
		          System.exit(1);
		      }
	    	 
	      });
	    }
	  }
	  

public static void main(String[] args) {
	  
    Application.run(Color.GREEN, context -> {
    
    	//int x=0;
		//int y=0;
    	//int shifting=0;
      // get the size of the screen
    	
    	
      ScreenInfo screenInfo = context.getScreenInfo();
      int width =(int)(screenInfo.getWidth());
      int height =(int)screenInfo.getHeight();
      System.out.println("size of the screen (" + width + " x " + height + ")"+context.getScreenInfo());
      
      Graphe g= new Graphe(1);
      
      int w=(width-10)/g.getHeight();
		int h=(height-10)/g.getWidth();
      
      context.renderFrame(graphics -> {
        graphics.setColor(Color.GREEN);
        graphics.fill(new  Rectangle2D.Float(0, 0,width,  height));
        
        
      });
      
      Area area = new Area();
      Area2 area2 = new Area2();
     
		
		
		area2.draw(context, g.getGraphe(),h,w);
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
        System.out.println(event);
        
        //if(location.y-20 < *g.getHeight() && location.x-20< w*g.getWidth()) {
       n2=g.getNoeud((int)((location.y-20)/h),(int)((location.x-45)/w));
       x=n2.getX()*h;
       y=n2.getY()*w;
        try {
			g.Shortest_path(n1,n2);
			System.out.println(n1+","+n2+"||"+g);
       	 area.draw(context,n2.getPath(), n2.getY(),n2.getX(),h,w);
       	n1=n2;
       	 //n1=g.getNoeud((int)(location.y/h),(int)(location.x/w));
        
        } catch (Exception e) {
			System.out.println(n1+","+n2+"||"+g);
			JOptionPane.showMessageDialog(null, "...", "Attention", JOptionPane.WARNING_MESSAGE);

		}//}
        System.out.println(context.getScreenInfo().getWidth()+"size of the screen ("+context.getScreenInfo().getHeight());
          
      }
      
    });
  }



private static void bomb(ApplicationContext context, int i, int j, int h, int w) {
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





}
