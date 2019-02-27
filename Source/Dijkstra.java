import java.util.*;
public class Dijkstra{


	public static void calculate(Vertex source,double velocity, double switchTime){
	if(source.underMain==false){
		source.minDistance = 0;
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.add(source);
		
		while(!queue.isEmpty()){
			
			Vertex u = queue.poll();
			for(Edge edge:u.neighbours){
				
				if(edge.target.underMain==false){
					
					if(edge.isBroken==false){
						Double newDist=time(u,edge,velocity,switchTime);
				
						if(edge.target.minDistance>newDist){
								// Remove the node from the queue to update the distance value.
								queue.remove(edge.target);
								edge.target.minDistance = newDist;
								// Take the path visited till now and add the new node.s
								edge.target.path = new LinkedList<Vertex>(u.path);
								edge.target.path.add(u);					
								//Reenter the node with new distance.
								queue.add(edge.target);					
						}
					}
				}
			}
		}
	}
	}
	public static double time(Vertex u,Edge edge, double vel,double sTime){
		double time=0;
		
		if(edge.isActive==true){
			time = u.minDistance+(edge.weight/vel*60);

		}
		else{
			time = u.minDistance+(edge.weight/vel*60)+sTime;
		}
		return time;
	}
	

	public static void printPath(Graph g,String dest,String src){
		for(Vertex v:g.getVertices()){
			if(v.name.equals(dest)){
				if(v.minDistance!= Double.POSITIVE_INFINITY){
					System.out.print("\tTime (in min): ");
					System.out.printf("%.3f",v.minDistance);
					System.out.print("\n\tTotal # of switch changes: "+Main.count+"\n\tRoute from "+src+" to "+ dest+": ");
					for(Vertex pathvert:v.path) {
					pathvert.totalPass+=1;
						System.out.print(pathvert+" ");
					}
					v.totalPass+=1;
					System.out.println(v);
				}
				else{
					System.out.println("\tNo route from "+src+" to "+ dest+" found currently!");
				}
			}
		}
	}
}