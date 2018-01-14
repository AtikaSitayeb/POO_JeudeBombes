package A_Star;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Project_java_l3_IMI.Bomb;
import Project_java_l3_IMI.Floor;
import Project_java_l3_IMI.Garbage;
import Project_java_l3_IMI.Trash;
import Project_java_l3_IMI.Wall;

public class Graphe {
	Noeud [][] graphe;
	
	public Noeud[][] getGraphe() {
		return graphe;
	}
	public Graphe(int leve)  {
		
		ArrayList<String> level;
		try {
			level = levels(leve);
		
		this.graphe=new Noeud[level.size()][level.get(0).length()];
		for(int i=0;i<level.size();i++)
			for(int j=0;j<level.get(0).length();j++) {
				switch(level.get(i).charAt(j)) {
				case 'W':this.graphe[i][j]=new Wall( i, j);break;
				case 'T':this.graphe[i][j]=new Trash( i, j);break;
				case 'G':this.graphe[i][j]=new Garbage( i, j);break;
				case ' ':this.graphe[i][j]=new Floor( i, j);break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}}
	
	public void setBomb(int i,int j,int second){
		this.graphe[i][j]=new Bomb(i, j, second);
	}
	public void	Shortest_path(Noeud n1,Noeud n2) throws Exception{
		if(n1==null) {n2.getPath().add(n2);return;}
		Noeud u;
		Noeud v;
		ArrayList<Noeud> openList2 = new ArrayList<Noeud>();
		ArrayList<Noeud> closedList = new ArrayList<Noeud>();
				ArrayList<Noeud> openList = new ArrayList<Noeud>();
				n1.getPath().clear();
				n1.setHeuristique(Math.abs(n1.getX()-n2.getX())+ Math.abs(n1.getY()-n2.getY()));
				openList.add(n1);
			
				while(openList.iterator().hasNext() ) {
			    	   
			    	   if( openList.iterator().next().getNom() ==' ' ) {
			    		   
			    		   //openList.sort((s1,s2)->(s1.cout-s2.cout));
			    		   openList.sort((s1,s2)->(s1.getHeuristique() - s2.getHeuristique()));
			    		   u=openList.iterator().next();
			    		   openList.remove(0);
			    	  
			    	      if( u.equals(n2)) {
			        	   
				          n2.setPath(u.getPath());
				          n2.getPath().add(n2);
			           return;  
			           }
			    	      openList2.clear();
			           for(int i=0;i<3 ;i++)
			        	   for(int j=0;j<3 ;j++) {
			        		   if( u.getX()-1+i>0 && u.getY()-1+j>0 &&
			        				   u.getX()-1+i<this.graphe.length && u.getY()-1+j<this.graphe[0].length 
			        				   &&  this.graphe[u.getX()-1+i][u.getY()-1+j].getNom()==' ' ){
			        			  			        		  
			        		   v=this.graphe[u.getX()-1+i][u.getY()-1+j];
			        		   if((!openList.contains(v) && !closedList.contains(v)) || (openList.contains(v)&& v.getCout()>u.getCout()+1)) {
			        			    v.setCout(u.getCout() +1); 
			        			    v.getPath().clear();
				                    v.getPath().addAll(u.getPath()) ;
				                    v.getPath().add(u);
				                    v.setHeuristique(v.getCout() + Math.abs(v.getX()-n2.getX())+ Math.abs(v.getY()-n2.getY()));
				                   openList.add(v); 
				                   
			        		   }}
			           closedList.add(u);
			           }}
			    	   }
				System.err.println ("pas de chemin");
				throw new Exception();
			       
	}
	
	@Override
	public String toString() {
		
		String s="\n";
		for (Noeud[] noeuds : this.graphe) {
			for (Noeud noeud : noeuds) {
				s+=noeud.getNom();
			}s+="\n";
			
		}
		System.out.println(this.graphe.length+","+this.graphe[0].length);
		return s;
	}
	
	/**
	 * une méthode qui vérifie si un tableau est correct et  return un tableau du string 
	 * @param leve c'est
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String> levels(int leve) throws Exception  {
		
		ArrayList<String> level=new ArrayList<String>();
		
		Path path = Paths.get(System.getProperty("user.dir"),"Levels","level"+leve+".txt");
	     try 
			(BufferedReader reader = Files.newBufferedReader(path)) {
			  
			  Matcher m;
			  Pattern pcorpus=Pattern.compile(".*(^[W,T].*[W,T]$)");
			  String line=reader.readLine();
			  int sez=line.length();
				
			while 
			(line  != null) { 
				m=pcorpus.matcher(line);
				if(m.matches() && sez==line.length())
				{	 level.add(m.group(1));
					
				}else throw new Exception("fichier incorecte");  	 
			line=reader.readLine();}
			reader.close();
			  Pattern pcorpus2=Pattern.compile("[W,T]+");
			  m=pcorpus2.matcher(level.get(0));
			if(!((m=pcorpus2.matcher(level.get(level.size()-1))).matches()&&
					(m=pcorpus2.matcher(level.get(0))).matches()))throw new Exception("fichier incorecte");
	     			
		}
	     			
	     		
	
		return level;
		

	}
	public int  getWidth() {return this.graphe.length;} 
	public int getHeight() {return this.graphe[0].length;}
	public Noeud getNoeud(int i,int j ){return this.graphe[i][j];}
	public boolean NotGrabage() {
		for(int i=0;i<graphe.length;i++)
			for (Noeud noeuds : graphe[i]) if(noeuds.getNom()=='G')return true;
				
		return false;
	}	
	
}
