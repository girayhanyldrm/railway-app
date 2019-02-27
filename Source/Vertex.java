import java.util.*;
public class Vertex implements Comparable<Vertex>{

	public boolean underMain;
	public int totalPass;
	public String name;
  	public ArrayList<Edge> neighbours;
	public LinkedList<Vertex> path;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;
	
	public int compareTo(Vertex other){
		return Double.compare(minDistance,other.minDistance);		
	}
	
	public Vertex(String name,boolean underMain){
		this.name = name;
		this.underMain=underMain;
		this.totalPass=0;
		neighbours = new ArrayList<Edge>();
		path = new LinkedList<Vertex>();
	}
	
	public String toString(){
		return name;
	}	
	public static void underMaintain(Graph g,String intersection){

		System.out.println("COMMAND IN PROCESS >> MAINTAIN "+intersection);
		System.out.println("\tCommand \"MAINTAIN "+intersection +"\"  has been executed successfully!");
		for(Vertex v:g.getVertices()){
			if(v.name.equals(intersection)){
				v.underMain=true;
			}
		}
	}
	
	public static void services(Graph g,String intersection){
		System.out.println("COMMAND IN PROCESS >> SERVICE "+intersection);
		System.out.println("\tCommand \"SERVICE "+intersection +"\"  has been executed successfully!");
		for(Vertex v:g.getVertices()){
			if(v.name.equals(intersection)){
				v.underMain=false;
			}
		}
	}
	public static void reset(Graph g){
		for(Vertex v:g.getVertices()){	
			v.minDistance=Double.POSITIVE_INFINITY;			
		}
	}
	public static void resetPath(Graph g){
		for(Vertex v:g.getVertices()){	
			v.path.clear();			
		}
	}
}