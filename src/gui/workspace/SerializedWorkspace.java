package gui.workspace;

import java.io.Serializable;

public class SerializedWorkspace implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7096772559917804549L;
	private String backgroundColor;
	private String trailColor;
	public String getBackgroundColor() {
		return backgroundColor;
	}
	/**
	 * @return the trailColor
	 */
	public String getTrailColor() {
		return trailColor;
	}
	/**
	 * @param trailColor the trailColor to set
	 */
	public void setTrailColor(String trailColor) {
		this.trailColor = trailColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
