package org.synesthesia.controller.transformers;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

import org.apache.commons.collections15.Transformer;
import org.synesthesia.model.EdgeWithMultiplicity;

public class GenericEdgeTransformers<E extends EdgeWithMultiplicity> implements BEdgeTransformers<E>{
	
	final private  Transformer<E, Paint> paint = new Transformer<E, Paint>(){
		public Paint transform(E e) {
			return new Color(0,100,0);
		}
	};

	final private  Transformer<E, String> label = new Transformer<E,String>() {
		public String transform(E e) {
			return "";
		}
		};

	final private  Transformer<E, Stroke> stroke = new Transformer<E,Stroke>(){

		public Stroke transform(E e) {				
			return new BasicStroke((float) Math.log(e.getMultiplicity()));
		}
    
    };

	public  Transformer<E, String> toLabelTransformer() {
		return label;
	}

	public  Transformer<E, Stroke> toArrowStrokeTransformer() {
		return stroke;
	}

	public  Transformer<E, Stroke> toEdgeStrokeTransformer() {
		return stroke;
	}

	public  Transformer<E, Paint> toArrowDrawPaintTranformer() {
		return paint;
	}

	public  Transformer<E, Paint> toArrowFillPaintTransformer() {
		return paint;
	}

	public  Transformer<E, Paint> toDrawPaintTransformer() {
		return paint;
	}

}
