package org.synesthesia;


import java.awt.Color;
import java.awt.Dimension;

import org.apache.log4j.Logger;
import org.synesthesia.controller.transformers.BEdgeTransformers;
import org.synesthesia.controller.transformers.BVertexTransformers;
import org.synesthesia.controller.transformers.GenericEdgeTransformers;
import org.synesthesia.controller.transformers.GenericVertexTransformers;
import org.synesthesia.model.EdgeWithMultiplicity;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.EdgeLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;

public class VisualizationViewerFactory<V,E extends EdgeWithMultiplicity> {
	private static Logger logger = Logger.getLogger(VisualizationViewerFactory.class);
	
	public ExtendedVisualizationViewer<V,E> createVisualizationViewer(Graph<V,E> graph, Layout<V,E> layout){
        return createVisualizationViewer(
                layout, 
                new GenericVertexTransformers<V>(), 
                new GenericEdgeTransformers<E>());
    }
	
	private ExtendedVisualizationViewer<V,E> createVisualizationViewer(Layout<V, E> layout, BVertexTransformers<V> vTransformers,
				BEdgeTransformers<E> eTransformers) {
					ExtendedVisualizationViewer<V,E> vv =  new ExtendedVisualizationViewer<V,E>(layout, new Dimension(800, 500));
				
					//	      vv.getRenderContext().setEdgeDrawPaintTransformer(new PickableEdgePaintTransformer<String>(vv.getPickedEdgeState(), Color.black, Color.cyan));
					vv.setBackground(Color.white);
				
					//	      vv.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.cyan));
					//vv.getRenderContext().setEdgeLabelRenderer(new DefaultEdgeLabelRenderer(Color.cyan));
					EdgeLabelRenderer edgeLabelRenderer = vv.getRenderContext().getEdgeLabelRenderer();
					edgeLabelRenderer.setRotateEdgeLabels(false);
				
					vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);
					
					vv.getRenderContextController().setVertexTransformers(vTransformers);
					vv.getRenderContextController().setEdgeTransformers(eTransformers);
				
					// add a listener for ToolTips
					vv.setVertexToolTipTransformer(new ToStringLabeller<V>());
//					vv.getRenderContext().setEdgeLabelTransformer(eTransformers.toLabelTransformer());
				
					// add a listener for ToolTips
					vv.setEdgeToolTipTransformer(new ToStringLabeller<E>());				
					return vv;
	}

}
