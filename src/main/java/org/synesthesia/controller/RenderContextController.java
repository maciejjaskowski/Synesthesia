package org.synesthesia.controller;



import java.awt.Rectangle;
import java.awt.Shape;

import org.apache.commons.collections15.Transformer;
import org.synesthesia.controller.transformers.BEdgeTransformers;
import org.synesthesia.controller.transformers.BVertexTransformers;

import edu.uci.ics.jung.visualization.RenderContext;

public class RenderContextController<V,E> {

	private final RenderContext<V,E> renderContext;

	public RenderContextController(RenderContext<V,E> renderContext){
		this.renderContext = renderContext;
		
	}
	public void setVertexTransformers(BVertexTransformers<V> vTransformers) {
		getRenderContext().setVertexLabelTransformer(vTransformers.toLabelTransformer());
		getRenderContext().setVertexFillPaintTransformer(vTransformers.toFillTransformer());
		getRenderContext().setVertexShapeTransformer(
				new Transformer<V, Shape>(){

					public Shape transform(V arg0) {
						return new Rectangle(6,6);
					}
					
				}
				);
	}

	public void setEdgeTransformers(BEdgeTransformers<E> eTransformers) {
		getRenderContext().setEdgeDrawPaintTransformer(eTransformers.toDrawPaintTransformer());
		getRenderContext().setArrowFillPaintTransformer(eTransformers.toArrowFillPaintTransformer());
		getRenderContext().setArrowDrawPaintTransformer(eTransformers.toArrowDrawPaintTranformer());
		getRenderContext().setEdgeStrokeTransformer(eTransformers.toEdgeStrokeTransformer());
		getRenderContext().setEdgeArrowStrokeTransformer(eTransformers.toArrowStrokeTransformer());
	}
	public RenderContext<V,E> getRenderContext() {
		return renderContext;
	}
}
