/*Version 0.0.0.1*/
package Project_java_l3_IMI_2.demo.fr.umlv.zen5.demo;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Project_java_l3_IMI.Graphe;
import Project_java_l3_IMI.Noeud;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
public class Demo {
	 
    static class Area {
	    private Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, 0, 0);
	   
	    
	    void draw(ApplicationContext context,ArrayList<Noeud> path, float xx, float yy) {
	      context.renderFrame(graphics  -> {
	        graphics.setColor(Color.ORANGE);
	        graphics.fill(ellipse);
	        graphics.setColor(Color.MAGENTA);
	        if(path!=null) {
	        	
	        	System.out.println("a:"+path);
	        	
	        	for (Noeud noeud : path) {
	        	
	        	 ellipse = new Ellipse2D.Float(noeud.getY()*20,noeud.getX()*20, 20, 20);
	             graphics.fill(ellipse);
				
	        	}
	        }
	        System.out.println("a:azertyuio:"+xx+","+yy);
	        	graphics.setColor(Color.RED);
	        	ellipse = new Ellipse2D.Float(yy*20,xx*20 , 20, 20);
	        graphics.fill(ellipse);
	        
	      });
	    }
	  }
  static class Area2 {
	    private Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, 0, 0);
	   
	    void draw(ApplicationContext context, Noeud [][] g) {
	      context.renderFrame(graphics  -> {
	        graphics.setColor(Color.ORANGE);
	        graphics.fill(ellipse);
	        
	        for (int i=0;i< g.length;i++) {
	        	graphics.setColor(Color.RED);
				graphics.drawLine( 0,i*20, g[0].length*20,i*20);
				
				for (int j=0;j<g[0].length;j++) {
					if(i==0) {graphics.drawLine(j*20, 0, j*20,g.length*20);}
					switch (g[i][j].getNom()) {
					case 'W':graphics.setColor(Color.black);
					ellipse = new Ellipse2D.Float(j*20 , i*20 , 20, 20);
					break;
					case 'T':graphics.setColor(Color.BLUE);
					ellipse = new Ellipse2D.Float(j*20 , i*20 , 20, 20);
					break;
					case 'G':graphics.setColor(Color.CYAN);
					ellipse = new Ellipse2D.Float(j*20 , i*20 , 20, 20);	
					break;
					
					
					}
					graphics.fill(ellipse);	
					
				}
						}
	        
	    	 
	      });
	    }
	  }
	  
  public static void main(String[] args) {
	    
		
    Application.run(Color.ORANGE, context -> {
    	int x=0;
		int y=0;
    	int shifting=0;
      // get the size of the screen
    	
      ScreenInfo screenInfo = context.getScreenInfo();
      float width = screenInfo.getWidth();
      float height = screenInfo.getHeight();
      System.out.println("size of the screen (" + width + " x " + height + ")");
      
      context.renderFrame(graphics -> {
        graphics.setColor(Color.ORANGE);
        //graphics.drawLine(20, 20, 30, 40);
        graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
        
        
      });
      
      Area area = new Area();
      Area2 area2 = new Area2();
      Graphe g;
	
		g = new Graphe("e");
		area2.draw(context, g.getGraphe());
		
		
		
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
        if(shifting<=2) {
        	area.draw(context,null, ((location.y-30)/20),((location.x-60)/20));}
        
        else {
        	Noeud n1=new Noeud('j',y/20,x/20 );
    		Noeud n2=new Noeud(' ', (int)((location.y-30)/20),(int)((location.x-60)/20));
        	g.Shortest_path(n1,n2);
        	 area.draw(context,n2.getPath(), ((location.y-30)/20),((location.x-60)/20));

        }
        
        x=(int)location.x-60;y=(int)location.y-30;
      }
    });
  }

private static void cop(ApplicationContext context,int width,int height) {
	context.renderFrame(graphics  -> {
		graphics.setColor(Color.RED);
		for(int i=0;i<width;i++) {System.out.println("i=="+i);
			for(int j=0;j<height;j++)
	    graphics.drawLine(i, height,i, height);}
		// TODO Auto-generated method stub
		});	
}


}
