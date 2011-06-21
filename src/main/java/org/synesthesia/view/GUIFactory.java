package org.synesthesia.view;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.synesthesia.VerticesRepository;
import org.synesthesia.controller.GraphController;
import org.synesthesia.controller.VisualizationController;
import org.synesthesia.spring.SpringEdge;
import org.synesthesia.spring.SpringVertex;

public class GUIFactory {

	
	static public JFrame createSpringGUI(SpringGraphVisualization<SpringVertex, SpringEdge> springGraphVisualization){
		VisualizationController<SpringVertex, SpringEdge> vController = new VisualizationController<SpringVertex, SpringEdge>(
				springGraphVisualization.vv.getRenderContext());

		GraphController<SpringVertex, SpringEdge> gController = new GraphController<SpringVertex,SpringEdge>(
				GraphController.getCurrentGraphOf(springGraphVisualization),
				SpringVertex.class);
		
		@SuppressWarnings("unchecked")
		VerticesRepository<SpringVertex> verticesRepository = VerticesRepository.getInstance();
		
		return createSpringGUI(springGraphVisualization, gController, vController, verticesRepository);
	}

	static  JFrame createSpringGUI(
			SpringGraphVisualization<SpringVertex, SpringEdge> graphApplet, 
			GraphController<SpringVertex, SpringEdge> gController, 
			VisualizationController<SpringVertex, SpringEdge> vController, 
			VerticesRepository<SpringVertex> verticesRepository){
		
		JFrame frame = new JFrame();
		Container content = frame.getContentPane();
		
		
		
		content.setLayout(new BorderLayout());
		
		content.add(createGraphPanel(graphApplet), BorderLayout.WEST);
		content.add(createActionsPanel(graphApplet, gController,
				vController, verticesRepository), BorderLayout.EAST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
		return frame;
	}

	private static Panel createActionsPanel(
			SpringGraphVisualization<SpringVertex, SpringEdge> graphApplet,
			GraphController<SpringVertex, SpringEdge> gController,
			VisualizationController<SpringVertex, SpringEdge> vController, VerticesRepository<SpringVertex> verticesRepository) {
		ViewsFactory<SpringVertex, SpringEdge> viewsFactory = 
			new ViewsFactory<SpringVertex, SpringEdge>(
					gController, 
					vController,
					verticesRepository);
		Panel rightPanel = new Panel();
		BoxLayout rightLayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
		
		JPanel selectPanel = viewsFactory.createSelectApplet(graphApplet.vv);
		JPanel colorPanel = viewsFactory.createColorPanel(graphApplet.vv);
		JPanel cleanPanel = viewsFactory.createCleanPanel(graphApplet.vv);
		JPanel getOthersPanel = viewsFactory.createGetOthersPanel(graphApplet.vv);
		rightPanel.setLayout(rightLayout);
		rightPanel.add(selectPanel);
		rightPanel.add(colorPanel);
		rightPanel.add(cleanPanel);
		rightPanel.add(getOthersPanel);
		return rightPanel;
	}

	private static Panel createGraphPanel(
			SpringGraphVisualization<SpringVertex, SpringEdge> graphApplet) {
		FlowLayout leftLayout = new FlowLayout();
		Panel leftPanel = new Panel();
		leftPanel.setLayout(leftLayout);
		leftPanel.add(graphApplet);
		return leftPanel;
	}
}
