package org.synesthesia.controller;

import org.synesthesia.model.EdgeWithMultiplicity;
import org.synesthesia.model.MyGraph;

public interface CurrentGraph<V,E extends EdgeWithMultiplicity> {
	
	public MyGraph<V,E> get();
}
