package algorithms;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


public class DFS 
{ 

	private LinkedList<Integer> adj[];
	private Graph graph_MST; 


	public DFS(Graph graph_MST) 
	{ 
		this.graph_MST = graph_MST;
		setArrayofAdj();
	} 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setArrayofAdj() {
		adj = new LinkedList[graph_MST.nodeSize()]; 
		
		for (int i=0; i<graph_MST.nodeSize(); ++i) {
			adj[i] = new LinkedList(); 
		}
		
		for (int i = 0; i < adj.length; i++) {
			Collection<edge_data> edges_of_node = graph_MST.getE(i);
			Iterator<edge_data> Iter = edges_of_node.iterator();
			
			while (Iter.hasNext()) {
				edge_data current_edge = Iter.next();
				addEdge(i, current_edge.getDest());
			}
		}
		
		
	}

	private void addEdge(int src, int dest)	{ 
		adj[src].add(dest); 
	} 
 
	private void DFSUtil(int v,boolean visited[]) 
	{ 
		visited[v] = true; 
		System.out.print(v+" "); 

		Iterator<Integer> i = adj[v].listIterator(); 
		while (i.hasNext()) 
		{ 
			int n = i.next(); 
			if (!visited[n]) 
				DFSUtil(n, visited); 
		} 
	} 

	public void startDFS(int index_to_start_with) { 
		boolean visited[] = new boolean[graph_MST.nodeSize()]; 
		DFSUtil(index_to_start_with, visited); 
	} 


} 

