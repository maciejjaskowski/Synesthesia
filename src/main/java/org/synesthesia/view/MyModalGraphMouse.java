package org.synesthesia.view;

import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMousePlugin;

public class MyModalGraphMouse extends AbstractModalGraphMouse {
	   

	/**
     * create an instance with default values
     *
     */
    public MyModalGraphMouse() {
        this(1.1f, 1/1.1f);
    }
    
    /**
     * create an instance with passed values
     * @param in override value for scale in
     * @param out override value for scale out
     */
    public MyModalGraphMouse(float in, float out) {
        super(in,out);
        loadPlugins();
    }
    

    
    protected GraphMousePlugin edgeShowingPlugin;
    
    @Override
    protected void loadPlugins() {
        edgeShowingPlugin = new EdgeShowingPlugin();

        add(edgeShowingPlugin);
    }
}
