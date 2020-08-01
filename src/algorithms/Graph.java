package algorithms;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class Graph implements IGraph, Serializable {

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8995919428111032917L;
	
	private Map<Integer, node_data> graph_dataStructure;
	private Map<Integer, List<edge_data>> edge_dataStructure;

	public Graph() {
		graph_dataStructure = new HashMap<>();
		edge_dataStructure = new HashMap<>();
	}
	
	@Override
	public node_data getNode(int key) {
		return graph_dataStructure.get(key); 
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		edge_data edge = null;
		List<edge_data> list = edge_dataStructure.get(src);
		if (list != null)
			for (edge_data current_edge : list) {
				if (current_edge.getDest() == dest) edge = current_edge; 
			}
		return edge;
	}

	@Override
	public void addNode(node_data n) {
		if (graph_dataStructure.containsKey(n.getKey()))	try {throw new IllegalAccessException("This id " + n.getKey() + " is already occupied.");} catch (IllegalAccessException e) {e.printStackTrace();}
		graph_dataStructure.put(n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double distance) {

		Edge edge_new = new Edge(src, dest, distance);
		
		List<edge_data> list = edge_dataStructure.get(src);
		if (list == null) list = new ArrayList<>();
		list.add(edge_new);
		edge_dataStructure.put(src, list);
		
		list = edge_dataStructure.get(dest);
		if (list == null) list = new ArrayList<>();
		edge_new = new Edge(dest, src, distance);
		list.add(edge_new);
		edge_dataStructure.put(dest, list);	
	}
	
	public List<node_data> getNodes() {
		List<node_data> nodes = new ArrayList<>();
		for (Entry<Integer, node_data> current_node : graph_dataStructure.entrySet())
			nodes.add(current_node.getValue());
		return nodes;
	}

	
	@Override
	public Collection<edge_data> getE(int node_id) {
		return edge_dataStructure.get(node_id);
	}

	@Override
	public node_data removeNode(int key) {
		List<edge_data> old_node_edges = edge_dataStructure.get(key);
		graph_dataStructure.remove(key); // remove the node
		edge_dataStructure.remove(key); //  remove its edges	
		for (edge_data edge : old_node_edges) { // remove the edges that connect with him from his neighbors 
			int dest = edge.getDest();
			List<edge_data> l = edge_dataStructure.get(dest); 
			for (int i=0; i< l.size(); i++) {
				edge_data edge_to_check = l.get(i);
				if (edge_to_check.getDest() == key)   {edge_dataStructure.get(dest).remove(edge_to_check); i--;} 
			}
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_dataStructure.remove(src);
		edge_dataStructure.remove(dest);
		return null;
	}

	@Override
	public int nodeSize() {
		return graph_dataStructure.size();
	}

	@Override
	public int edgeSize() {
		return edge_dataStructure.size();
	}
	
	
	public double[][] getMatrixGraph(int nodes){
		System.out.println(graph_dataStructure.size());
		double mat[][] = new double[nodes][nodes];
		for (int i=0;i<mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (i != j && getEdge(i, j) != null) {
				mat[i][j] = getEdge(i, j).getWeight();
				}
			}
		}
		return mat;
	}

	public Map<Integer, node_data> getGraph() {
		return graph_dataStructure;
	}
	

}
