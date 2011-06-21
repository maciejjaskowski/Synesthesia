package org.synesthesia.spring;

import org.synesthesia.model.EdgeFactory;

public class SpringEdgeFactory implements EdgeFactory<SpringEdge> {

	private static final long serialVersionUID = 4717409647708649218L;
	static int i = 0;
	public SpringEdge create(String from, String to){
		return new SpringEdge("from: " + from + " to: " + to);
	}
	public SpringEdge create() {
		return new SpringEdge(String.valueOf(i++));
	}

}
