package Project_java_l3_IMI;

import A_Star.Noeud;

public class Bomb extends Noeud{
	private int second;
	public Bomb(int i,int j,int second) {
	super('B',i,j);
	this.second=second;
	}
}
