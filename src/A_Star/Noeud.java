package Project_java_l3_IMI;

import java.util.ArrayList;

public class Noeud {
	
			char nom;
			int x, y,cout, heuristique;
			ArrayList<Noeud> path=new ArrayList<Noeud>();
			 
	public Noeud(char c,int i,int j) {
		nom=c;x=i;y=j;cout=0;heuristique=0;	
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public char getNom() {
		return nom;
	}
	public int compareto(Noeud n) {
	if (this.heuristique< n.heuristique)return 1;
	else if(this.heuristique==n.heuristique)return 0;
	else return -1;
	}
	public ArrayList<Noeud> getPath() {
		return path;
	}
	
@Override
public boolean equals(Object obj) {
	if (obj instanceof Noeud) {
		Noeud noeud = (Noeud) obj;
		if(x==noeud.x && y==noeud.y ) {
			return true;
		}
		
	}

return false;
}
@Override
public String toString() {

return "("+x+","+y+")";
}

}
