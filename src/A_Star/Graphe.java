package Project_java_l3_IMI;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graphe {
	Noeud [][] graphe;
	
	Noeud u;
	Noeud v;
	public Noeud[][] getGraphe() {
		return graphe;
	}
	public Graphe(String p)  {
		
		ArrayList<String> level;
		try {
			level = levels();
		
		this.graphe=new Noeud[level.size()][level.get(0).length()];
		for(int i=0;i<level.size();i++)
			for(int j=0;j<level.get(0).length();j++) {
				this.graphe[i][j]=new Noeud(level.get(i).charAt(j), i, j);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	public Noeud getNoeud(int i,int j ){return this.graphe[i][j];}
	
	public void	Shortest_path(Noeud n1,Noeud n2){
		
		ArrayList<Noeud> closedList = new ArrayList<Noeud>();
				ArrayList<Noeud> openList = new ArrayList<>();
				openList.add(n1);
				
			     
			       
			       while(openList.iterator().hasNext() ) {
			    	   
			    	   if( openList.iterator().next().nom!='W' && 
			        				   openList.iterator().next().nom!='T' && 
			        				   openList.iterator().next().nom!='G') {
			    	  
			    	  openList.sort((s1,s2)->(s1.heuristique-s2.heuristique));   
			    	  u=openList.iterator().next();
			    	  openList.remove(0);
			    	  
			    	  //System.out.println("sdfghj");
			           if( u.equals(n2)) {
			           n2.path=u.path;
			           n2.path.add(n2);
			           reconstituerChemin(u);
			          
			           break;
			           }
			          
			           for(int i=0;i<3 ;i++)
			        	   for(int j=0;j<3 ;j++) {
			        		   if( u.x-1+i>0 && u.y-1+j>0 &&
			        				   u.x-1+i<this.graphe.length && u.y-1+j<this.graphe[0].length 
			        				   &&  this.graphe[u.x-1+i][u.y-1+j].nom!='W' && 
			        				   this.graphe[u.x-1+i][u.y-1+j].nom!='T' && 
			        				   this.graphe[u.x-1+i][u.y-1+j].nom!='G'){
			        			  			        		  
			        		   v=this.graphe[u.x-1+i][u.y-1+j];
			        		   if((!openList.contains(v) && !closedList.contains(v)) ||  v.cout>u.cout+1) {
			        			    v.cout = u.cout +1; 
			        			    v.path.clear();
				                    v.path.addAll(u.path) ;
				                    v.path.add(u);
				                    v.heuristique =v.cout + Math.abs(v.x-n2.x)+ Math.abs(v.y-n2.y);
				                  
				                    openList.add(v); 
				                   
			        		   }}
			        		  
			           closedList.add(u);
			           }}}
			    
			       //terminer le programme (avec erreur)
			      
	
	
	}
	private void reconstituerChemin(Noeud u2) {
		//System.out.println(this.graphe);
		//System.out.println(u2.path);
		for (Noeud noeuds : u2.path) {
			noeuds.nom='P';
		}
		
		
	}
	public void spath(Noeud n1,Noeud n2) {
	
	}
	@Override
	public String toString() {
		//this.graphe[6][33].nom='d';
		//this.graphe[7][15].nom='f';
		String s="\n";
		for (Noeud[] noeuds : this.graphe) {
			for (Noeud noeud : noeuds) {
				s+=noeud.nom;
			}s+="\n";
			
		}
		System.out.println(this.graphe.length+","+this.graphe[0].length);
		return s;
	}
	
	
	public static ArrayList<String> levels() throws Exception  {
		
		ArrayList<String> level=new ArrayList<String>();
		
		Path path = Paths.get(System.getProperty("user.dir"),"Levels","level0.txt");
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
			System.out.println(level);
			 
			  Pattern pcorpus2=Pattern.compile("[W,T]+");
			  m=pcorpus2.matcher(level.get(0));
			if(!((m=pcorpus2.matcher(level.get(level.size()-1))).matches()&&
					(m=pcorpus2.matcher(level.get(0))).matches()))throw new Exception("fichier incorecte");
	     			
		}
	     			
	     		
	
		return level;
		

	}
public int  getWidth() {return this.graphe.length;} 
public int getHeight() {return this.graphe[0].length;}
	
}
