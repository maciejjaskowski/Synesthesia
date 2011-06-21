package org.synesthesia.controller.transformers;

import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

public interface BVertexTransformers<V> {

	public  Transformer<V, Paint> toFillTransformer();

	public  Transformer<V, String> toLabelTransformer();
}
