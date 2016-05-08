/**
 * 
 */
package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlers.PromptButton;
import Controlers.PromptStringInformation;

/**
 * @author Antoine
 *
 */
public class TabRunSocioDemographicInference extends JPanel {

	PromptStringInformation line1;
	PromptStringInformation line2;
	PromptStringInformation line3;
	PromptStringInformation line4;
	PromptStringInformation line5;
	PromptStringInformation line6;
	PromptStringInformation line7;
	PromptStringInformation line8;
	PromptButton line9;
	
	public TabRunSocioDemographicInference(Dimension d){
		super();
		JLabel header1 = new JLabel("Infering socio-demographic charatecteristics");
		line1 = new PromptStringInformation("Path to the choice description file", 
				"<html> .txt file -- this file describes choices attribute following this format: <br>"
				+ "choice, number of discrete alternatives<br>"
				+ "--<br>"
				+ "For example: <br>"
				+ "FIDEL_PT_RANGE, 3<br>" + 
				"FIRST_DEPShort, 3<br>" + 
				"LAST_DEPShort, 3<br>" + 
				"N_ACT, 4<br>" + 
				"NEST, 5</html>");
		line2 = new PromptStringInformation("Path to hypothesis description file",
				"<html> .txt file -- the file describes hypothesis following this format:<br>"
				+ "DOWNTOWN, accessIndicator = 1, NEST = 2 - 3, dummy <br>"
				+ "where DOWNTOWN: is the hypothesis name, <br>"
				+ "accesIndicator: is the affecting attribute name (this name should be spelled the same way than in the travel survey <br>"
				+ "= 1-2 :are the affecting categories(1 and 2). For a continuous affeting category, just leave =<br>"
				+ "NEST: is the affected choice<br>"
				+ "=2-3: are the affected categories (2 and 3)<br>"
				+ "dummy: is the type of variable (choose: dummy, agent, alternative");
		line3 = new PromptStringInformation("Path to smart card data",
				"<html>.csv file -- path to the smart card data file<br>"
				+ "we recommend using at least one month of data so average values are meaningful<br>"
				+ "--<br>"
				+ "the program should be modified so it can connect to the transaction database, handle large data sets and process information on a daily basis</html>");
		line4 = new PromptStringInformation("Path to the synthetic population file", 
				".csv file -- provide here the path to the synthetic population you want to use.");
		line5 = new PromptStringInformation("Path to the neighborhood file",
				"<html>.csv file -- this file should contain a list of all bus stops Id and the list of all dissemination areas that are close enough<br>"
				+ "--<br>"
				+ "The format should be:<br>"
				+ "stopId,zoneId<br>"
				+ "208, 300405<br>"
				+ "208, 300406<br>"
				+ "209, 299 889<br>"
				+ "etc</html>");
		line6 = new PromptStringInformation("Path to the stops base","");
		line7 = new PromptStringInformation("Path to the model coefficients",
				"<html>.F12 file -- the BisonBiogeme software produces an output on the F12 format. We use this format to read parameters values<br>"
				+ "END <br>"
				+ "0 ParameterName	F	0.1	0.02<br>"
				+ "-1<br>"
				+ "here, 0.1 is the average value for the parameter, and that is what is being used. For information, 0.02 is the standard deviation, we don't use this information in our framework.</html>");
		line8 = new PromptStringInformation("Output path for the smart cards with inferred attributes",
				"<html>.csv file -- this file will have a line for each smart card including the new attributes. </html>");
		line9 = new PromptButton("Start infering socio-demographic attributes",
				"Be aware that this process may last for a few hours",
				d);
		
		JPanel myContent = new JPanel();
		myContent.add(header1);
		myContent.add(line1);
		myContent.add(line2);
		myContent.add(line3);
		myContent.add(line4);
		myContent.add(line5);
		myContent.add(line6);
		myContent.add(line7);
		myContent.add(line8);
		myContent.add(line9);
		
		myContent.setLayout(new BoxLayout(myContent, BoxLayout.PAGE_AXIS));
	    
		this.add(myContent, BorderLayout.SOUTH);
	}
}
