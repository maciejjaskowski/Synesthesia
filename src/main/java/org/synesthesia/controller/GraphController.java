package org.synesthesia.controller;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.synesthesia.model.EdgeWithMultiplicity;
import org.synesthesia.model.MyGraph;
import org.synesthesia.tools.DirectionChanger;
import org.synesthesia.view.SpringGraphVisualization;

import edu.uci.ics.jung.algorithms.shortestpath.BFSDistanceLabeler;
import edu.uci.ics.jung.graph.Graph;

public class GraphController<V,E extends EdgeWithMultiplicity> {

	private final CurrentGraph<V, E> currentGraph;
	private final Class<V> vertexClass;

	public GraphController(CurrentGraph<V,E> currentGraph, Class<V> vertexClass){
		this.currentGraph = currentGraph;
		this.vertexClass = vertexClass;		
	}
	
	public static <V,E extends EdgeWithMultiplicity> CurrentGraph<V,E> getCurrentGraphOf(final SpringGraphVisualization<V,E> springGraphVisualization){
		return new CurrentGraph<V, E>(){
			public MyGraph<V, E> get(){
				return (MyGraph<V, E>) springGraphVisualization.vv.getGraphLayout().getGraph();
			}
		};
	}

	private boolean matches(Method getter, V v, Pattern pattern){
		try {
			return pattern.matcher(getter.invoke(v).toString()).matches();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public Set<V> select(String fieldName, String patternString){
		Method getter = getGetter(fieldName);
		Pattern pattern = getPattern(patternString);
		HashSet<V> result = new HashSet<V>();
		try{
			return result;
		}finally{
			for(V v : currentGraph.get().getVertices()){
				if(matches(getter, v, pattern)){
					result.add(v);
				}
			}
		}
	}

	Method getGetter(String fieldName){
		try{
			return vertexClass.getMethod("get" + fieldName);
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		} catch (NoSuchMethodException e) {
			throw new UnsupportedOperationException("Method \"" + "get" + fieldName + "\" not found!");
		}  
	}

	Pattern getPattern(String pattern){
		try{
			return Pattern.compile(pattern);
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}catch(PatternSyntaxException e){
			throw new UnsupportedOperationException("The regexp pattern not recognized: " + pattern, e);
		}
	}
	
	public Set<V> getSonsOf(Set<V> fathers){
		Set<V> result = new HashSet<V>();
		try{
			return result;
		} finally{
			for(V v : fathers){
				result.addAll(currentGraph.get().getSuccessors(v));
			}
		}
	}
	
	public Set<V> getFathersOf(Set<V> sons){
		Set<V> result = new HashSet<V>();
		for(V v : sons){
			Collection<V> predecessors  = currentGraph.get().getPredecessors(v);
			if(predecessors != null){
				result.addAll(predecessors );
				
			}
		}
		return result;
	}
	
	public Set<V> getRoots(){
		Set<V> result = new HashSet<V>();
		try{
			return result;
		} finally {
			for(V v : currentGraph.get().getVertices()){
				if(currentGraph.get().getPredecessors(v).size() == 0){
					result.add(v);
				}
			}
		}
	}
	
	public Set<V> getAutowiresMoreThen(int n){
		throw new RuntimeException("Not implemented yet!");
	}
	
	public Set<V> getDescendants(Set<V> fathers){
		return getDescendants(getDescendantsWithDistances(fathers));
	}
	
	static private <V> Set<V> getDescendants(Map<V,Number> map){
		Map<V, Number> result = new HashMap<V,Number>();
		try{
			return result.keySet();
		}finally{
		Map<V, Number> descendantsWithDistances = map; 
		 for(Map.Entry<V,Number> entry : descendantsWithDistances.entrySet()){
				if(entry.getValue().intValue() >= 0){
					result.put(entry.getKey(), entry.getValue());
				}
		 }
		}
	}
	
	public Map<V,Number> getDescendantsWithDistances(Set<V> fathers){
		return getDescendantsWithDistances(currentGraph.get(), fathers);
	}
	
	static private <V,E> Map<V,Number> getDescendantsWithDistances(Graph<V,E> graph, Set<V> fathers){
		BFSDistanceLabeler<V, E> bfs = new BFSDistanceLabeler<V, E>();
		HashMap<V,Number> distances = new HashMap<V,Number>();
		bfs.labelDistances(graph, fathers);
		distances.putAll( bfs.getDistanceDecorator());
		return distances;
	}
	
	public Set<V> getGain(Set<V> roots){
		Set<V> desc = getDescendants(roots);
		Set<V> newFathers = getFathersOf(desc);
		newFathers.removeAll(desc);
		newFathers.removeAll(roots);
		
		Set<V> gain = new HashSet<V>(desc);
		gain.removeAll(getDescendants(newFathers));
		return gain;
	}
	
	public Set<V> getAncestors(Set<V> children){
		return getDescendants(getAncestorsWithDistances(children));
	}
	
	public Map<V, Number> getAncestorsWithDistances(Set<V> children){
		Graph<V, E> graph = DirectionChanger.changeDirection(new MyGraph<V,E>(currentGraph.get().getVertexFactory(), currentGraph.get().getEdgeFactory()), 
				currentGraph.get());
		return getDescendantsWithDistances(graph, children);
	}
}
