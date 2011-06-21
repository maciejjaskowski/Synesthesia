package org.synesthesia;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.synesthesia.events.VertexEvent;
import org.synesthesia.events.VertexEventListener;

public class VerticesRepository<V> {
	
	private static VerticesRepository instance;

	public static class Listener<V> implements VertexEventListener<V>{
		private org.synesthesia.VerticesRepository<V> verticesRepository;
		public Listener(VerticesRepository<V> verticesRepository){
			this.verticesRepository = verticesRepository;
		}
		public void handle(VertexEvent<V> event) {
			verticesRepository.switchOnOff(event.getVertex());
		}

	};
	
	public static void setSingleton(VerticesRepository vr){
		instance = vr;
	}
	
	public VerticesRepository(){
	}
	
	//
	//public void changeVerticesTo(String)
	//public Set getVertices(String name)
	//public Set setVertices(String name)
	
	//static public class Vertices{
	// 
	//  tutaj wszystkie metody, ktore sa uzywane ponizej 
	//  a tam zmiany w "defaultowej" Vertices.
	//}
	
	private Set<V> defaultVertices = new HashSet<V>();
	
	public Set<V> getVertices() {
		return new HashSet<V>(defaultVertices);
	}
	public void setVertex(V currentVertex) {
		this.defaultVertices = new HashSet<V>();
		this.defaultVertices.add(currentVertex);
	}
	
	public void setVertices(Collection<V> vertices){
		this.defaultVertices = new HashSet<V>(vertices);
	}
	
	public void addVertices(Collection<V> vertices){
		this.defaultVertices.addAll(vertices);
	}
	
	public void addVertex(V o){
		this.defaultVertices.add(o);
	}
	
	public void switchOnOff(V o){
		if(this.defaultVertices.contains(o)){
			this.defaultVertices.remove(o);
		}else{
			this.defaultVertices.add(o);
		}
	}

	public boolean contains(V v) {
		return this.defaultVertices.contains(v);
	}

	public void clean() {
		this.defaultVertices.clear();
	}

	public static VerticesRepository getInstance() {
		return instance;
	}

}
