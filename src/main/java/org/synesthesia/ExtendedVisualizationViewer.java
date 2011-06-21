package org.synesthesia;


import java.awt.Dimension;

import org.synesthesia.controller.RenderContextController;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class ExtendedVisualizationViewer<V,E> extends VisualizationViewer<V, E> {

	private static final long serialVersionUID = -1362485066209510633L;
	final private RenderContextController<V, E> renderContextController;
	private Layout<V, E> layout;
	
	public ExtendedVisualizationViewer(Layout<V,E> layout, Dimension dim){
		super(layout, dim);
		this.setGraphLayout(layout);
		 renderContextController = new RenderContextController<V, E>(renderContext);
	}
	
	public RenderContext<V,E> getRenderContext(){
		return renderContext;
	}
	
	public RenderContextController<V,E> getRenderContextController(){
		return renderContextController;
	}

	public void setGraphLayout(Layout<V, E> layout) {
		this.layout = layout;
	}

	public Layout<V, E> getGraphLayout() {
		return layout;
	}
}
