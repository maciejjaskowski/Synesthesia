package org.synesthesia.model;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.uci.ics.jung.algorithms.shortestpath.BFSDistanceLabeler;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class MyGraph<V,E extends EdgeWithMultiplicity> extends DirectedSparseMultigraph<V, E>{
	
	private static final long serialVersionUID = 6677551387550510283L;
	
	
	transient HashMap<V, VertexStatistics> vertexStatisticsMap = null;


	private VertexFactory<V> vertexFactory;
	private EdgeFactory<E> edgeFactory;
	
	public MyGraph(VertexFactory<V> vertexFactory, EdgeFactory<E> edgeFactory){
		this.setVertexFactory(vertexFactory);
		this.setEdgeFactory(edgeFactory);
	}
	
	public void addEdge(V father, V son){	
		for(E ie : getOutEdges(father)){
			if(getOpposite(father, ie).equals(son)){
				ie.increaseMultiplicity();
				return;
			}
		}
		addEdge(edgeFactory.create(father.toString(), son.toString()), father, son, EdgeType.DIRECTED);
	}
	

	public Map<V, VertexStatistics> getVertexStatistics(){
		if(vertexStatisticsMap == null){ 
			vertexStatisticsMap = new HashMap<V, VertexStatistics>();
			for(V v : this.getVertices()){
				vertexStatisticsMap.put(v, new VertexStatistics(this.getInEdges(v).size(), this.getOutEdges(v).size()));
			}
		}
		return vertexStatisticsMap;
	}
	
	private enum EType {Inner, Out, In, Outer};
	
	 EType getEdgeType(E e, Set<V> vertices){
		V source = getSource(e);
		V dest   = getDest(e);
		if(vertices.contains(source) && vertices.contains(dest)){
			return EType.Inner;
		}
		if(vertices.contains(source)){
			return EType.Out;
		}
		if(vertices.contains(dest)){
			return EType.In;
		}
		return EType.Outer;
	}
	
	public MyGraph<V,E> substituteWith(Set<V> verticesToBeJoined, V substituteVertex){
		//TODO: nowy graf powinien pamietac jak sie rozwinac do starego grafu!
		MyGraph<V, E> result = new MyGraph<V,E>(getVertexFactory(), getEdgeFactory());
		for(V v : getVertices()){
			if(!verticesToBeJoined.contains(v)){
				result.addVertex(v);
			}
		}
		result.addVertex(substituteVertex);
		for(E e : getEdges()){
			EType edgeType = getEdgeType(e, verticesToBeJoined);
			switch(edgeType){
			case Inner: continue;
			case Out: result.addEdge(substituteVertex, getDest(e)); break;
			case In:  result.addEdge(getSource(e), substituteVertex); break;
			case Outer: result.addEdge(e, getSource(e), getDest(e)); break;
			}
		}		
		return result;
	}
	
	@Override public Collection<E> getIncidentEdges(V u){
		return super.getIncidentEdges(u) == null ? new HashSet<E>() : super.getIncidentEdges(u);
	}
	
	@Override public Collection<E> getInEdges(V vertex) {
        if (!containsVertex(vertex)){
        	return new HashSet<E>();
        }
        return super.getInEdges(vertex);
	}
	
	@Override public Collection<E> getOutEdges(V vertex) {
        if (!containsVertex(vertex)){
        	return new HashSet<E>();
        }
        return super.getOutEdges(vertex);
	}
	
	@Override public Collection<V> getNeighbors(V vertex) {
		if (!containsVertex(vertex)){
        	return new HashSet<V>();
        }
        return super.getNeighbors(vertex);
	}
	   
	public void setVertexStatistics(HashMap<V, VertexStatistics> vertexStatisticsMap) {
		this.vertexStatisticsMap = vertexStatisticsMap;
	}
	
	public Map<V, Number> getDistancesFrom(V v){
		BFSDistanceLabeler<V, E> bfs = new BFSDistanceLabeler<V, E>();
		bfs.labelDistances(this, v);
//		List<BVertex> verticesInOrderVisited = bfs.getVerticesInOrderVisited();
		Map<V, Number> distanceDecorator = bfs.getDistanceDecorator();
		return distanceDecorator;
	}

	public void setVertexFactory(VertexFactory<V> vertexFactory) {
		this.vertexFactory = vertexFactory;
	}

	public VertexFactory<V> getVertexFactory() {
		return vertexFactory;
	}

	public void setEdgeFactory(EdgeFactory<E> edgeFactory) {
		this.edgeFactory = edgeFactory;
	}

	public EdgeFactory<E> getEdgeFactory() {
		return edgeFactory;
	}
}
