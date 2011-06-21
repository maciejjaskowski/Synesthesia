package org.synesthesia.view;


import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import org.synesthesia.ExtendedVisualizationViewer;
import org.synesthesia.VerticesRepository;
import org.synesthesia.events.VertexEventSource;
import org.synesthesia.spring.SpringEdge;
import org.synesthesia.spring.SpringVertex;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;

public class EdgeShowingPlugin extends AbstractGraphMousePlugin 
	implements MouseListener, MouseMotionListener{

	public EdgeShowingPlugin() {
		this(InputEvent.BUTTON1_MASK, InputEvent.BUTTON1_MASK | InputEvent.SHIFT_MASK);
	}
	
    public EdgeShowingPlugin(int selectionModifiers, int addToSelectionModifiers) {
        super(selectionModifiers);
//        this.addToSelectionModifiers = addToSelectionModifiers;
//        this.lensPaintable = new LensPaintable();
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }
    
    public void mouseReleased(MouseEvent e) {
        VisualizationViewer vv = (VisualizationViewer)e.getSource();
        down = null;
        vv.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

	public void mousePressed(MouseEvent e) {
		down = e.getPoint();

		
		ExtendedVisualizationViewer vv = (ExtendedVisualizationViewer) e.getSource();
		GraphElementAccessor<SpringVertex, SpringEdge> pickSupport = vv.getPickSupport();
		Point2D ip = e.getPoint();
		

		final Layout<SpringVertex, SpringEdge> layout = vv.getGraphLayout();
		final SpringVertex vertexHit = pickSupport.getVertex(layout, ip.getX(), ip.getY());
		if(vertexHit != null){
			VertexEventSource.getInstance().fireEvent(vertexHit);
			vv.repaint();
		}else{
		    boolean accepted = checkModifiers(e);
		    if(accepted) {
		        vv.setCursor(cursor);
		    }
		}
//		final RenderContext<BVertex, BEdge> renderContext = vv.getRenderContext();
		
	}
	
	 public void mouseDragged(MouseEvent e) {
	        VisualizationViewer vv = (VisualizationViewer)e.getSource();
	        boolean accepted = checkModifiers(e);
	        if(accepted) {
	            MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
	            vv.setCursor(cursor);
	            try {
	                Point2D q = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(down);
	                Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
	                float dx = (float) (p.getX()-q.getX());
	                float dy = (float) (p.getY()-q.getY());
	                
	                modelTransformer.translate(dx, dy);
	                down.x = e.getX();
	                down.y = e.getY();
	            } catch(RuntimeException ex) {
	                System.err.println("down = "+down+", e = "+e);
	                throw ex;
	            }
	        
	            e.consume();
	            vv.repaint();
	        }
	    }


	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
