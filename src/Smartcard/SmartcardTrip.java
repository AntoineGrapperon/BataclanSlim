/**
 * 
 */
package Smartcard;

import java.util.HashMap;

/**
 * The passenger trip class represent the trip as observed through smart card data. It has an observed boarding and it should be processed 
 * to determine the alighting.
 * @author Antoine
 *
 */
public class SmartcardTrip {

	String boardingStop;
	String alightingStop;
	String boardingTime;
	String boardingDate;
	
	HashMap<String,String> myData = new HashMap<String,String>();
	
	
}
