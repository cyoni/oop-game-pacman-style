package algorithms;


public class Prim { 

	
	private double[][] mat;
	private Graph gameGraph;

	public Prim(Graph gameGraph, double[][] mat) {
		this.mat = mat;
		this.gameGraph = gameGraph;
	}

	private int minKey(double key[], Boolean mstSet[]) 
	{ 
		double min = Double.MAX_VALUE;
		int min_index = -1; 
		for (int v = 0; v < mat.length; v++) 
			if (mstSet[v] == false && key[v] < min) { 
				min = key[v]; 
				min_index = v; 
			} 
		return min_index; 
	} 

	public Graph printMST(int parent[], double mat[][]) { 
		Graph graph_MST = new Graph();
		System.out.println("Edge \tWeight"); 
		for (int i = 1; i < mat.length; i++) {
			System.out.println(parent[i] + " - " + i + "\t" + mat[i][parent[i]]); 
			
			node_data node_src = gameGraph.getNode(parent[i]);
			node_data node_dest = gameGraph.getNode(i);
			
			if (graph_MST.getNode(node_src.getKey()) == null)
				graph_MST.addNode(node_src);
			graph_MST.addNode(node_dest);
			graph_MST.connect(node_src.getKey(), node_dest.getKey(), mat[i][parent[i]]);
		}
		return graph_MST;
	} 

	public Graph getMST() { 
		int parent[] = new int[mat.length]; 
		double key[] = new double[mat.length]; 
		Boolean mstSet[] = new Boolean[mat.length]; 
		for (int i = 0; i < mat.length; i++) { 
			key[i] = Integer.MAX_VALUE; 
			mstSet[i] = false; 
		} 
		key[0] = 0;
		parent[0] = -1; 
		for (int count = 0; count < mat.length - 1; count++) { 
			int u = minKey(key, mstSet); 
			mstSet[u] = true; 
			for (int v = 0; v < mat.length; v++) 
				if (mat[u][v] != 0 && mstSet[v] == false && mat[u][v] < key[v]) {
					parent[v] = u; 
					key[v] = mat[u][v]; 
				} 
		} 
		return printMST(parent, mat); 
	}
} 

