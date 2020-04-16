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
	
	private Map<Integer, node_data> g;
	private Map<Integer, List<edge_data>> e;

	public Graph() {
		g = new HashMap<>();
		e = new HashMap<>();
	}
	
	@Override
	public node_data getNode(int key) {
		return g.get(key); 
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		edge_data edge = null;
		List<edge_data> list = e.get(src);
		for (edge_data current_edge : list) {
			if (current_edge.getDest() == dest) edge = current_edge; 
			
		}
		return edge;
	}

	@Override
	public void addNode(node_data n) {
		if (g.containsKey(n.getKey()))	try {throw new IllegalAccessException("This id " + n.getKey() + " is already occupied.");} catch (IllegalAccessException e) {e.printStackTrace();} 
		g.put(n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double distance) {

		Edge edge_new = new Edge(src, dest, distance);
		
		List<edge_data> list = e.get(src);
		if (list == null) list = new ArrayList<>();
		list.add(edge_new);
		e.put(src, list);

		list = e.get(dest);
		if (list == null) list = new ArrayList<>();
		edge_new = new Edge(dest, src, w);
		list.add(edge_new);
		e.put(dest, list);	
	}

	@Override
	public Collection<node_data> getV() {
		return (Collection<node_data>) g.values() ;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return e.get(node_id);
	}

	@Override
	public node_data removeNode(int key) {
		List<edge_data> old_node_edges = e.get(key);
		g.remove(key); // remove the node
		e.remove(key); //  remove its edges	
		for (edge_data edge : old_node_edges) { // remove the edges that connect with him from his neighbors 
			int dest = edge.getDest();
			List<edge_data> l = e.get(dest); 
			for (int i=0; i< l.size(); i++) {
				edge_data edge_to_check = l.get(i);
				if (edge_to_check.getDest() == key)   {e.get(dest).remove(edge_to_check); i--;} 
			}
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		e.remove(src);
		e.remove(dest);
		return null;
	}

	@Override
	public int nodeSize() {
		return g.size();
	}

	@Override
	public int edgeSize() {
		return e.size();
	}
	

}
