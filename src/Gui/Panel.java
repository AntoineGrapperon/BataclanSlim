/**
 * 
 */
package Gui;

import java.awt.Color;
import java.awt.GradientPaint;
/**
 * @author Antoine
 *
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class Panel extends JPanel { 
	private Image img;
	public Panel(){
		 try{
			  img = ImageIO.read(new File("C:\\Users\\Antoine\\Dropbox\\Antoine_Grapperon_MSCA\\Recherche\\Gabarit\\graph\\busStop.png"));
			  int y1 = this.getHeight();
			  int x1 = this.getWidth();
			  int y2 = img.getHeight(this);
			  int x2 = img.getWidth(this);
			  //g.drawImage(img,(x1-x2)/2,(y1-y2)/2,this);
			  
			 // g.fillRect(0, 0, this.getWidth()/2, this.getHeight()/2);  
		  }
		  
		  catch (IOException e) {
		      e.printStackTrace();
		  } 
	}
  public void paintComponent(Graphics g){
	  super.paintComponent(g);
	  int y1 = this.getHeight();
	  int x1 = this.getWidth();
	  int y2 = img.getHeight(this);
	  int x2 = img.getWidth(this);
	 // g.drawImage(img,(x1-x2)/2,(y1-y2)/2,this);
	  g.drawImage(img,0,0,x1,y1,null);
	 //g.drawImage(img,0,0,null);
	 
  } 
  
  
}