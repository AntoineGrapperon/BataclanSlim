/**
 * 
 */
package Smartcard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ActivityChoiceModel.BiogemeAgent;

/**
 * @author Antoine
 *
 */
public class GTFSStop {
	String myId;
	/**
	 * X coordinate of the position of the station in the NAD83 UTM ZONE 18N coordinate system.
	 */
	double lat;
	/**
	 * Y coordinate of the position of the station in the NAD83 UTM ZONE 18N coordinate system.
	 */
	double lon;
	
	public double getDistance(GTFSStop s) {
		// TODO Auto-generated method stub
		PublicTransitSystem.gc.setDestinationGeographicPoint(this.lon, this.lat);
		PublicTransitSystem.gc.setStartingGeographicPoint(s.lon, s.lat);
		return PublicTransitSystem.gc.getOrthodromicDistance();
	}

	public ArrayList<Smartcard> getSmartcards() {
		// TODO Auto-generated method stub
		ArrayList<Smartcard> localSmartcards = new ArrayList<Smartcard>();
		Iterator<Smartcard> itS = PublicTransitSystem.mySmartcards.iterator();
		while(itS.hasNext()){
			Smartcard tempS = itS.next();
			if(tempS.stationId == myId){
				if(!tempS.isDistributed){
					localSmartcards.add(tempS);
				}
			}
		}
		return localSmartcards;
	}

	public ArrayList<BiogemeAgent> getLocalPopulation() {
		// TODO Auto-generated method stub
		ArrayList<BiogemeAgent> localPopulation = new ArrayList<BiogemeAgent>();
		Iterator<BiogemeAgent> itA = PublicTransitSystem.myPopulation.iterator();
		while(itA.hasNext()){
			BiogemeAgent currA = itA.next();
			if(!currA.isDistributed){
				double agentZone = Double.parseDouble(currA.myAttributes.get(UtilsSM.zoneId));
				
				if(PublicTransitSystem.geoDico.containsKey(agentZone)){
					ArrayList<Integer> closeStation = PublicTransitSystem.geoDico.get(agentZone);
					if(closeStation.contains(myId)){
						localPopulation.add(currA);
					}
				}
			}
		}
		return localPopulation;
	}
}
