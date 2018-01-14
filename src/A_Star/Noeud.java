package A_Star;

import java.util.ArrayList;

public abstract class Noeud {
	
			 private char nom;
			private int x, y,cout, heuristique;
			private ArrayList<Noeud> path=new ArrayList<Noeud>();
			
	public int getHeuristique() {
		return heuristique;
	}
	public int getCout() {
		return cout;
	}		 
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
	public void setCout(int cout) {
		this.cout = cout;
	}
	public void setHeuristique(int heuristique) {
		this.heuristique = heuristique;
	}
	public void setPath(ArrayList<Noeud> path) {
		this.path = path;
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
