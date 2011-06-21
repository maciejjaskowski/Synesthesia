package org.synesthesia.view;


import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import org.synesthesia.ExtendedVisualizationViewer;
import org.synesthesia.VerticesRepository;
import org.synesthesia.controller.GraphController;
import org.synesthesia.controller.VisualizationController;
import org.synesthesia.model.EdgeWithMultiplicity;
import org.synesthesia.spring.SpringEdge;
import org.synesthesia.spring.SpringVertex;

import edu.uci.ics.jung.visualization.VisualizationViewer;

public class ViewsFactory<V,E extends EdgeWithMultiplicity> {

	private final GraphController<V, E> gContr;
	private final VisualizationController<V, E> vContr;
	protected VerticesRepository<V> verticesRepository;

	public ViewsFactory(GraphController<V,E> gContr, VisualizationController<V,E> vContr, VerticesRepository<V> verticesRepository){
		this.gContr = gContr;
		this.vContr = vContr;
		this.verticesRepository = verticesRepository;
	}
	
	public JPanel createSelectApplet(final VisualizationViewer<V,E> viewer){
		JPanel panel = new JPanel();
		panel.setSize(300, panel.getHeight());
		try{
			return panel;
		}finally{
			final TextField regexp = new TextField(".*DAO");
			regexp.setSize(100, regexp.getHeight());
			final TextField fieldName = new TextField("BeanName");
			fieldName.setSize(80, fieldName.getHeight());
			
			JButton select = new JButton("select");
			select.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent event) {
					Set<V> selected = gContr.select(fieldName.getText(), regexp.getText());
					verticesRepository.addVertices(selected);
					viewer.repaint();
					//jak to odświeżyć? --> wysłać event
				}

			});
			panel.add(fieldName);
			panel.add(regexp);
			panel.add(select);
		}
	}
	
	public static class ColorPanel extends JPanel{
		private static final long serialVersionUID = -5703892693735861254L;
		private Map<String, Color> map;
		private JList jList;
		private JButton color;

		public ColorPanel(){
			map = new HashMap<String, Color>();
			for(java.lang.reflect.Field f : Color.class.getFields()){
				try {
					if(f.get(Color.class).getClass().equals(Color.class)){
						map.put(f.getName(), (Color) f.get(Color.class));
					}
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				}
			}
			jList = new JList(map.keySet().toArray());
			add(jList);
			
			color = new JButton("color");
			add(color);
		}
		
		public void addActionListener(ActionListener actionListener){
			color.addActionListener(actionListener);
		}
		
		public Color getColor(){
			return map.get(jList.getSelectedValue());
		}
	}

	public JPanel createColorPanel(final VisualizationViewer<V,E> viewer) {
		final ColorPanel cp = new ColorPanel();
			cp.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent event) {
				
				vContr.addColor(verticesRepository.getVertices(), cp.getColor());
				viewer.repaint();
			}
			
		});
			return cp;
	}
	
	private JPanel decorateCleanPanel(JPanel panel, final VisualizationViewer<V,E> viewer){
		JButton cleanSelection = new JButton("clean selection");
		cleanSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				VerticesRepository.getInstance().clean();
				viewer.repaint();
			}
		});
		
		JButton cleanAllColors = new JButton("clean colors");
		cleanAllColors.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				vContr.resetColor();
				viewer.repaint();
			}
			
		});
		
		JButton repaint = new JButton("repaint");
		repaint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				viewer.repaint();
			}
			
		});
		
		panel.add(cleanSelection);
		panel.add(cleanAllColors);
		panel.add(repaint);
		return panel;
	}
	
	public JPanel createCleanPanel(final VisualizationViewer<V,E> viewer) {
		return decorateCleanPanel(new JPanel(), viewer);
	}

	public JPanel createGetOthersPanel(final ExtendedVisualizationViewer<SpringVertex, SpringEdge> vv) {
		JPanel panel = new JPanel();
		panel.setSize(300, panel.getHeight());
		try{
			return panel;
		}finally{
			
			JButton descendants = new JButton("descendants");
			JButton ancestors = new JButton("ancestors");
			JButton gain = new JButton("gain");
			
			descendants.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event) {
					verticesRepository.addVertices(gContr.getDescendants(verticesRepository.getVertices()));
					vv.repaint();
				}
			});
			ancestors.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event) {
					verticesRepository.addVertices(gContr.getAncestors(verticesRepository.getVertices()));
					vv.repaint();
				}
			});
			gain.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event) {
					verticesRepository.addVertices(gContr.getGain(verticesRepository.getVertices()));
					vv.repaint();
				}
			});
			panel.add(descendants);
			panel.add(ancestors);
			panel.add(gain);
		}
	}
}
