package org.synesthesia.view;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import java.awt.Dimension;
import java.util.HashSet;

import org.junit.Test;
import org.mockito.Mockito;
import org.synesthesia.ExtendedVisualizationViewer;
import org.synesthesia.VerticesRepository;
import org.synesthesia.controller.GraphController;
import org.synesthesia.controller.VisualizationController;
import org.synesthesia.view.GUIFactory;
import org.synesthesia.view.SpringGraphVisualization;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class GUIFactoryTest {

	@Test
	public void test() throws InterruptedException{
		
		SpringGraphVisualization graph = new SpringGraphVisualization(new ExtendedVisualizationViewer(new FRLayout(new DirectedSparseGraph()), new Dimension(800,500)), null);		
		
		GraphController graphController;
		
		GraphController gControllerMock = mock(GraphController.class);
		stub(gControllerMock.select(Mockito.anyString(), Mockito.anyString())).toReturn(new HashSet());
		
		VisualizationController vControllerMock = mock(VisualizationController.class);
		
		//stub(vControllerMock.addColor((Map) Mockito.anyObject(), (Color) Mockito.anyObject(), Mockito.anyInt()));
		
		GUIFactory.createSpringGUI( graph, gControllerMock, vControllerMock, VerticesRepository.getInstance());
		Thread.sleep(8000);
	}
}
