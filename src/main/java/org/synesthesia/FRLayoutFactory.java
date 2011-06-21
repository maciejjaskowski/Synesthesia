package org.synesthesia;

import java.awt.Dimension;
import java.util.Properties;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.Graph;

public class FRLayoutFactory<V,E> extends LayoutFactory<V,E> {
	
	@Override
	public  AbstractLayout<V, E> create(Graph<V, E> graph, Properties props) {
		FRLayout<V, E> layout = new FRLayout<V, E>(graph);
		layout.setMaxIterations(100);
		layout.setSize(new Dimension(800,500));
	    return layout;
	}
}
