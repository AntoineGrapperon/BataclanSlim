/**
 * 
 */
package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlers.Button;
import Controlers.PromptButton;
import Controlers.PromptStringInformation;

/**
 * @author Antoine
 *
 */
public class TabRunPopulationSynthesis extends JPanel {

	Button act1;
	PromptStringInformation line1;
	PromptStringInformation line2;
	PromptStringInformation line3;
	//PromptStringInformation line4;
	PromptStringInformation line5;
	PromptStringInformation line6;
	PromptButton line7;
	
	public TabRunPopulationSynthesis(){
		super();
		Dimension d = new Dimension(30, 50);
		JLabel runPopSyn = new JLabel("Run the population synthesis");
		line1 = new PromptStringInformation("Path to census data", ".csv file -- provide the path to census data");
		line2 = new PromptStringInformation("Path to seeds" , ".csv file -- provide a path to a disaggregated data source which will be used to take a good seed to initiate the MCMC random walk");
		line3 = new PromptStringInformation("Number of batches", "number -- if you have an important population, you may want to proceed the population synthesis by batch to avoid to reach RAM capability and the resulting crash");
		
		JPanel line4 = new JPanel();
		line4.setLayout(new GridLayout(1,1));
		JLabel lbl = new JLabel("Number of threads");	
		ArrayList<Integer> threads =  new ArrayList<Integer>();
		for(int i = 1; i <= Runtime.getRuntime().availableProcessors()-1; i++){threads.add(i);}
		JComboBox threadChoice = new JComboBox(threads.toArray());
		line4.add(lbl);
		
		line5 = new PromptStringInformation("Output path for population synthesis statistical log", ".csv file -- there will be a single statistical log for all zones, including TAE, SAE and SRMSE values as well as categories counts, to ease the population synthesis validation. ");
		line6 = new PromptStringInformation("Output path for population synthesis agents", ".csv file -- the path to the actual population pool of agent that you will be able to use.");
		/*JPanel line7 = new JPanel();
	    act1 = new Button("Create a synthetic population pool", "<html>Input: <br>"
	    		+ "Census data -- CSV file -- it is used to create total population count. In our methodology we discarded children under 11. <br>"
	    		+ "Seed data -- CSV file -- it can be a disaggregated data source, it is used to initiate the Gibbs sampler in a reasonable space to speed up the random walk and make the output more reliable.<br>"
	    		+ "Number of batches -- in order to avoid crash due to a lack of RAM, you can decide to batch your data and process it sequentially. <br>"
	    		+ "Number of threads -- MCMC sampling techniques are random walk and it may take a while to process. If you have a CPU with multiple cores, you can use them all at once using multithreading. Be aware that using a big amount of your cores will slow down your computer.<br>"
	    		+ "Output: <br>"
	    		+ "Statistical log -- CSV file -- for each local area a statistical analysis is computed, Total Absolute Errors, Standardized Absolute Errors and Square Root Mean Squared Errors are computed. It allows to control the goodness of the population synthesis using GIS tools.<br>"
	    		+ "Population pool -- CSV file -- all produced agents are stored with their location and their attributes described in the control file."
	    		+ "--<br>"
	    		+ ""
	    		);
	    line7.add(act1);*/
		line7 = new PromptButton("Create synthetic population","<html>Input: <br>"
	    		+ "Census data -- CSV file -- it is used to create total population count. In our methodology we discarded children under 11. <br>"
	    		+ "Seed data -- CSV file -- it can be a disaggregated data source, it is used to initiate the Gibbs sampler in a reasonable space to speed up the random walk and make the output more reliable.<br>"
	    		+ "Number of batches -- in order to avoid crash due to a lack of RAM, you can decide to batch your data and process it sequentially. <br>"
	    		+ "Number of threads -- MCMC sampling techniques are random walk and it may take a while to process. If you have a CPU with multiple cores, you can use them all at once using multithreading. Be aware that using a big amount of your cores will slow down your computer.<br>"
	    		+ "Output: <br>"
	    		+ "Statistical log -- CSV file -- for each local area a statistical analysis is computed, Total Absolute Errors, Standardized Absolute Errors and Square Root Mean Squared Errors are computed. It allows to control the goodness of the population synthesis using GIS tools.<br>"
	    		+ "Population pool -- CSV file -- all produced agents are stored with their location and their attributes described in the control file."
	    		+ "--<br>"
	    		+ "",
	    		d);

		
	    JPanel prepareData = new JPanel();
	    prepareData.add(runPopSyn);
	    prepareData.add(line1);
	    prepareData.add(line2);
	    prepareData.add(line3);
	    prepareData.add(line4);
	    prepareData.add(threadChoice);
	    prepareData.add(line5);
	    prepareData.add(line6);
	    prepareData.add(line7);
	    
	    prepareData.setLayout(new BoxLayout(prepareData, BoxLayout.PAGE_AXIS));
	    
	    this.add(prepareData,BorderLayout.SOUTH);
	
	}
	
}
