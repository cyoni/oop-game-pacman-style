package algorithms;

import java.io.Serializable;

public class Edge implements edge_data, Serializable {

	/**
	 * This class contains information about an edge.
	 */

	private static final long serialVersionUID = 4269760787650059514L;
	private double weight;
	private int src, dest, tempData;
	private String info;
	
	public Edge(int x, int y, double weight) {
		src = x;
		dest = y;
		this.weight = weight;
	}
	
	@Override
	public int getSrc() {
		return src;
	}

	@Override
	public int getDest() {
		return dest;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return tempData;
	}

	@Override
	public void setTag(int t) {
		this.tempData = t;
	}



}
