package org.synesthesia.spring;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

import org.apache.commons.collections15.Transformer;
import org.synesthesia.controller.transformers.BEdgeTransformers;
import org.synesthesia.model.MyGraph;



public class SpringEdgeTransformer implements BEdgeTransformers<SpringEdge>{
    
    final private Transformer<SpringEdge, Paint> paint;

    final private static Transformer<SpringEdge, String> label = new Transformer<SpringEdge,String>() {
        public String transform(SpringEdge e) {
            return "";
        }
        };

    final private static Transformer<SpringEdge, Stroke> stroke = new Transformer<SpringEdge,Stroke>(){

        public Stroke transform(SpringEdge e) {                
            return new BasicStroke(1);
        }
    
    };

    public SpringEdgeTransformer(final MyGraph<SpringVertex, SpringEdge> graph){
    	this.paint = new Transformer<SpringEdge, Paint>(){
            public Paint transform(SpringEdge e) {
            	SpringVertex source = graph.getSource(e);
            	SpringVertex dest = graph.getDest(e);
            	if(dest.getDate().getTime() - source.getDate().getTime() > 0){
            		return new Color(0, 255, 0);
            	}
            	return new Color(255, 0, 0);
            }
        };
    }

    public  Transformer<SpringEdge, String> toLabelTransformer() {
        return label;
    }

    public  Transformer<SpringEdge, Stroke> toArrowStrokeTransformer() {
        return stroke;
    }

    public  Transformer<SpringEdge, Stroke> toEdgeStrokeTransformer() {
        return stroke;
    }

    public  Transformer<SpringEdge, Paint> toArrowDrawPaintTranformer() {
        return paint;
    }

    public  Transformer<SpringEdge, Paint> toArrowFillPaintTransformer() {
        return paint;
    }

    public  Transformer<SpringEdge, Paint> toDrawPaintTransformer() {
        return paint;
    }
}

