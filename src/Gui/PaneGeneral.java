/**
 * 
 */
package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlers.PromptButton;
import Controlers.PromptStringInformation;

/**
 * @author Antoine
 *
 */
public class PaneGeneral extends JPanel{

	JPanel myContent = new JPanel();
	JLabel line1;	
	PromptStringInformation line2;
	PromptButton line3;
	JLabel line4;
	
	public PaneGeneral(){
		super();
		line1 = new JLabel("BATACLAN slim version");
		line2 = new PromptStringInformation("Path to the project directory",
				"<html>Provide the path to the project directory. It is recommended that the project directory is structured as follow:<br>"
				+ "...myProject//<br>"
				+ "...myProject//populationSynthesis//<br>"
				+ "...myProject//populationSynthesis//ctrl//<br>"
				+ "...myProject//populationSynthesis//ctrl//descFile.txt<br><br>"
				+ "...myProject//populationSynthesis//data//<br>"
				+ "...myProject//populationSynthesis//data//census.csv<br>"
				+ "...myProject//populationSynthesis//data//travelSurvey.csv<br><br>"
				+ "...myProject//populationSynthesis//distributions//<br><br>"
				+ "...myProject//populationSynthesis//temp//<br><br><br>"
				+ "...myProject//modelCalibration//<br><br>"
				+ "...myProject//modelCalibration//ctrl//<br>"
				+ "...myProject//modelCalibration//ctrl//choiceDescription<br>"
				+ "...myProject//modelCalibration//ctrl//hypothesis<br><br>"
				+ "...myProject//modelCalibration//data//<br>"
				+ "...myProject//modelCalibration//data//travelSurvey.csv<br><br><br>"
				+ "...myProject//association//ctrl//<br>"
				+ "...myProject//association//ctrl//choiceDescription.txt<br>"
				+ "...myProject//association//ctrl//hypothesis.txt<br>"
				+ "...myProject//association//ctrl//model.F12<br>"
				+ "...myProject//association//ctrl//geoDico500m.csv<br><br>"
				+ "...myProject//association//data//<br>"
				+ "...myProject//association//data//syntheticPopulation.csv<br>"
				+ "...myProject//association//data//smartcard.csv<br>",
				"");
		line3 = new PromptButton("Update project directory",
				"",
				new Dimension(30,50));
		line3.myButton.setSize(new Dimension(30,50));
		
		line4 = new JLabel("<html> <br><br><br><br><br><br><br><br><br><br><br><br><br><br>"
				+ "This software was named 'Bataclan' as a tribute to the victims of the DAECH "
				+ "terrorist attacks in Paris: <br>"
				+ "-the 7th of January 2016, 12 persons working for Charlie Hebdo were killed in Paris<br>"
				+ "-the 13rd of November 2016, 130 persons were killed in various places as they were <br>enjoying the Paris nightlife. "
				+ "90 of them were attending a music concert at the Bataclan showroom. <br>"
				+ "-hundreds more were injured severly.<br>"
				+ "Qu'ils reposent en paix.");
		JPanel format = new JPanel();
		format.add(line4);
		format.setLayout(new FlowLayout(FlowLayout.LEFT));
		myContent.add(line1);
		myContent.add(line2);
		myContent.add(line3);
		myContent.add(format);
		myContent.setLayout(new BoxLayout(myContent, BoxLayout.PAGE_AXIS));
		
		this.add(myContent, BorderLayout.SOUTH);
		
		
	}
}
