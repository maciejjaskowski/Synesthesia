package org.synesthesia;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.codehaus.groovy.control.ConfigurationException;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.graph.Graph;

public class LayoutFactory<V,E> {
	private static Logger logger = Logger.getLogger(LayoutFactory.class);
	
	static public String LayoutFactoryClassName = "layoutFactoryClassName";
	public  AbstractLayout<V, E> create(Graph<V,E> graph, Properties props) {
		if(!props.contains(LayoutFactoryClassName)){
			props = new Properties();
			props.put(LayoutFactoryClassName, FRLayoutFactory.class);
		}
		return createLayout(graph, props);
	}
	
	
	public AbstractLayout<V, E> createLayout(Graph<V,E> graph, Properties props){
		assert props.get(LayoutFactoryClassName) instanceof Class : "Layout property is of incorrect type!";
		@SuppressWarnings("unchecked")
		Class<? extends LayoutFactory<V,E>> layoutClass = (Class<? extends LayoutFactory<V,E>>) props.get(LayoutFactoryClassName);
		
		try {
			return layoutClass.newInstance().create(graph, props);
		} catch (InstantiationException e) {
			throw new ConfigurationException(LayoutFactoryClassName + " set improperly to: " + layoutClass);
		} catch (IllegalAccessException e) {
			throw new ConfigurationException(LayoutFactoryClassName + " set improperly to: " + layoutClass);
		}
	}

	

}
