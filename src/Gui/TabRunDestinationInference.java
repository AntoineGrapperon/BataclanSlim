/**
 * 
 */
package Gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Antoine
 *
 */
public class TabRunDestinationInference extends JPanel {

	public TabRunDestinationInference(){
		
		super();
		JLabel warning = new JLabel("this was not coded yet");
		JPanel myContent = new JPanel();
		myContent.add(warning);
		myContent.setLayout(new BoxLayout(myContent, BoxLayout.PAGE_AXIS));
		this.add(myContent);
	}
	
}
