package org.synesthesia.model;

import java.io.Serializable;

public interface VertexFactory<V> extends Serializable{

	public V create(String... args);
}
