package org.synesthesia.events;

import java.util.EventObject;

public class VertexEvent<V> extends EventObject {

	private V vertex;

	public VertexEvent(V source) {
		super(source);
		this.vertex = source;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1124054355146864291L;

	public V getVertex() {
		return vertex;
	}

}
