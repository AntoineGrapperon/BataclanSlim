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
public class TabModelValidation extends JPanel {
	
	PromptStringInformation line1;
	PromptStringInformation line2;
	PromptStringInformation line3;
	PromptStringInformation line4;
	PromptButton line5;

	public TabModelValidation(Dimension d){
		super();
		JLabel header1 = new JLabel("Apply the model on the data used for its calibration");
		line1 = new PromptStringInformation("Path to the travel survey",
				"<html>.csv file -- path to the travel survey used for the model calibration. It is recommanded to calibrate the model over 80% of the data available and to apply to model on the 20% remaining.</html>");
		line2 = new PromptStringInformation("Path to the choice description file", 
				"<html> .txt file -- this file describes choices attribute following this format: <br>"
				+ "choice, number of discrete alternatives<br>"
				+ "--<br>"
				+ "For example: <br>"
				+ "FIDEL_PT_RANGE, 3<br>" + 
				"FIRST_DEPShort, 3<br>" + 
				"LAST_DEPShort, 3<br>" + 
				"N_ACT, 4<br>" + 
				"NEST, 5</html>");
		line3 = new PromptStringInformation("Path to hypothesis description file",
				"<html> .txt file -- the file describes hypothesis following this format:<br>"
				+ "DOWNTOWN, accessIndicator = 1, NEST = 2 - 3, dummy <br>"
				+ "where DOWNTOWN: is the hypothesis name, <br>"
				+ "accesIndicator: is the affecting attribute name (this name should be spelled the same way than in the travel survey <br>"
				+ "= 1-2 :are the affecting categories(1 and 2). For a continuous affeting category, just leave =<br>"
				+ "NEST: is the affected choice<br>"
				+ "=2-3: are the affected categories (2 and 3)<br>"
				+ "dummy: is the type of variable (choose: dummy, agent, alternative");
		line4 = new PromptStringInformation("Output path",
				"<html>.csv file -- the ouput will have simulated choice and observed choice so the user can draw confusion matrix. It also includes socio-demographic attributes to perform cross-sectionnal analysis of the model.</html>");
		line5 = new PromptButton("Apply the model on the observed data",
				"<html> It is recommended to run the model on observed data to analyse whether the output is correctly predicted or not. </html>",
				d);
		
		JPanel myContent = new JPanel();
	    myContent.add(header1);
	    myContent.add(line1);
	    myContent.add(line2);
	    myContent.add(line3);
	    myContent.add(line4);
	    myContent.add(line5);
	    
	    myContent.setLayout(new BoxLayout(myContent, BoxLayout.PAGE_AXIS));
	    this.add(myContent,BorderLayout.SOUTH);
	}
}
