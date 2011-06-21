package org.synesthesia.controller;


import java.awt.Color;
import java.awt.Paint;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections15.Transformer;
import org.synesthesia.tools.TransformersSequence;

import edu.uci.ics.jung.visualization.RenderContext;

public class VisualizationController<V,E> {

	private final RenderContext<V, E> context;
	
	public Transformer<V,Paint> getColorTransformer(final Set<V> setOfBeans, final Paint paint){
		return new Transformer<V,Paint>(){
			public Paint transform(V v){
				if(setOfBeans.contains(v)){
					return paint;
				}
				return null;
			}
		};
	}
	
	public void resetColor(){
		((TransformersSequence<V,Paint>) getContext().getVertexFillPaintTransformer()).resetToDefaults();
	}
	
	public void addColor(final Set<V> setOfBeans, final Paint paint){
		getContext().setVertexFillPaintTransformer(
						((TransformersSequence<V,Paint>) getContext().getVertexFillPaintTransformer())
						.add(getColorTransformer(setOfBeans, paint)));
	}
	
	public void addColor(final Map<V,Number> mapOfBeans, final Color color, final int max){
		Transformer<V, Paint> transformer = getLogColorTransformer(mapOfBeans, color, max);
		((TransformersSequence<V,Paint>) getContext().getVertexFillPaintTransformer())
			.add(transformer);
	}
	
	public Transformer<V,Paint> getLogColorTransformer(final Map<V,Number> setOfBeans, final Color color, final int max){		
		//min = 0;
		return new Transformer<V,Paint>(){
			public Paint transform(V v){
				
				
				int lvl = setOfBeans == null ? max : setOfBeans.get(v).intValue();
				if(lvl < 0){
					lvl = max;
				}
				float frac =  (float) Math.min(1f, 
						(Math.log10(Math.min(max+1, lvl+1)) / Math.log10(max+1)));
				System.out.println(frac);
				Color c = new Color((int) (color.getRed() * frac), (int) (color.getGreen() * frac), (int) (color.getBlue() * frac));
				System.out.println(c);
				return c;
			}
		};
	}
	
	public VisualizationController(RenderContext<V,E> context){
		this.context = context;
	}

	public RenderContext<V, E> getContext() {
		return context;
	}
}
