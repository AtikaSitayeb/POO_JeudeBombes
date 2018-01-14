package Project_java_l3_IMI;


import java.nio.file.Paths;

import A_Star.Graphe;
import Graphic_Interface.Interface;

public class Games {
	
	
	public static void main(String[] args) {
		int level=0;
		Graphe graphe;
		//Interface interfac;
		int max=Paths.get(System.getProperty("user.dir"),"Levels").toFile().list().length;
		System.out.println(max +" levels");
		while (level<max) {
			graphe=new Graphe(level);
			new Interface(graphe);
			if(graphe.NotGrabage())level++;
		}
		
		

	}

}
