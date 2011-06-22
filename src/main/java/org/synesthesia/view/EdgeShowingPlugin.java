package org.synesthesia.view;


import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import org.apache.log4j.Logger;
import org.synesthesia.ExtendedVisualizationViewer;
import org.synesthesia.events.VertexEventSource;
import org.synesthesia.spring.SpringEdge;
import org.synesthesia.spring.SpringVertex;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;

public class EdgeShowingPlugin extends AbstractGraphMousePlugin 
	implements MouseListener, MouseMotionListener{

	private int addToSelectionModifiers;
	private float offsetx;
	private float offsety;
	private SpringVertex vertexHit;
	private Logger logger = Logger.getLogger(EdgeShowingPlugin.class);

	public EdgeShowingPlugin() {
		this(InputEvent.BUTTON1_MASK, InputEvent.BUTTON1_MASK | InputEvent.SHIFT_MASK);
	}
	
    public EdgeShowingPlugin(int selectionModifiers, int addToSelectionModifiers) {
        super(selectionModifiers);
        this.addToSelectionModifiers = addToSelectionModifiers;
//        this.lensPaintable = new LensPaintable();
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }
    
    public void mouseReleased(MouseEvent e) {
        VisualizationViewer vv = (VisualizationViewer)e.getSource();
        vv.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        vertexHit = null;
        down = null;
    }

	public void mousePressed(MouseEvent e) {
		ExtendedVisualizationViewer vv = (ExtendedVisualizationViewer) e.getSource();
		GraphElementAccessor<SpringVertex, SpringEdge> pickSupport = vv.getPickSupport();
		Point2D ip = e.getPoint();
		
		PickedState pickedVertexState = vv.getPickedVertexState();

		final Layout<SpringVertex, SpringEdge> layout = vv.getGraphLayout();
		vertexHit = pickSupport.getVertex(layout, ip.getX(), ip.getY());
		if(vertexHit != null){
			logger .info("Hit: " + vertexHit);
                if(pickedVertexState.isPicked(vertexHit) == false) {
                	pickedVertexState.clear();
                	pickedVertexState.pick(vertexHit, true);
                }
                // layout.getLocation applies the layout transformer so
                // q is transformed by the layout transformer only
                Point2D q = layout.transform(vertexHit);
                // transform the mouse point to graph coordinate system
                Point2D gp = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.LAYOUT, ip);

                offsetx = (float) (gp.getX()-q.getX());
                offsety = (float) (gp.getY()-q.getY());
			
			vv.repaint();
		}else{
			logger .info("Hit nothing.");
			vv.setCursor(cursor);
		}
		down = e.getPoint();
		if(vertexHit != null) e.consume();
	}
	
	 public void mouseDragged(MouseEvent e) {
	        VisualizationViewer vv = (VisualizationViewer)e.getSource();
	        if(vertexHit == null) {
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
	        }
	        else
	        if(vertexHit != null) {
	        	Point p = e.getPoint();
	        	Point2D graphPoint = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p);
	        	Point2D graphDown = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(down);
	        	Layout layout = vv.getGraphLayout();
	        	double dx = graphPoint.getX()-graphDown.getX();
	        	double dy = graphPoint.getY()-graphDown.getY();
	        	PickedState ps = vv.getPickedVertexState();

	        	Point2D vp = (Point2D) layout.transform(vertexHit);
	        	vp.setLocation(vp.getX()+dx, vp.getY()+dy);
	        	layout.setLocation(vertexHit, vp);
	        	down = p;
	        	
	        	
	        } 
	        vv.repaint();
	        e.consume();
	    }


	public void mouseClicked(MouseEvent e) {
		logger.info("Mouse clicked");
		
		ExtendedVisualizationViewer vv = (ExtendedVisualizationViewer) e.getSource();
		GraphElementAccessor<SpringVertex, SpringEdge> pickSupport = vv.getPickSupport();
		Point2D ip = e.getPoint();
		
		PickedState pickedVertexState = vv.getPickedVertexState();

		final Layout<SpringVertex, SpringEdge> layout = vv.getGraphLayout();
		vertexHit = pickSupport.getVertex(layout, ip.getX(), ip.getY());
		if(vertexHit != null){
			logger.info("Hit: " + vertexHit);
			VertexEventSource.getInstance().fireEvent(vertexHit);
			vv.repaint();
		}
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
