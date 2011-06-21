package org.synesthesia.controller.transformers;



import java.awt.Color;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;
import org.synesthesia.VerticesRepository;
import org.synesthesia.tools.TransformersSequence;

public class GenericVertexTransformers<V> implements BVertexTransformers<V>{

	final private Transformer<V, String> label = 
	new Transformer<V,String>(){
    	public String transform(V input){
    		return "";
    	}
    };
    
    final private Transformer<V, Paint> mouse = new Transformer<V, Paint>(){

		@SuppressWarnings("unchecked")
		public Paint transform(V v) {
			if(((VerticesRepository<V>) VerticesRepository.getInstance()).contains(v)){
				return Color.YELLOW;
			}
			return null;
		}
    	
    };
    
	public  Transformer<V, Paint> toFillTransformer() {
		return new TransformersSequence<V, Paint>(mouse);
	}

	public  Transformer<V, String> toLabelTransformer() {
		return label;
	}

}
