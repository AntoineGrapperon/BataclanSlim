/**
 * 
 */
package Gui;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import ActivityChoiceModel.BiogemeControlFileGenerator;
import ActivityChoiceModel.BiogemeSimulator;
import ActivityChoiceModel.CensusPreparator;
import ActivityChoiceModel.TravelSurveyPreparator;
import Controlers.Button;
import Controlers.ComboBox;
import Controlers.TextField;
import SimulationObjects.World;
import Smartcard.PublicTransitSystem;
import Smartcard.UtilsSM;
import Utils.*;

/**
 * @author Antoine
 *
 */
public class Window extends JFrame implements ActionListener {
	
	JPanel container = new JPanel();
	PaneContent content = new PaneContent();
    InformationPane informationPane = new InformationPane();
    PaneButtons paneButtons = new PaneButtons();

	public Window(){
		this.setTitle("BataclanGUI");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    //container.add(content, BorderLayout.CENTER);
	    
	    
	    paneButtons.buttonPopulationSynthesis.myButton.addMouseListener(new HelpListenerButton());
	    paneButtons.buttonShowMap.myButton.addMouseListener(new HelpListenerButton());
	    paneButtons.buttonModelCalibration.myButton.addMouseListener(new HelpListenerButton());
	    paneButtons.buttonDestinationInference.myButton.addMouseListener(new HelpListenerButton());
	    paneButtons.buttonSocioDemoInference.myButton.addMouseListener(new HelpListenerButton());
	    
	    paneButtons.buttonPopulationSynthesis.myButton.addActionListener(new ButtonListener());
	    paneButtons.buttonShowMap.myButton.addActionListener(new ButtonListener());
	    paneButtons.buttonModelCalibration.myButton.addActionListener(new ButtonListener());
	    paneButtons.buttonDestinationInference.myButton.addActionListener(new ButtonListener());
	    paneButtons.buttonSocioDemoInference.myButton.addActionListener(new ButtonListener());
	    
	    content.panePopulationSynthesis.tabPreparePopulationSynthesis.line1.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabPreparePopulationSynthesis.line2.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabPreparePopulationSynthesis.line3.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabPreparePopulationSynthesis.line4.myButton.addMouseListener(new HelpListenerButton());
	    content.panePopulationSynthesis.tabPreparePopulationSynthesis.line4.myButton.addActionListener(new PrepareGlobalPopulationSynthesisButton());
	    content.panePopulationSynthesis.tabPreparePopulationSynthesis.line5.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabPreparePopulationSynthesis.line6.myButton.addMouseListener(new HelpListenerButton());
	    content.panePopulationSynthesis.tabPreparePopulationSynthesis.line6.myButton.addActionListener(new PrepareLocalPopulationSynthesisButton());
	    
	    
	     
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line1.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line2.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line3.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line4.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line5.myComboBox.addMouseListener(new HelpListenerComboBox());
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line6.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line7.myText.addMouseListener(new HelpListenerText());
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line8.myButton.addMouseListener(new HelpListenerButton());
	    content.panePopulationSynthesis.tabRunPopulationSynthesis.line8.myButton.addActionListener(new RunPopulationSynthesisButton());
	    
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line1.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line2.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line3.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line4.myButton.addMouseListener(new HelpListenerButton());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line4.myButton.addActionListener(new PrepareBiogemeCtrlFile());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line5.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line6.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line7.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line8.myComboBox.addMouseListener(new HelpListenerComboBox());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line9.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line10.myButton.addMouseListener(new HelpListenerButton());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line10.myButton.addActionListener(new FormatTravelSurvey());
	    
	    content.paneModelCalibration.tabRunModelValidation.line1.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabRunModelValidation.line2.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabRunModelValidation.line3.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabRunModelValidation.line4.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabRunModelValidation.line5.myComboBox.addMouseListener(new HelpListenerComboBox());
	    content.paneModelCalibration.tabRunModelValidation.line6.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabRunModelValidation.line7.myButton.addMouseListener(new HelpListenerButton());
	    
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line1.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line2.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line3.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line4.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line5.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line6.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line7.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line8.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line9.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line10.myButton.addMouseListener(new HelpListenerButton());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line10.myButton.addActionListener(new RunSocioDemographicInference());
	    
	    
	    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, paneButtons, content);
	    JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, split, informationPane);
	    
	    
	    
	    //this.getContentPane().add(split, BorderLayout.WEST);
	    this.getContentPane().add(split2, BorderLayout.CENTER);
	    this.setVisible(true);
	    
	    
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			int index = ((Button)arg0.getSource()).id;
			content.cl.show(content, content.listContent[index]);
		}
	}
	
	class RunSocioDemographicInference implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
				String choiceDescription = content.paneSocioDemographicInference.tabRunSocioDemographicInference.line1.myText.getText();
				String hypothesis = content.paneSocioDemographicInference.tabRunSocioDemographicInference.line2.myText.getText();
				String smartcard = content.paneSocioDemographicInference.tabRunSocioDemographicInference.line3.myText.getText();
				String syntheticPopulation = content.paneSocioDemographicInference.tabRunSocioDemographicInference.line4.myText.getText();
				String geoDico = content.paneSocioDemographicInference.tabRunSocioDemographicInference.line5.myText.getText();
				
				String model = content.paneSocioDemographicInference.tabRunSocioDemographicInference.line7.myText.getText();
				String btch = content.paneSocioDemographicInference.tabRunSocioDemographicInference.line8.myText.getText();
				int nBatches = Integer.parseInt(btch.trim());
				String output = content.paneSocioDemographicInference.tabRunSocioDemographicInference.line9.myText.getText();
				
				BiogemeControlFileGenerator myCtrlGenerator = new BiogemeControlFileGenerator();
				PublicTransitSystem myPublicTransitSystem = new PublicTransitSystem();
				
		    	myCtrlGenerator.initialize(choiceDescription, hypothesis);
				myCtrlGenerator.printChoiceIndex(Utils.DATA_DIR + "outputs\\choiceIndex.csv");
				System.out.println("-- control file generator initiated");
				
				myPublicTransitSystem.initialize(
						myCtrlGenerator, 
						smartcard, 
						Utils.DATA_DIR + "ptSystem\\stops.txt",
						geoDico,
						syntheticPopulation,
						model
						);
				myPublicTransitSystem.getValues();
				System.out.println("--pt system initialized");
				myPublicTransitSystem.createZonalSmartcardIndex();
				myPublicTransitSystem.createZonalPopulationIndex();
				
				//myPublicTransitSystem.printStation(Utils.DATA_DIR + "ptSystem\\station_smartcard.csv");
				System.out.println("--potential smartcard assigned");
				
				//########
				Utils.occupationCriterion = true;
				//myPublicTransitSystem.processMatchingStationByStation();
				//myPublicTransitSystem.processMatchingOnPtRiders();
				myPublicTransitSystem.processMatchingOnPtRidersByBatch(nBatches);
				myPublicTransitSystem.printSmartcards(output);
			}
			catch(IOException|NumberFormatException e1){
				System.out.println(e1);
			}
			
		}
		
	}
	
	class RunModelValidation implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
				String travelSurvey = content.paneModelCalibration.tabRunModelValidation.line1.myText.getText();
				String choiceDescription = content.paneModelCalibration.tabRunModelValidation.line2.myText.getText();
				String hypothesis = content.paneModelCalibration.tabRunModelValidation.line3.myText.getText();
				String model = content.paneModelCalibration.tabRunModelValidation.line4.myText.getText();
				int choiceSetType = (int) content.paneModelCalibration.tabRunModelValidation.line5.myComboBox.getSelectedItem();
				String output = content.paneModelCalibration.tabRunModelValidation.line6.myText.getText();
				
				BiogemeSimulator mySimulator;
				BiogemeControlFileGenerator myCtrlGenerator = new BiogemeControlFileGenerator();
	    		myCtrlGenerator.initialize(choiceDescription, hypothesis);
	    		
				//String pathToModel = Utils.DATA_DIR + "ptSystem\\ctrlWithZones~1.F12";
				mySimulator = new BiogemeSimulator(myCtrlGenerator);
				mySimulator.initialize(travelSurvey);
				//Utils.DATA_DIR + "biogeme\\dataZones2.csv"
				mySimulator.importBiogemeModel(model);
				mySimulator.importNest(model);
				mySimulator.applyModelOnTravelSurveyPopulation(output,choiceSetType, true);
			}
			catch(IOException e1){
				System.out.println(e1.getMessage());
			}
		}
		
	}
	
	class FormatTravelSurvey implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String choiceDescription = content.paneModelCalibration.tabPrepareBiogemeCalibration.line5.myText.getText();
				String hypothesis = content.paneModelCalibration.tabPrepareBiogemeCalibration.line6.myText.getText();
				String pathToTravelSurvey = content.paneModelCalibration.tabPrepareBiogemeCalibration.line7.myText.getText();
				int nThreads = (int) content.paneModelCalibration.tabPrepareBiogemeCalibration.line8.myComboBox.getSelectedItem();
				String nAlt = content.paneModelCalibration.tabPrepareBiogemeCalibration.line9.myText.getText();
				int nAlternatives = Integer.parseInt(nAlt.trim());
				
				BiogemeControlFileGenerator myCtrlGenerator = new BiogemeControlFileGenerator();
				TravelSurveyPreparator odGatineau = new TravelSurveyPreparator();
	    		myCtrlGenerator.initialize(choiceDescription, hypothesis);
	    		odGatineau.initialize(pathToTravelSurvey);
		    	
		    	System.out.println("--computation with: " + nThreads + " logical processors");
		    	odGatineau.processDataMultiThreads(nThreads, nAlternatives, myCtrlGenerator);
		    	
			} catch (IOException | NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
		}
		
	}
	
	class PrepareBiogemeCtrlFile implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String choiceDescription = content.paneModelCalibration.tabPrepareBiogemeCalibration.line1.myText.getText();
			String hypothesis = content.paneModelCalibration.tabPrepareBiogemeCalibration.line2.myText.getText();
			String output = content.paneModelCalibration.tabPrepareBiogemeCalibration.line3.myText.getText();
			
			BiogemeControlFileGenerator myCtrlGenerator = new BiogemeControlFileGenerator();
	    	try {
	    		myCtrlGenerator.initialize(choiceDescription, hypothesis);
				myCtrlGenerator.generateBiogemeControlFile(output);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
		}
		
	}
	
	class PrepareGlobalPopulationSynthesisButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			String data = content.panePopulationSynthesis.tabPreparePopulationSynthesis.line1.myText.getText();
			String dataDescFile = content.panePopulationSynthesis.tabPreparePopulationSynthesis.line2.myText.getText();
			String destPath = content.panePopulationSynthesis.tabPreparePopulationSynthesis.line3.myText.getText();
			ConditionalGenerator condGenerator = new ConditionalGenerator();
			try {
				condGenerator.GenerateConditionalsStepByStep(data,dataDescFile,destPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
		
	class PrepareLocalPopulationSynthesisButton implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String data = content.panePopulationSynthesis.tabPreparePopulationSynthesis.line5.myText.getText();
			System.out.println("<html>-- start  creating local distributions from <br>	"+ data +"</html>");
			CensusPreparator census = new CensusPreparator(data);
			
	    	try {
				census.prepareDataColumnStorage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
	    	System.out.println("<html>-- done creating local distributions</html>");
		}
	}
	
	class RunPopulationSynthesisButton implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("<html>--population synthesis is running");
			try{
				String censusData = content.panePopulationSynthesis.tabRunPopulationSynthesis.line1.myText.getText();
				String seedData = content.panePopulationSynthesis.tabRunPopulationSynthesis.line2.myText.getText();
				String distributionsDirectory = content.panePopulationSynthesis.tabRunPopulationSynthesis.line3.myText.getText();
				String nBtch = content.panePopulationSynthesis.tabRunPopulationSynthesis.line4.myText.getText();
				int nBatch = Integer.parseInt(nBtch.trim());
				int	nThreads = (int) content.panePopulationSynthesis.tabRunPopulationSynthesis.line5.myComboBox.getSelectedItem();
				String outputStat = content.panePopulationSynthesis.tabRunPopulationSynthesis.line6.myText.getText();
				String outputPop = content.panePopulationSynthesis.tabRunPopulationSynthesis.line7.myText.getText();
				
				CensusPreparator census = new CensusPreparator(censusData);
		    	census.writeZonalInputFile(nBatch);	
		    	census.writeCtrlFile(nBatch);
		    	System.out.println("<html>-- local controler and local description file produced");
				
				OutputFileWritter localStatAnalysis = new OutputFileWritter();
	            localStatAnalysis.OpenFile(outputStat);
	            String headers =UtilsSM.zoneId + Utils.COLUMN_DELIMETER + Utils.population;
	            for(int i = 0; i < ConfigFile.AttributeDefinitionsImportance.size(); i++){
	    			headers = headers + Utils.COLUMN_DELIMETER + ConfigFile.AttributeDefinitionsImportance.get(i).category + "SRMSE"
	    					 + Utils.COLUMN_DELIMETER + ConfigFile.AttributeDefinitionsImportance.get(i).category + "TAE_DA"
	    							 + Utils.COLUMN_DELIMETER + ConfigFile.AttributeDefinitionsImportance.get(i).category + "%SAE_DA" ;
	            }
	            for(int i = 0; i < ConfigFile.AttributeDefinitionsImportance.size(); i++){
	            	for(int j = 0 ; j < ConfigFile.AttributeDefinitionsImportance.get(i).value; j++){
	            		headers = headers + Utils.COLUMN_DELIMETER  + ConfigFile.AttributeDefinitionsImportance.get(i).category + j + "TAE";
	            	}
	            }
	            for(int i = 0; i < ConfigFile.AttributeDefinitionsImportance.size(); i++){
	            	for(int j = 0 ; j < ConfigFile.AttributeDefinitionsImportance.get(i).value; j++){
	            		headers = headers + Utils.COLUMN_DELIMETER  + ConfigFile.AttributeDefinitionsImportance.get(i).category + "SAE";
	            	}
	            }
	            for(int i = 0; i < ConfigFile.AttributeDefinitionsImportance.size(); i++){
	            	for(int j = 0 ; j < ConfigFile.AttributeDefinitionsImportance.get(i).value; j++){
	            		headers = headers + Utils.COLUMN_DELIMETER  + ConfigFile.AttributeDefinitionsImportance.get(i).category + j + "Target";
	            	}
	            }
	            for(int i = 0; i < ConfigFile.AttributeDefinitionsImportance.size(); i++){
	            	for(int j = 0 ; j < ConfigFile.AttributeDefinitionsImportance.get(i).value; j++){
	            		headers = headers + Utils.COLUMN_DELIMETER  + ConfigFile.AttributeDefinitionsImportance.get(i).category + j + "Result";
	            	}
	            }
	            
	            localStatAnalysis.myFileWritter.write(headers);
	            
	            // Initialize the population pool log
	            OutputFileWritter population =  new OutputFileWritter();
	        	population.OpenFile(outputPop);
	        	headers = "agentId" + Utils.COLUMN_DELIMETER + UtilsSM.zoneId;
	            for(int i = 0; i < ConfigFile.AttributeDefinitions.size(); i++){
	    			headers = headers + Utils.COLUMN_DELIMETER + ConfigFile.AttributeDefinitions.get(i).category ;
	            }
	            population.myFileWritter.write(headers);
	            
	            World myWorld = new World(505);
	            ConfigFile.resetConfigFile();
	            myWorld = null;
		    	
	            //Create batches
		    	for(int i = 0; i < nBatch; i++){
		    		
		    		
		    		World currWorld = new World(505);
		    		currWorld.Initialize(true, 1, i, distributionsDirectory);
			    	System.out.println("--computation with: " + nThreads + " logical processors");
			    	String[] answer = currWorld.CreatePersonPopulationPoolLocalLevelMultiThreadsBatch(seedData, nThreads);
					currWorld = null;
					//System.out.println(answer[1]);
			    	localStatAnalysis.myFileWritter.write(answer[0]);
		            population.myFileWritter.write(answer[1]);
		            ConfigFile.resetConfigFile();
		    	}

	            localStatAnalysis.CloseFile();
		    	population.CloseFile();
			}
			catch(NumberFormatException | IOException e){
				System.out.println(e.getMessage());
			} 
			
	    	System.out.println("<html>-- population synthesis done</html>");
		}
	}
	
	class HelpListenerButton implements MouseListener{
		public void actionPerformed(MouseEvent arg0){}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			String hlp = ((Button)e.getSource()).help.toString();
			informationPane.helpText.setText(hlp);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			informationPane.helpText.setText("--");
		}
	}
	
	class HelpListenerComboBox implements MouseListener{
		public void actionPerformed(MouseEvent arg0){}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			String hlp = ((ComboBox)e.getSource()).help.toString();
			informationPane.helpText.setText(hlp);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			informationPane.helpText.setText("--");
		}
	}
	
	class HelpListenerText implements MouseListener{
		public void actionPerformed(MouseEvent arg0){}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			String hlp = ((TextField)e.getSource()).help.toString();
			informationPane.helpText.setText(hlp);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			informationPane.helpText.setText("--");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
		  
}
