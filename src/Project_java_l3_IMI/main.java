package Project_java_l3_IMI;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Graphe g=new Graphe("e");
		Noeud n1=new Noeud('j',6,33 );
		Noeud n2=new Noeud(' ', 7,15);
		
g.Shortest_path(n1,n2);

System.out.println("gg:"+g);
	}

}
