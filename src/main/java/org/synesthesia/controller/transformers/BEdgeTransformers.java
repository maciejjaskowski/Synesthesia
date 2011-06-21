package org.synesthesia.controller.transformers;

import java.awt.Paint;
import java.awt.Stroke;

import org.apache.commons.collections15.Transformer;

public interface BEdgeTransformers<E> {
	public  Transformer<E, String> toLabelTransformer();

	public  Transformer<E, Stroke> toArrowStrokeTransformer();

	public  Transformer<E, Stroke> toEdgeStrokeTransformer();

	public  Transformer<E, Paint> toArrowDrawPaintTranformer();

	public  Transformer<E, Paint> toArrowFillPaintTransformer();

	public  Transformer<E, Paint> toDrawPaintTransformer();
}
