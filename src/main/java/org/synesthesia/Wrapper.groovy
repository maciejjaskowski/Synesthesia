package org.synesthesia

import org.synesthesia.controller.CurrentGraph;
import org.synesthesia.controller.GraphController
import org.synesthesia.controller.VisualizationController
import org.synesthesia.mimport.ImportVertex
import org.synesthesia.mimport.ImportVertexFactory
import org.synesthesia.model.MyGraph
import org.synesthesia.spring.SpringEdge
import org.synesthesia.spring.SpringEdgeFactory
import org.synesthesia.spring.SpringVertex
import org.synesthesia.spring.SpringVertexFactory
import org.synesthesia.view.SpringGraphVisualization
import org.synesthesia.view.ViewsFactory
import org.synesthesia.view.GUIFactory

import org.synesthesia.tools.ReaderFactory

import java.awt.BorderLayout;
import java.awt.Color
import java.awt.Container

import javax.sound.midi.ControllerEventListener;
import javax.swing.JApplet
import javax.swing.JFrame

import edu.uci.ics.jung.graph.Graph
import groovy.ui.Console



import org.apache.log4j.Logger;

class Wrapper {

	static private Logger logger = Logger.getLogger(Wrapper.class);
	static public MyGraph createSpringGraphFromFile(String file){
		try {
			return MyGraphFactory.create(ReaderFactory.fileReader(file),
										 new SpringVertexFactory(), 
									     new SpringEdgeFactory());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	static public MyGraph createImportGraphFromFile(String fileName){
		try {
			MyGraph graph = MyGraphFactory.createImportVertices(ReaderFactory.fileReader(fileName), new MyGraph<ImportVertex, SpringEdge>(new ImportVertexFactory(), 
									     new SpringEdgeFactory()))
			return MyGraphFactory.createImport(ReaderFactory.fileReader(fileName),
											   graph);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	static public void storeSpringGraphInBinaryFile(MyGraph graph, String fileName){
		new File(fileName)
		    .withObjectOutputStream { 
				out -> out <<graph 
			}
	}
	
	public static processLogAndStoreInBinaryFile(String logFileName){
		MyGraph graph = createSpringGraphFromFile(logFileName);
		storeSpringGraphInBinaryFile graph, logFileName + ".binary3"
	}
	
	public static SpringGraphVisualization createSpringGraphVisualization(Graph graph){
		Properties properties = new Properties();
		properties.put(LayoutFactory.LayoutFactoryClassName,"edu.uci.ics.jung.algorithms.layout.FRLayout")
		
		SpringGraphVisualization springGraphVisualization = new MyVisualizationFactory().createGraphVisualization(graph, properties);
	}
	
	public static SpringGraphVisualization createImportGraphVisualization(Graph graph){
		Properties properties = new Properties();
		properties.put(LayoutFactory.LayoutFactoryClassName,"edu.uci.ics.jung.algorithms.layout.FRLayout")
		
		SpringGraphVisualization springGraphVisualization = new MyVisualizationFactory().createGraphVisualization(graph, properties);
	}
	
	static public MyGraph createSpringGraphFromBinaryFile(String fileName){
		MyGraph sg;
		new File(fileName)
			.withObjectInputStream(
				MyGraph.class.getClassLoader(),
				{
					instream -> instream.eachObject {
						sg = it
					}
				})
		return sg;
	}
	
	public static void main(String[] args){

					  processLogAndStoreInBinaryFile("logs/spring-new.log")
		// 		MyGraph graph = createSpringGraphFromFile("spring.log");
		MyGraph graph = createSpringGraphFromBinaryFile("logs/spring-new.log.binary3")
		SpringGraphVisualization springGraphVisualization = createSpringGraphVisualization(graph);
		VisualizationController<SpringVertex, SpringEdge> controller = new VisualizationController<SpringVertex, SpringEdge>(
				springGraphVisualization.vv.getRenderContext());

		GraphController<SpringVertex, SpringEdge> gController = new GraphController<SpringVertex,SpringEdge>(
				GraphController.getCurrentGraphOf(springGraphVisualization),
				SpringVertex.class)

		logger.info("Running console");
		runConsole(springGraphVisualization, graph, controller, gController);
		
		//ViewsFactory viewsFactory = new ViewsFactory(gController, controller);
		logger.info("Creating GUI");
		GUIFactory.createSpringGUI(springGraphVisualization);
	}
	
//	public static void main(String[] args){
//		MyGraph graph = createImportGraphFromFile("imports");
//		final SpringGraphVisualization<ImportVertex, SpringEdge> springGraphVisualization = createImportGraphVisualization(graph);
//		VisualizationController<ImportVertex, SpringEdge> controller = new VisualizationController<ImportVertex, SpringEdge>(
//			springGraphVisualization.vv.getRenderContext());
//		
//		GraphController<ImportVertex, SpringEdge> gController = new GraphController<ImportVertex,SpringEdge>(
//			new CurrentGraph<ImportVertex, SpringEdge>(){
//				public MyGraph<ImportVertex, SpringEdge> get(){
//					return springGraphVisualization.vv.getGraphLayout().getGraph();
//				}
//			},
//			ImportVertex.class)
//		runConsole(springGraphVisualization, graph, controller, gController);
//		
//		showGraph(springGraphVisualization)
//		
//	}
	
	public static void test(){
		
	}
	
   public static void runConsole(SpringGraphVisualization springGraphVisualization, MyGraph graph, VisualizationController controller, GraphController gController){
	   import jaskowski.spring.model.MyGraph;
	   import jaskowski.spring.Wrapper;
	   ExtendedVisualizationViewer vv = springGraphVisualization.vv;

	  Console console = new Console();
	  
	  console.setVariable "applet", springGraphVisualization
	  console.setVariable "vv", springGraphVisualization.vv
	  console.setVariable "graph", graph
	  console.setVariable "controller", controller
	  console.setVariable "gController", gController
	  console.setVariable "selected", VerticesRepository.getInstance()
	  console.setVariable "console", console
	  console.setVariable "wrapper", this
	  console.run();
	  console.loadScriptFile(new File("example-scripts/graphExamples.groovy"))
   }
}
