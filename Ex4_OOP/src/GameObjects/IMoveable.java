package GameObjects;


/**
 *  This interface represents an object that eats.
 *
 * @author Yoni
 */

public interface IMoveable {
	
	public void increaseEatenFruits();
	public int getNumEatenFruits();
	public void setDegree(double deg);
	public double getDegree();
	public void setVelocity(double vel);
	public double getVelocity();
	
}
