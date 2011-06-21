package org.synesthesia.tools;

import edu.uci.ics.jung.graph.Graph;

public class DirectionChanger {

	static public <V,E> Graph<V,E> changeDirection(Graph<V,E> d, Graph<V,E> g){
		for(V v : g.getVertices()){
			d.addVertex(v);
		}
		for(E e : g.getEdges()){
			d.addEdge(e, g.getDest(e), g.getSource(e));
		}
		return d;
	}
}
