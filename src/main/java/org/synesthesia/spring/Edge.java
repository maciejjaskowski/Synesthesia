package org.synesthesia.spring;

import org.synesthesia.model.EdgeWithMultiplicity;

public class Edge implements EdgeWithMultiplicity {

	private int mul = 1;
	public void increaseMultiplicity() {
		mul ++; 
	}

	public int getMultiplicity() {
		return mul;
	}

	public void setMultiplicity(int multiplicity) {
		mul = multiplicity;
	}

}
