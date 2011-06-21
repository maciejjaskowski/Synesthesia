package org.synesthesia.events;

public interface VertexEventListener<V> {

	

	void handle(VertexEvent<V> event);

}
