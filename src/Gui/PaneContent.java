/**
 * 
 */
package Gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author Antoine
 *
 */
public class PaneContent extends JPanel {

	CardLayout cl = new CardLayout();
	String[] listContent = {"population_synthesis","model_calibration", "destination_inference", "socio_demo_inference", "show_map"};
	PanePopulationSynthesis popSynPane = new PanePopulationSynthesis();
	PaneModelCalibration paneModelCalibration = new PaneModelCalibration();
	PaneDestinationInference paneDestinationInference = new PaneDestinationInference();
	PaneSocioDemographicInference paneSocioDemographicInference = new PaneSocioDemographicInference();
    Panel map = new Panel();
	public PaneContent(){
		
	    this.setLayout(cl);
	    this.add(popSynPane, listContent[0]);
	    this.add(paneModelCalibration, listContent[1]);
	    this.add(paneDestinationInference, listContent[2]);
	    this.add(paneSocioDemographicInference, listContent[3]);
	    this.add(map, listContent[4]);
	   
	}
}
