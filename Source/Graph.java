import java.util.*;
public class Graph {
	private ArrayList<Vertex> vertices;
	public Graph(int numberVertices){
		vertices = new ArrayList<Vertex>(numberVertices);
		for(int i=0;i<numberVertices;i++){
			vertices.add(new Vertex(Main.stationDic2.get(i), false));
		}
	}
	
	public void addEdge(int src, int dest, double weight,boolean isActive,boolean isBroken){
		Vertex s = vertices.get(src);
		Edge new_edge = new Edge(vertices.get(dest),weight,isActive,isBroken);
		s.neighbours.add(new_edge);
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	
	public Vertex getVertex(int vert){
		return vertices.get(vert);
	}
	
	static Graph adding(Graph graph,String src)
    {       
		System.out.println("COMMAND IN PROCESS >> ADD "+src);
		System.out.println("\tCommand \"ADD "+src  +"\"  has been executed successfully!");
		Graph g2=new Graph(graph.vertices.size()+1);
        int i=0;
        for(Vertex v:graph.getVertices()){	
        	g2.vertices.get(i).underMain=v.underMain;
        	g2.vertices.get(i).totalPass=v.totalPass;
        	i++;
        	for(int t=0;t<v.neighbours.size();t++){
				Edge x=v.neighbours.get(t);
				g2.addEdge(Main.stationDic.get(v.name), Main.stationDic.get(v.neighbours.get(t).target.name), x.weight, x.isActive, x.isBroken);
			}
		}
        return g2;
    }
}