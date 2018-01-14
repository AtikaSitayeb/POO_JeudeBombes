/*Version 0.0.0.1*/
package Project_java_l3_IMI_2.demo.fr.umlv.zen5.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.plaf.SliderUI;

import Project_java_l3_IMI.Graphe;
import Project_java_l3_IMI.Noeud;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
public class Demo {
	 
    static class Area {
    	 void clear(ApplicationContext context,Noeud n,int w,int h) {
    		 context.renderFrame(graphics  -> {
    			 graphics.clearRect(n.getY()*h,n.getX()*w, h, w);	 
			     	 
    		 });
    		 }
	    private Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, 0, 0);
 private static String imageFileNameWall_j = Paths.get(System.getProperty("user.dir"),"Image","followme4.png").toString();
	    
	    private static URL imageSrc;//=((new File(imageFileNameWall_j)).toURI()).toURL();;
	  
	    
	    void draw(ApplicationContext context,ArrayList<Noeud> path, float xx, float yy,int w,int h) {
	      context.renderFrame(graphics  -> {
	        graphics.setColor(Color.GREEN);
	        graphics.fill(ellipse);
	        graphics.setColor(Color.MAGENTA);
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
						System.out.println("atika");
						clear(context,path.get(path.size()-1), h, w);
						graphics.clearRect(noeud.getY()*h,noeud.getX()*w, h, w);	
						System.out.println("atikasdfgh");
					 
					 
				
					// TODO Auto-generated catch block
				
			     
		        	 
	        			
	        	}} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        	 graphics.drawImage(imgW,path.get(path.size()-1).getY()*h,path.get(path.size()-1).getX()*w, h, w, null);
	  		   
	        	
	        }
	       /* System.out.println("a:azertyuio:"+xx+","+yy);
	        	graphics.setColor(Color.RED);
	        	ellipse = new Ellipse2D.Float(yy*h,xx*w ,h, w);
	        graphics.fill(ellipse);*/
	     
	        else  graphics.drawImage(imgW,(int)yy*h,(int)xx*w, h, w, null);
		     
    	 
	        
	      });
	    }
	  }
  static class Area2 {
	  
	    private static String imageFileNameWall = Paths.get(System.getProperty("user.dir"),"Image","icons8-mur-de-briques-50(2).png").toString();//"amazigh.png";
	    private static String imageFileNameTrash = Paths.get(System.getProperty("user.dir"),"Image","icons8-poubelle-50(2).png").toString();//"amazigh.png";
	    private static String imageFileNameGarbage = Paths.get(System.getProperty("user.dir"),"Image","index.jpeg").toString();//"amazigh.png";
	    
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
				     graphics.drawImage(imgW,j*hh  , i*ww, hh, ww, null);  
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
    	int shifting=0;
      // get the size of the screen
    	
      ScreenInfo screenInfo = context.getScreenInfo();
      int width =(int) screenInfo.getWidth();
      int height =(int) screenInfo.getHeight();
      System.out.println("size of the screen (" + width + " x " + height + ")");
      
      context.renderFrame(graphics -> {
        graphics.setColor(Color.GREEN);
        //graphics.drawLine(20, 20, 30, 40);
        graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
        
        
      });
      
      Area area = new Area();
      Area2 area2 = new Area2();
      Graphe g;
	
		g = new Graphe("e");
		
		int w=width/g.getHeight();
		int h=height/g.getWidth();
		area2.draw(context, g.getGraphe(),h,w);
		Noeud n1 = null;//=new Noeud(' ',(int)(location.y/h),(int)(location.x/w));//=new Noeud('j',y/h,x/w );
		Noeud n2;//=new Noeud(' ', (int)(location.y/h),(int)(location.x/w));
    
		
      for(;;) {
        Event event = context.pollOrWaitEvent(10);
        if (event == null) {  // no event
          continue;
        }
        Action action = event.getAction();
        if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
          System.out.println("abort abort !");
          context.exit(0);
          return;
        }
        System.out.println(event);
        
        Point2D.Float location = event.getLocation();
        shifting++;
        System.out.println(shifting);
        
        if(shifting%2 == 0) {
        if(shifting<=2) {
        	area.draw(context,null, location.y/h,location.x/w,h,w);
        //Noeud 
        	 n1=g.getNoeud((int)(location.y/h),(int)(location.x/w));}
		//Noeud n2=new Noeud(' ', (int)(location.y/h),(int)(location.x/w));
    
        else {
        	// n2=new Noeud(' ',(int)(location.y/h),(int)(location.x/w));
		n2=g.getNoeud((int)(location.y/h),(int)(location.x/w));
        		g.Shortest_path(n1,n2);
        	System.out.println(n1+","+n2+"||"+g);
        	 area.draw(context,n2.getPath(), n2.getY(),n2.getX(),h,w);
        	// n1=new Noeud(' ',(int)(location.y/h),(int)(location.x/w));
        	 n1=g.getNoeud((int)(location.y/h),(int)(location.x/w));
        }}
        
		
        //x=(int)location.x/h;y=(int)location.y/w;
      }
    });
  }

}
