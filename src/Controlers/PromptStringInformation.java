/**
 * 
 */
package Controlers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Antoine
 *
 */
public class PromptStringInformation extends JPanel{
	
	public TextField myText;
	
	public PromptStringInformation(String prompt, String hlp){
		super();
		this.setLayout(new GridLayout(2,1));
		JLabel label = new JLabel(prompt);
		myText = new TextField("", hlp);
		myText.setPreferredSize(new Dimension(850,20));
	    this.add(label);
	    this.add(myText);
	}
	
	public PromptStringInformation(String prompt, String hlp, String endPath){
		super();
		this.setLayout(new GridLayout(2,1));
		JLabel label = new JLabel(prompt);
		myText = new TextField(Utils.Utils.DATA_DIR + "\\" + endPath, hlp);
		myText.setPreferredSize(new Dimension(850,20));
	    this.add(label);
	    this.add(myText);
	}
	
	public PromptStringInformation(String prompt, String hlp, String endPath, Dimension d){
		super();
		this.setLayout(new GridLayout(2,1));
		JLabel label = new JLabel(prompt);
		myText = new TextField(Utils.Utils.DATA_DIR + "\\" + endPath, hlp);
		myText.setPreferredSize(d);
		//myText.setSize(d);
	    this.add(label);
	    this.add(myText);
	}

}
