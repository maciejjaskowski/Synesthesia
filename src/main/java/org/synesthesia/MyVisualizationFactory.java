package org.synesthesia;


import java.util.Properties;

import org.apache.commons.collections15.Transformer;
import org.apache.log4j.Logger;
import org.synesthesia.VerticesRepository.Listener;
import org.synesthesia.events.VertexEventSource;
import org.synesthesia.model.EdgeWithMultiplicity;
import org.synesthesia.model.MyGraph;
import org.synesthesia.model.VertexStatistics;
import org.synesthesia.view.SpringGraphVisualization;

import edu.uci.ics.jung.algorithms.layout.Layout;

public class MyVisualizationFactory<V,E extends EdgeWithMultiplicity> {

	private static Logger logger = Logger.getLogger(MyVisualizationFactory.class);
	static public String VertexFactoryClassName = "vertexFactoryClassName"; 
	static void validateGraphProperties(Properties props){
	}
	
	
	public SpringGraphVisualization<V,E> createGraphVisualization(MyGraph<V,E> graph, Properties props){		
		Layout<V,E> layout = new LayoutFactory<V,E>().create(graph, props);
		logger.info("Layout created");
		
		final ExtendedVisualizationViewer<V,E> vv = new VisualizationViewerFactory<V, E>().createVisualizationViewer(graph, layout);
		logger.info("VisualizationViewer created");
		
		VerticesRepository.setSingleton(new VerticesRepository<V>());
		VertexEventSource.getInstance().addEventListener(new Listener<V>(VerticesRepository.getInstance()));
		
        Transformer<V, String> tooltipTransformer = new Transformer<V,String>(){
			public String transform(V bVertex) {
				VertexStatistics vs = ((MyGraph<V, E>) vv.getGraphLayout().getGraph()).getVertexStatistics().get(bVertex);
				return bVertex.toString() + " (@Autowired by:" + vs.getInDegree() + ", @Autowiring: " + vs.getOutDegree();
			}
        };
        vv.setVertexToolTipTransformer(tooltipTransformer);
        try{
        	return new SpringGraphVisualization<V,E>(vv, tooltipTransformer);
        }finally{
        	logger.info("SpringGraphVisualization created");
        }
	}
}
