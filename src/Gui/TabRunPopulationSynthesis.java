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
import Controlers.PromptComboBox;
import Controlers.PromptStringInformation;

/**
 * @author Antoine
 *
 */
public class TabRunPopulationSynthesis extends JPanel {


	PromptStringInformation line1;
	PromptStringInformation line2;
	PromptStringInformation line3;
	PromptStringInformation line4;
	PromptComboBox line5;
	PromptStringInformation line6;
	PromptStringInformation line7;
	PromptButton line8;
	
	public TabRunPopulationSynthesis(){
		super();
		Dimension d = new Dimension(30, 50);
		JLabel runPopSyn = new JLabel("Run the population synthesis");
		line1 = new PromptStringInformation("Path to census data", 
				"<html>.csv file -- provide the path to census data",
				"populationSynthesis\\data\\census2006GatineauOttawaDAlevel.csv");
		line2 = new PromptStringInformation("Path to seeds" , 
				"<html>.csv file -- provide a path to a disaggregated data source which will be used to take a good seed to initiate the MCMC random walk",
				"populationSynthesis\\data\\pumf2006GatineauOttawa.csv");
		
		line3 = new PromptStringInformation("Distributions directory",
				"<html> path to the folder where distributions are stored. It is expected to be structure as follow:<br>"
				+ "directory/to/my/distributions/<br>"
				+ "..............................globalDistributions.csv<br>"
				+ "..............................localZone1/<br>"
				+ ".........................................localDistributions.csv<br>",
				"populationSynthesis\\distributions\\");
		line4 = new PromptStringInformation("Number of batches", 
				"<html>number -- if you have an important population, you may want to proceed the population synthesis by batch to avoid to reach RAM capability and the resulting crash");
		
		ArrayList<Integer> threads =  new ArrayList<Integer>();
		for(int i = 1; i <= Runtime.getRuntime().availableProcessors()-1; i++){threads.add(i);}
		JComboBox threadChoice = new JComboBox(threads.toArray());
		line5 = new PromptComboBox("Number of threads", 
				"<html>Number -- it allows the software to work using multithreading. This is interesting to reduce computation time.",
				threads);

		line6 = new PromptStringInformation("Output path for population synthesis statistical log", 
				"<html>.csv file -- there will be a single statistical log for all zones, including TAE, SAE and SRMSE values as well as categories counts, to ease the population synthesis validation. ",
				"outputs\\populationSynthesisStatistics.csv");
		line7 = new PromptStringInformation("Output path for population synthesis agents", 
				"<html>.csv file -- the path to the actual population pool of agent that you will be able to use.",
				"outputs\\syntheticPopulation.csv");
		
		line8 = new PromptButton("Create synthetic population","<html>Input: <br>"
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

		
	    JPanel myContent = new JPanel();
	    myContent.add(runPopSyn);
	    myContent.add(line1);
	    myContent.add(line2);
	    myContent.add(line3);
	    myContent.add(line4);
	    myContent.add(line5);
	    //prepareData.add(threadChoice);
	    myContent.add(line6);
	    myContent.add(line7);
	    myContent.add(line8);
	    
	    myContent.setLayout(new BoxLayout(myContent, BoxLayout.PAGE_AXIS));
	    
	    this.add(myContent,BorderLayout.SOUTH);
	
	}
	
}
