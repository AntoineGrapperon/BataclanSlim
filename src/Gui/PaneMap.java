/**
 * 
 */
package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
/**
 * @author Antoine
 *
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.io.File;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;
import org.geotools.swing.action.ResetAction;
import org.geotools.swing.action.ZoomInAction;
import org.geotools.swing.action.ZoomOutAction;
import org.geotools.swing.action.InfoAction;
import org.geotools.swing.action.MapAction;
import org.geotools.swing.action.NoToolAction;
import org.geotools.swing.action.PanAction;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.event.MapMouseListener;

import com.vividsolutions.jts.geom.Envelope;



 
public class PaneMap extends JMapPane { 
	double clickToZoom = 0.01;
	
	public PaneMap() throws IOException{
		super();
		//File file = JFileDataStoreChooser.showOpenFile("shp", null);
		File file = new File("D:\\Recherche\\Data\\Gatineau\\shp\\gatineauPop.shp");
        if (file == null) {
            return;
        }

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();
        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        

        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");
        map.addLayer(layer);
        this.setDisplayArea(map.getMaxBounds());
        
        this.setMapContent(map);
        this.setRenderer(new StreamingRenderer());
        
        
        JToolBar toolBar = new JToolBar();
        toolBar.setOrientation(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        ButtonGroup cursorToolGrp = new ButtonGroup();

        JButton zoomInBtn = new JButton(new ZoomInAction(this));
        toolBar.add(zoomInBtn);
        cursorToolGrp.add(zoomInBtn);

        JButton zoomOutBtn = new JButton(new ZoomOutAction(this));
        toolBar.add(zoomOutBtn);
        cursorToolGrp.add(zoomOutBtn);
        
        JButton centerBtn = new JButton(new ResetAction(this));
        toolBar.add(centerBtn);
        cursorToolGrp.add(centerBtn);
        
        JButton infoBtn = new JButton(new InfoAction(this));
        toolBar.add(infoBtn);
        cursorToolGrp.add(infoBtn);
        
        JButton noBtn = new JButton(new NoToolAction(this));
        toolBar.add(noBtn);
        cursorToolGrp.add(noBtn);
        
        JButton panBtn = new JButton(new PanAction(this));
        toolBar.add(panBtn);
        cursorToolGrp.add(panBtn);
        
        
        //this.setActionMap(new WheelAction(this));
        
        
        this.add(toolBar);
        this.add(toolBar, BorderLayout.WEST);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        //this.addMouseListener(new ZoomWithWheel());
       
	}
	
	
	class ZoomWithWheel implements  MapMouseListener{

		@Override
		public void onMouseClicked(MapMouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseDragged(MapMouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseEntered(MapMouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseExited(MapMouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseMoved(MapMouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMousePressed(MapMouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseReleased(MapMouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseWheelMoved(MapMouseEvent ev) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			int clicks = ev.getWheelAmount();
			System.out.println(clicks);
		   // -ve means wheel moved up, +ve means down
		    int sign = (clicks < 0 ? -1 : 1);

		    Envelope env = getDisplayArea();
		    double width = env.getWidth();
		    double scale = clickToZoom * sign;
		   
		    Rectangle paneArea = getVisibleRect();
	        DirectPosition2D mapPos = ev.getWorldPos();


	         DirectPosition2D corner = new DirectPosition2D(
	                mapPos.getX() - 0.5d * paneArea.getWidth() * scale,
	                mapPos.getY() + 0.5d * paneArea.getHeight() * scale);
	        
	         Envelope2D newMapArea = new Envelope2D();
	         newMapArea.setFrameFromCenter(mapPos, corner);
	         setDisplayArea(newMapArea);

		}
		
	}
  
}