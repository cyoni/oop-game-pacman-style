package algorithms;

import Geom.Point2D;
/**
 * This interface represents the set of operations applicable on a 
 * node (vertex) in a (directional) weighted graph.
 *
 */
public interface node_data {
	/**
	 * Return the key (id) associated with this node.
	 * @return
	 */
	public int getKey();
	/** Return the location (of applicable) of this node, if
	 * none return null.
	 * 
	 * @return
	 */
	public Point2D getLocation();
	/** Allows changing this node's location.
	 * 
	 * @param p - new new location  (position) of this node.
	 */
	public void setLocation(Point2D p);

	/**
	 * return the remark (meta data) associated with this node.
	 * @return
	 */
	public String getInfo();
	/**
	 * Allows changing the remark (meta data) associated with this node.
	 * @param s
	 */
	public void setInfo(String s);
	/**
	 * Temporal data (aka color: e,g, white, gray, black) 
	 * which can be used be algorithms 
	 * @return
	 */
	public int getTag();
	/** 
	 * Allow setting the "tag" value for temporal marking an node - common 
	 * practice for marking by algorithms.
	 * @param t - the new value of the tag
	 */
	public void setTag(int t);
}
