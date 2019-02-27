import java.util.Collections;

public class Print {

	public static void listRoute(Graph g,String src){
		System.out.println("COMMAND IN PROCESS >> LISTROUTESFROM "+src);
		System.out.print("\tRoutes from "+src+":");
		for(Vertex v:g.getVertices()){	
			if(v.name.equals(src)){
			for(int t=0;t<v.neighbours.size();t++){
					Main.printer.add(v.neighbours.get(t).target.name);
				}
			}
		}	
		Collections.sort(Main.printer);
		for(int i=0;i<Main.printer.size();i++){
			System.out.print(" "+Main.printer.get(i));
		}
		System.out.println("");
		System.out.println("\tCommand \"LISTROUTESFROM "+src+"\"  has been executed successfully!");
		Main.printer.clear();
	}
	public static void listMaintain(Graph g){
		System.out.println("COMMAND IN PROCESS >> LISTMAINTAINS");
		System.out.print("\tIntersections under maintenance:");

		for(Vertex v:g.getVertices()){
			if(v.underMain==true){
				Main.printer.add(v.name);
			}
		}
		Collections.sort(Main.printer);
		for(int i=0;i<Main.printer.size();i++){
			System.out.print(" "+Main.printer.get(i));
		}
		System.out.println("\n\tCommand \"LISTMAINTAINS\"  has been executed successfully!");
		Main.printer.clear();
	}
	public static void listActive(Graph g) {
		System.out.println("COMMAND IN PROCESS >> LISTACTIVERAILS");
		System.out.print("\tActive Rails:");
		
		for(Vertex v:g.getVertices()){
			for(Edge baba : v.neighbours){
				if(baba.isActive==true){
					Main.printer.add(v+">"+baba.target.name);
				}
			}
		}
		Collections.sort(Main.printer);
		for(int i=0;i<Main.printer.size();i++){
			System.out.print(" "+Main.printer.get(i));
		}
		System.out.println("\n\tCommand \"LISTACTIVERAILS\"  has been executed successfully!");
		Main.printer.clear();
		
	}
	public static void listBrokenRails(Graph g){
		System.out.println("COMMAND IN PROCESS >> LISTBROKENRAILS");
		System.out.print("\tBroken rails:");
		
		for(Vertex v:g.getVertices()){
			for(Edge baba : v.neighbours){
				if(baba.isBroken==true){
					Main.printer.add(v+">"+baba.target.name);
				}
			}
		}
		Collections.sort(Main.printer);
		for(int i=0;i<Main.printer.size();i++){
			System.out.print(" "+Main.printer.get(i));
		}
		System.out.println("\n\tCommand \"LISTBROKENRAILS\"  has been executed successfully!");
		Main.printer.clear();
	}
	public static void totalJunc(Graph g){
		System.out.println("COMMAND IN PROCESS >> TOTALNUMBEROFJUNCTIONS");
		System.out.println("\tTotal # of junctions: "+g.getVertices().size());
		System.out.println("\tCommand \"TOTALNUMBEROFJUNCTIONS\"  has been executed successfully!");

	}
	public static void totalRail(Graph g) {
		System.out.println("COMMAND IN PROCESS >> TOTALNUMBEROFRAILS");
		int count = 0;
		for(Vertex v:g.getVertices()){	
				count+=v.neighbours.size();
		}
		System.out.println("\tTotal # of rails: "+count);
		System.out.println("\tCommand \"TOTALNUMBEROFRAILS\"  has been executed successfully!");

		
		
		
	}
	public static void listCrossTimes(Graph g) {
		System.out.println("COMMAND IN PROCESS >> LISTCROSSTIMES");
		System.out.print("\t# of cross times:");
		for(Vertex v:g.getVertices()){	
			if(v.totalPass>0){
			Main.printer.add(v.name+":"+v.totalPass);
		}
		}
		Collections.sort(Main.printer);
		for(int i=0;i<Main.printer.size();i++){
			System.out.print(" "+Main.printer.get(i));
		}
		System.out.println("\n\tCommand \"LISTCROSSTIMES\"  has been executed successfully!");
		Main.printer.clear();
	}
}
