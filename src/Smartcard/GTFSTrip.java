/**
 * 
 */
package Smartcard;

/**
 * @author Antoine
 *
 */
public class GTFSTrip {

	String myId;
	String myRouteId;
	String myDirectionId;
	
	public GTFSTrip(){
		
	}
	
	public GTFSTrip(String tripId, String routeId, String directionId){
		myId = tripId;
		myRouteId = routeId;
		myDirectionId = directionId;
	}
}
