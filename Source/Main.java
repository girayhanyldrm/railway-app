import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static Map<String,Integer> stationDic = new HashMap<String,Integer>();
	public static Map<Integer,String> stationDic2 = new HashMap<Integer,String>();
	public static ArrayList<String[]> distanceList = new ArrayList<String[]>();
	public static ArrayList<String>  printer=new ArrayList<String>();
	public static int count =0;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(args[1]);
		try{
			Scanner input= new Scanner(file);
			while(input.hasNextLine()){
				String data = input.nextLine();
				String[] words = data.replace("-", " ").split(" ");
				distanceList.add(words);
			}
			input.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}	
//////////////////////////////////////////////////////////////////////////////////////////		
		String fileName = args[0];
		File file2 = new File(fileName);
		int i=0;
		try{
			Scanner input= new Scanner(file2);
			while(input.hasNextLine()){
				String data = input.nextLine();
				String[] words = data.replace(">", ":").split(":");
				stationDic.put(words[0], i);
				stationDic2.put(i,words[0]);

				i++;
			}
			input.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
/////////////////////////////////////////////////////////////////////////////////////////
		
		Graph g=new Graph(stationDic.size());

		File file3 = new File(args[0]);
		try{
			Scanner input= new Scanner(file3);
			while(input.hasNextLine()){
				String data = input.nextLine();
				String[] words = data.replace(">", ":").split(":");
				String[] kelime=words[1].split(",");
				for(int y=0;y<kelime.length;y++){
					if(kelime[y].equals(words[2])){
						g.addEdge(stationDic.get(words[0]),stationDic.get(kelime[y]), Edge.distanceOfEdge(words[0], kelime[y]), true, false);
					}
					else{
						g.addEdge(stationDic.get(words[0]),stationDic.get(kelime[y]), Edge.distanceOfEdge(words[0], kelime[y]), false, false);
					}
				}
				
			}
			input.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			}
		double switchTime=Double.parseDouble(args[3]);
		Scanner scannerCommand= new Scanner(new File(args[2]));	
		String data2;
		while(scannerCommand.hasNextLine()){

			data2=scannerCommand.nextLine();
			String[] params=data2.replace(">"," ").split(" ");
			switch(params[0]){
			case"MAINTAIN":
				Vertex.underMaintain(g,params[1]);
				break;	
			case"SERVICE":
				Vertex.services(g,params[1]);
				break;
			case"BREAK":
				Edge.breaking(g, params[1], params[2]);
				break;
			case"REPAIR":
				Edge.repairing(g, params[1], params[2]);
				break;
			case"ADD":	
				stationDic2.put(i, params[1]);
				stationDic.put(params[1],i);
				g=Graph.adding(g,params[1]);
				i++;
				break;
			case"LINK":
				Edge.linking(params[1],params[2],g);
				break;
			case"ROUTE":
				System.out.println("COMMAND IN PROCESS >> ROUTE "+params[1]+">"+params[2]+" "+params[3]);
				Dijkstra.calculate(g.getVertex(stationDic.get(params[1])),Double.parseDouble(params[3]),switchTime);	
				Edge.upgradeEdge(g,params[2]);
				Dijkstra.printPath(g, params[2],params[1]);
				Vertex.reset(g);
				Vertex.resetPath(g);
				Main.count=0;
				System.out.println("\tCommand \"ROUTE "+params[1]+">"+params[2]+" "+params[3]+"\"  has been executed successfully!");

				break;
			case"LISTROUTESFROM":
				Print.listRoute(g, params[1]);
				break;
			case"LISTMAINTAINS":
				Print.listMaintain(g);
				break;
			case"LISTACTIVERAILS":
				Print.listActive(g);
				break;
			case"LISTBROKENRAILS":
				Print.listBrokenRails(g);
				break;
			case"TOTALNUMBEROFJUNCTIONS":
				Print.totalJunc(g);
				break;
			case"TOTALNUMBEROFRAILS":
				Print.totalRail(g);
				break;
			case"LISTCROSSTIMES":
				Print.listCrossTimes(g);
				break;
				
			default:
				System.out.println("COMMAND IN PROCESS >> "+params[0]);
				System.out.println("	Unrecognized command \""+params[0]+"\"!");
				break;
			}
			
		}
		scannerCommand.close();		
		
	}	

}