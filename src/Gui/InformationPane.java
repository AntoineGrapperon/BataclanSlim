/**
 * 
 */
package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * @author Antoine
 *
 */
public class InformationPane extends JPanel {

	JPanel helpPane = new JPanel();
	JLabel helpText = new JLabel(("<html><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>"
			+ "<br><br><br><br><br><br><br><br><br><br><br><br></html>"));
	JLabel outputText = new JLabel("Output");
	JPanel outputPane = new JPanel();
	
	public InformationPane(){
		helpPane.setBackground(Color.decode("#FFFFCC"));
		helpPane.add(helpText);
		helpPane.setLayout(new BoxLayout(helpPane, BoxLayout.PAGE_AXIS));
		
		
		outputText.setForeground(Color.WHITE);
		outputPane.setBackground(Color.BLACK);
		outputPane.add(outputText);
		outputPane.setLayout(new BoxLayout(outputPane, BoxLayout.PAGE_AXIS));

	    JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, helpPane, outputPane);
	    this.add(split);
	    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
}
