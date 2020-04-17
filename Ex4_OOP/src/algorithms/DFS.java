package algorithms;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class DFS 
{ 

	private LinkedList<Integer> adj[];
	private Graph graph_MST; 
	private Queue<Integer> path = new LinkedList();
	private int V;

	public DFS(int total_nodes, Graph graph_MST) 
	{ 
		this.V = total_nodes;
		this.graph_MST = graph_MST;
		setArrayofAdj();
	} 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setArrayofAdj() {
		adj = new LinkedList[V]; 
		
		for (int i=0; i<V; ++i) {
			adj[i] = new LinkedList(); 
		}
		
		for (int i = 0; i < adj.length; i++) {
			Collection<edge_data> edges_of_node = graph_MST.getE(i);
			if (edges_of_node != null) {
			Iterator<edge_data> Iter = edges_of_node.iterator();
			
			while (Iter.hasNext()) {
				edge_data current_edge = Iter.next();
				addEdge(i, current_edge.getDest());
			}
			}
		}
		
		
	}

	private void addEdge(int src, int dest)	{ 
		adj[src].add(dest); 
	} 
 
	public Queue<Integer> getPath() {
		return path;
	}
	
	private void DFSUtil(int v,boolean visited[]) 
	{ 
		visited[v] = true; 
		System.out.print(v+" "); 
		path.add(v);

		Iterator<Integer> i = adj[v].listIterator(); 
		while (i.hasNext()) 
		{ 
			int n = i.next(); 
			if (!visited[n]) 
				DFSUtil(n, visited); 
		} 
	} 

	public void startDFS(int index_to_start_with) { 
		boolean visited[] = new boolean[V]; 
		DFSUtil(index_to_start_with, visited); 
	} 


} 

