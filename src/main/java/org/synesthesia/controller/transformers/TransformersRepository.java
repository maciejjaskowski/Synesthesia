package org.synesthesia.controller.transformers;


import org.apache.commons.collections15.Transformer;
import org.synesthesia.spring.SpringVertex;

public class TransformersRepository {

	
//	public static Transformer<SpringVertex, Paint> showHowManyTimesIsAutowired(final MyGraph<SpringVertex, SpringEdge> graph) {
//		return new Transformer<SpringVertex, Paint>(){
//			public Paint transform(SpringVertex v){
//				if(v.equals(Globals.getCurrentVertex())){
//					return new Color(0,200,0);
//				}
//				if(graph.getNeighbors(v).contains(Globals.getCurrentVertex())){
//					return new Color(0,0,200);
//				}
//				int autowiring = graph.getVertexStatistics().get(v).getOutDegree();
//				int color = (int) (255f * Math.log10(Math.min(100, autowiring+1)) / 2f);
//				return new Color(color, 0, 0);
//			}
//		};
//	}
	
//	public static Transformer<SpringVertex, Paint> showHowManyTimesIsAutowired2(final MyGraph<SpringVertex, SpringEdge> graph) {
//		return new Transformer<SpringVertex, Paint>(){
//			public Paint transform(SpringVertex v){
//				if(v.equals(Globals.getCurrentVertex())){
//					return new Color(0,200,0);
//				}
//				Collection<SpringEdge> inEdges = graph.getInEdges(v);
//				org.apache.commons.collections.Transformer trans = new org.apache.commons.collections.Transformer(){
//
//					public Object transform(Object arg0) {
//						return graph.getSource((SpringEdge) arg0);
//					}
//					
//				};
//				Collection edgeSourceVertices = CollectionUtils.transformedCollection(new HashSet(), trans);
//				edgeSourceVertices.addAll((Collection) inEdges);
//				if(edgeSourceVertices.contains(Globals.getCurrentVertex())){
//					return new Color(0,0,200);
//				}
//				int autowiring = graph.getVertexStatistics().get(v).getOutDegree();
//				int color = (int) (255f * Math.log10(Math.min(100, autowiring+1)) / 2f);
//				return new Color(color, 0, 0);
//			}
//		};
//	}

	public static Transformer<SpringVertex, String> showEmptyString() {
		return new Transformer<SpringVertex, String>(){
            public String transform(SpringVertex v){
                return "";
            }
        };
	}

//	public static Transformer<SpringVertex, Paint> showHowManyBeansGetAutowiredByThisOne(final MyGraph<SpringVertex, SpringEdge> graph) {
//		return new Transformer<SpringVertex,Paint> (){
//			public Paint transform(SpringVertex v) {
//				if(v.equals(Globals.getCurrentVertex())){
//					return new Color(0,200,0);
//				}
//				if(graph.getNeighbors(v).contains(Globals.getCurrentVertex())){
//					return new Color(0,0,200);
//				}
//				int autowiring = graph.getVertexStatistics().get(v).getInDegree();
//				int color = (int) (255f * Math.log10(Math.min(100, autowiring+1)) / 2f);
//				return new Color(color, 0, 0);
//			}
//	    };
//	}
	
//	public static Transformer<SpringVertex, Paint> showAllBeansLeveregedByThisOne(final MyGraph<SpringVertex, SpringEdge> graph){
//		return new Transformer<SpringVertex, Paint> (){
//			SpringVertex lastVertex = null;
//			Map<SpringVertex, Number> dist = null;
//			public Paint transform(SpringVertex v){
//				if(dist == null || lastVertex == null ||    
//				   ! lastVertex.equals(Globals.getCurrentVertex())){
//					lastVertex = Globals.getCurrentVertex();
//					dist = graph.getDistancesFrom(Globals.getCurrentVertex());
//				}
//				Integer d = (Integer) dist.get(v);
//				
//				if(d == -1){
//					d = 10;
//				}else{
//					d = Math.min(9,d + 1);
//				}
//				int color = Math.min(250,(int) (255f * Math.log10(d)));
//				return new Color(0, 0, color);
//			}
//		};
//	}

}
