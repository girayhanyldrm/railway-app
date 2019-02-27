import java.util.LinkedList;

//To represent the edges in the graph.
public class Edge{
	
	public Vertex target;
	public double weight;
	public boolean isActive;
	public boolean isBroken;

	
	public Edge(Vertex target, double weight,boolean isActive,boolean isBroken){
		this.target = target;
		this.weight = weight;
		this.isActive=isActive;
		this.isBroken=isBroken;
	}
	
	
	public static double distanceOfEdge(String a,String b){
		double distance=0;
		for(int i =0;i<Main.distanceList.size();i++){
			if(Main.distanceList.get(i)[0].equals(a) && Main.distanceList.get(i)[1].equals(b) || Main.distanceList.get(i)[0].equals(b) && Main.distanceList.get(i)[1].equals(a)){
				distance=Double.parseDouble(Main.distanceList.get(i)[2]);
			}
		}

		return distance;
	}
	static void breaking(Graph g,String src,String dest)
    {       
		System.out.println("COMMAND IN PROCESS >> BREAK "+src+">"+dest);
		System.out.println("\tCommand \"BREAK "+src  +">"+dest+"\"  has been executed successfully!");
		for(Vertex v:g.getVertices()){	
			if(v.name.equals(src)){
			for(int t=0;t<v.neighbours.size();t++){
					if(v.neighbours.get(t).target.name.equals(dest)){
						v.neighbours.get(t).isBroken=true;
					}
				}
		}
    }
}
	
	static void repairing(Graph g,String src,String dest)
    {       
		System.out.println("COMMAND IN PROCESS >> REPAIR "+src+">"+dest);
		System.out.println("\tCommand \"REPAIR "+src  +">"+dest+"\"  has been executed successfully!");
		for(Vertex v:g.getVertices()){	
			if(v.name.equals(src)){
			for(int t=0;t<v.neighbours.size();t++){
					if(v.neighbours.get(t).target.name.equals(dest)){
						v.neighbours.get(t).isBroken=false;
					}
				}
		}
    }
}

	public static void linking(String intersection,String active,Graph g) {
		System.out.println("COMMAND IN PROCESS >> LINK "+intersection+">"+active);
		System.out.println("\tCommand \"LINK "+intersection+">"+active+"\"  has been executed successfully!");
		String[] words = intersection.replace(">", ":").split(":");
		String[] kelime=words[1].replace("-", ",").split(",");
	
	
		for(int y=0;y<kelime.length;y=y+2){
			if(kelime[y].equals(active)){
				g.addEdge(Main.stationDic.get(words[0]),Main.stationDic.get(kelime[y]), Double.parseDouble(kelime[y+1]), true, false);
				g.addEdge(Main.stationDic.get(kelime[y]), Main.stationDic.get(words[0]),Double.parseDouble(kelime[y+1]), false, false);

			}
			else{
				g.addEdge(Main.stationDic.get(words[0]),Main.stationDic.get(kelime[y]), Double.parseDouble(kelime[y+1]), false, false);
				g.addEdge(Main.stationDic.get(kelime[y]), Main.stationDic.get(words[0]),Double.parseDouble(kelime[y+1]), false, false);

			}
		
		}
	}
	
	public static void upgradeEdge(Graph g,String dest){
		LinkedList<Vertex> liste=g.getVertex(Main.stationDic.get(dest)).path;
		int i=0;
		for(i=0;i<liste.size()-1;i++){
			upgrade2(g,liste.get(i).name,liste.get(i+1).name);
		}
		if(i>0){
		upgrade2(g,liste.get(i).name,dest);
		}
	}
	public static void upgrade2(Graph g,String src,String dest){
		for(Vertex v:g.getVertices()){	
			if(v.name.equals(src)){
			for(int t=0;t<v.neighbours.size();t++){
				if(v.neighbours.get(t).target.name.equals(dest)){
					if(v.neighbours.get(t).isActive==false){
						Main.count++;
					}
					v.neighbours.get(t).isActive=true;
				}
				else{
					v.neighbours.get(t).isActive=false;
					
					
				}
			}
			}
		}
	}

}