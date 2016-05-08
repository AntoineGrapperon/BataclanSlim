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
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import Controlers.Button;
import Controlers.TextField;

/**
 * @author Antoine
 *
 */
public class Window extends JFrame implements ActionListener {
	
	JPanel container = new JPanel();
	PaneContent content = new PaneContent();
    InformationPane helpPane = new InformationPane();
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
	    paneButtons.buttonPopulationSynthesis.myButton.addActionListener(new ButtonListener());
	    paneButtons.buttonShowMap.myButton.addMouseListener(new HelpListenerButton());
	    paneButtons.buttonShowMap.myButton.addActionListener(new ButtonListener());
	    paneButtons.buttonModelCalibration.myButton.addMouseListener(new HelpListenerButton());
	    paneButtons.buttonModelCalibration.myButton.addActionListener(new ButtonListener());
	    paneButtons.buttonDestinationInference.myButton.addMouseListener(new HelpListenerButton());
	    paneButtons.buttonDestinationInference.myButton.addActionListener(new ButtonListener());
	    paneButtons.buttonSocioDemoInference.myButton.addMouseListener(new HelpListenerButton());
	    paneButtons.buttonSocioDemoInference.myButton.addActionListener(new ButtonListener());
	    
	    content.popSynPane.tabPreparePopulationSynthesis.line1.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabPreparePopulationSynthesis.line2.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabPreparePopulationSynthesis.line3.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabPreparePopulationSynthesis.line4.myButton.addMouseListener(new HelpListenerButton());
	    content.popSynPane.tabPreparePopulationSynthesis.line5.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabPreparePopulationSynthesis.line6.myButton.addMouseListener(new HelpListenerButton());
	     
	    content.popSynPane.tabRunPopulationSynthesis.line1.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabRunPopulationSynthesis.line2.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabRunPopulationSynthesis.line3.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabRunPopulationSynthesis.line5.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabRunPopulationSynthesis.line6.myText.addMouseListener(new HelpListenerText());
	    content.popSynPane.tabRunPopulationSynthesis.line7.myButton.addMouseListener(new HelpListenerButton());
	    
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line1.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line2.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line3.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line4.myButton.addMouseListener(new HelpListenerButton());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line5.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line6.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line7.myText.addMouseListener(new HelpListenerText());
	    content.paneModelCalibration.tabPrepareBiogemeCalibration.line8.myButton.addMouseListener(new HelpListenerButton());
	    
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line1.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line2.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line3.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line4.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line5.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line6.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line7.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line8.myText.addMouseListener(new HelpListenerText());
	    content.paneSocioDemographicInference.tabRunSocioDemographicInference.line9.myButton.addMouseListener(new HelpListenerButton());
	    
	    
	    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, paneButtons, content);
	    JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, split, helpPane);
	    
	    
	    
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
			helpPane.helpText.setText(hlp);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			helpPane.helpText.setText("--");
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
			helpPane.helpText.setText(hlp);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			helpPane.helpText.setText("--");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
		  
}
