package org.synesthesia.model;

public class VertexStatistics{
	public VertexStatistics(int inDegree, int outDegree) {
		this.setInDegree(inDegree);
		this.setOutDegree(outDegree);
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getInDegree() {
		return inDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	private int inDegree;
	private int outDegree;
}