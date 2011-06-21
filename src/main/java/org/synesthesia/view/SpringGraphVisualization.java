/* Copyright (c) 2003, the JUNG Project and the Regents of the University of
 * California All rights reserved.
 * 
 * This software is open-source under the BSD license; see either "license.txt"
 * or http://jung.sourceforge.net/license.txt for a description.
 * 
 */
package org.synesthesia.view;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;
import org.synesthesia.ExtendedVisualizationViewer;

import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;

/**
 * Demonstrates the use of images on graph edge labels.
 * 
 * @author Tom Nelson
 * 
 */
public class SpringGraphVisualization<V,E> extends JApplet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4332663871914930864L;
	

    final public ExtendedVisualizationViewer<V,E> vv;
    
    public SpringGraphVisualization(ExtendedVisualizationViewer<V,E> vv){
    	this(vv, null);
    }
    
    private final Container content;
    
    public SpringGraphVisualization(ExtendedVisualizationViewer<V,E> visualizationViewer, 
    		Transformer<V, String> tooltipTransformer){
    	this.vv = visualizationViewer;
    	final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
    	content = getContentPane();
        getContent().add(panel);
        
        final ModalGraphMouse graphMouse = new MyModalGraphMouse();
        vv.setGraphMouse(graphMouse);
        
        final ScalingControl scaler = new CrossoverScalingControl();
        
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1/1.1f, vv.getCenter());
                
            }
        });
        vv.setVertexToolTipTransformer(tooltipTransformer);

        
        
        
//        JComboBox modeBox = graphMouse.getModeComboBox();
//        JPanel modePanel = new JPanel();
//        modePanel.setBorder(BorderFactory.createTitledBorder("Mouse Mode"));
//        modePanel.add(modeBox);
        
        
        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));
        JPanel controls = new JPanel();
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(scaleGrid);
//        controls.add(modePanel);
        getContent().add(controls, BorderLayout.SOUTH);
    }

	public Container getContent() {
		return content;
	}
}
