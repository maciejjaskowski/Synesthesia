package org.synesthesia.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VertexEventSource<V> {

	public static VertexEventSource instance = new VertexEventSource();
	public static VertexEventSource getInstance() {
		return instance;
	}

	private List<VertexEventListener<V>> _listeners = new ArrayList<VertexEventListener<V>>();
	public synchronized void addEventListener(VertexEventListener<V> listener)	{
		_listeners.add(listener);
	}
	public synchronized void removeEventListener(VertexEventListener<V> listener)	{
		_listeners.remove(listener);
	}

	public synchronized void fireEvent(V vertex) {
		VertexEvent<V> event = new VertexEvent<V>(vertex);
		Iterator<VertexEventListener<V>> i = _listeners.iterator();
		while(i.hasNext())	{
			i.next().handle(event);
		}
	}
}
