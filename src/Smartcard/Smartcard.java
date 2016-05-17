/**
 * 
 */
package Smartcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import ActivityChoiceModel.BiogemeChoice;
import ActivityChoiceModel.BiogemeControlFileGenerator;
import ActivityChoiceModel.UtilsTS;
import Utils.Utils;

/**
 * @author Antoine
 *
 */
public class Smartcard extends BiogemeChoice{

	double cardId;
	String stationId;
	//public int choiceId;
	protected HashMap<String, ArrayList<String>> myData = new HashMap<String, ArrayList<String>>();
	public int columnId;
	public boolean isDistributed = false;
	public int fare;
	
	public Smartcard(){
		nest = UtilsTS.stoUser;
	}
	
	public Smartcard(BiogemeChoice toConvert){
		biogeme_id = toConvert.biogeme_id;
		biogeme_case_id = toConvert.biogeme_case_id;
		choiceCombination = toConvert.choiceCombination;
		probability = toConvert.probability;
		utility = toConvert.utility;
		nest = UtilsTS.stoUser;
	}
	
	public void setId(double id) {
		// TODO Auto-generated method stub
		this.cardId = id;
	}
	
	
	public void setChoiceId(){
		HashMap<String, Integer> myCombination = new HashMap<String, Integer>();
		int firstDep = getWeekDayAverageFirstDep();
		int lastDep = getWeekDayAverageLastDep();
		int nAct = getWeekDayAverageActivityCount(UtilsSM.timeThreshold);
		int ptFidelity = getWeekDayAveragePtFidelity(UtilsSM.distanceThreshold);
		
		myCombination.put(UtilsTS.firstDep+"Short", firstDep);
		myCombination.put(UtilsTS.lastDep+"Short", lastDep);
		myCombination.put(UtilsTS.nAct, nAct);
		myCombination.put(UtilsTS.fidelPtRange, ptFidelity);
		myCombination.put(UtilsTS.nest, 2);
		biogeme_case_id = BiogemeControlFileGenerator.returnChoiceId(myCombination);
		choiceCombination = myCombination;
	}


	
	/**
	 * This function process the departure hour which is assumed to be in a string format HHMM based on the public transit authority journey which starts at 00AM and end at 2730 (in case of Gatineau, 2005, public transit system.)
	 * It returns a category of departure hour (defined with respect to:
	 * a) granularity available in the travel survey for calibrating the activity choice model
	 * b) hypothesis made in the activity choice model in terms of power of making a difference between categories.
	 * For first departure of the day, this function implements the following categorization (0am to 7 am, 7am to 9 am, after 9am)
	 * @return the departure hour category according the methodology developed 
	 */
	private int getWeekDayAverageFirstDep() {
		// TODO Auto-generated method stub
		int counter = 0;
		double averageDepHour = 0;
		for(int i = 0; i < myData.get(UtilsSM.cardId).size(); i++){
			String currDate = myData.get(UtilsSM.date).get(i);
			if(isWeekDay(currDate)){
				if(myData.get(UtilsSM.firstTrans).get(i).equals(UtilsSM.isFirst)){
					counter++;
					averageDepHour += hourStrToDouble(myData.get(UtilsSM.time).get(i));
				}
			}
		}
		averageDepHour = averageDepHour/counter;
		
		if(averageDepHour <= UtilsSM.morningPeakHourStart){
			return 0;
		}
		else if(averageDepHour < UtilsSM.morningPeakHourEnd){
			return 1;
		}
		else if(averageDepHour >= UtilsSM.morningPeakHourEnd){
			return 2;
		}
		else{
			System.out.println("--error affecting departure hours");
			return 10;
		}
	}
	
	/**
	 * This function process the departure hour which is assumed to be in a string format HHMM based on the public transit authority journey which starts at 00AM and end at 2730 (in case of Gatineau, 2005, public transit system.)
	 * It returns a category of departure hour (defined with respect to:
	 * a) granularity available in the travel survey for calibrating the activity choice model
	 * b) hypothesis made in the activity choice model in terms of power of making a difference between categories.
	 * For first departure of the day, this function implements the following categorization (before 3.30pm, 3.30pm to 6pm, after 6pm)
	 * @return the departure hour category according the methodology developed 
	 */
	private int getWeekDayAverageLastDep() {
		// TODO Auto-generated method stub
		int counter = 0;
		double averageDepHour = 0;
		for(int i = 0; i < myData.get(UtilsSM.cardId).size(); i++){
			String date = myData.get(UtilsSM.date).get(i);
			double time = hourStrToDouble(myData.get(UtilsSM.time).get(i));
			if(isWeekDay(date)){
				if(isLast(date,time)){
					counter++;
					averageDepHour += hourStrToDouble(myData.get(UtilsSM.time).get(i));
				}
			}
		}
		averageDepHour = averageDepHour/counter;
		
		if(averageDepHour <= UtilsSM.eveningPeakHourStart){
			return 0;
		}
		else if(averageDepHour < UtilsSM.eveningPeakHourEnd){
			return 1;
		}
		else if(averageDepHour >= UtilsSM.eveningPeakHourEnd){
			return 2;
		}
		else{
			System.out.println("--error affecting departure hours");
			return 10;
		}
	}
	
	/**
	 * This function identifies activities out of boarding time.
	 * @param timeThreshold in minutes, is the time limit between two boardings to consider it as an activity.
	 * @return the number of activities
	 */
	public int getWeekDayAverageActivityCount(double timeThreshold){
		
		HashMap<String, ArrayList<Double>> boardingTimes = new HashMap<String, ArrayList<Double>>();
		for(int i = 0; i < myData.get(UtilsSM.cardId).size();i++){
			String currDate = myData.get(UtilsSM.date).get(i);
			Double time = hourStrToDouble(myData.get(UtilsSM.time).get(i));
			if(isWeekDay(currDate)){
				if(!boardingTimes.containsKey(currDate)){
					boardingTimes.put(currDate, new ArrayList<Double>());
					boardingTimes.get(currDate).add(time);
				}
				else{
					boardingTimes.get(currDate).add(time);
				}
			}
		}
		int nAct = 0;
		int dayCounter = 0;
		for(String date: boardingTimes.keySet()){
			if(!boardingTimes.get(date).isEmpty()){
				dayCounter++;			
				Collections.sort(boardingTimes.get(date));
				if(boardingTimes.get(date).size() == 1){
					nAct++;
				}
				else{
					for(int j = 0; j < boardingTimes.get(date).size()-1; j++){
						double timeInterval = boardingTimes.get(date).get(j+1) - boardingTimes.get(date).get(j);
						if(timeInterval >= timeThreshold){
							nAct++;
						}
					}
				}
			}
			
		}
		
		double avgNAct = nAct/dayCounter;
		int answer = (int) Math.round(avgNAct);
		if(answer<3){return answer;}
		else{return 3;}
	}
	
	
	public int getWeekDayAveragePtFidelity(double distanceThreshold){
		int counterTripLegs = 0;
		int counterNonPt = 0;
		for(int i = 0; i < myData.get(UtilsSM.cardId).size();i++){
			String currDate = myData.get(UtilsSM.date).get(i);
			if(isWeekDay(currDate)){
				int stationId = Integer.parseInt(myData.get(UtilsSM.stationId).get(i));
				int nextStationId = Integer.parseInt(myData.get(UtilsSM.nextStationId).get(i));
				if(nextStationId != 0){
					GTFSStop gTFSStop = PublicTransitSystem.myStops.get(stationId);
					GTFSStop nextStation = PublicTransitSystem.myStops.get(nextStationId);
					gTFSStop.getDistance(nextStation);
					if(gTFSStop.getDistance(nextStation)>distanceThreshold){
						counterNonPt ++;
						counterTripLegs ++;
					}
				}
				else{
					counterNonPt++;
					counterTripLegs++;
				}
				counterTripLegs++;
			}
		}
		if(counterTripLegs != 0){
			double ptFidelity = 1 - counterNonPt/counterTripLegs;
			if(ptFidelity < 0.05){return 0;}
			else if(ptFidelity < 0.95){return 1;}
			else{return 2;}
		}
		else{
			return 0;
		}
		
	}
	
	private boolean isWeekDay(String currDate) {
		// TODO Auto-generated method stub
		return !Arrays.asList(UtilsSM.weekEnd).contains(currDate);
	}

	

	/**
	 * Assign to the smart card instance the identifier of the local area where the smart card holder did validate the most frequently.
	 */
	public void identifyMostFrequentStation(){
		HashMap<String, Integer> stationFrequencies = getStationFrequencies();
		String myStationId = new String();
		Integer freq = 0;
		for(String key: stationFrequencies.keySet()){
			if(stationFrequencies.get(key)>freq){
				myStationId = key;
				freq = stationFrequencies.get(key);
			}
		}
		stationId = myStationId;
	}

	private ArrayList<Object> getAverageFirstDepTime() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> stopFrequencies = new HashMap<String, Integer>();
		HashMap<String, Double> stopTime = new HashMap<String,Double>();
		
		for(int i = 0; i < myData.get(UtilsSM.cardId).size(); i++){
			String date = myData.get(UtilsSM.date).get(i);
			double time = hourStrToDouble(myData.get(UtilsSM.time).get(i));
			if(isFirst(date, time)){
				String stopId = myData.get(UtilsSM.stationId).get(i);
				if(stopFrequencies.containsKey(stopId)){
					int fr = stopFrequencies.get(stopId) + 1;
					double t = stopFrequencies.get(stopId) + time;
					stopFrequencies.put(stopId, fr);
					stopTime.put(stopId, t);
				}
				else{
					stopFrequencies.put(stopId, 1);
					stopTime.put(stopId, time);
				}
			}
		}
		
		String myStationId = new String();
		Integer freq = 0;
		for(String key: stopFrequencies.keySet()){
			if(stopFrequencies.get(key)>freq){
				myStationId = key;
				freq = stopFrequencies.get(key);
			}
		}
		
		double avgTime = stopTime.get(myStationId);
		avgTime = avgTime/stopTime.size();
		ArrayList<Object> answer = new ArrayList<Object>();
		answer.add(myStationId);
		answer.add(avgTime);
		return answer;
	}
	
	private String getMostFrequentLastStation() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> stopFrequencies = new HashMap<String, Integer>();
		
		for(int i = 0; i < myData.get(UtilsSM.cardId).size(); i++){
			String date = myData.get(UtilsSM.date).get(i);
			double time = hourStrToDouble(myData.get(UtilsSM.time).get(i));
			if(isLast(date, time)){
				String stopId = myData.get(UtilsSM.destStationId).get(i);
				if(stopFrequencies.containsKey(stopId) && !stopId.equals("0")){
					int fr = stopFrequencies.get(stopId) + 1;
					stopFrequencies.put(stopId, fr);
				}
				else{
					stopFrequencies.put(stopId, 1);
				}
			}
		}
		
		String myStationId = new String();
		Integer freq = 0;
		for(String key: stopFrequencies.keySet()){
			if(stopFrequencies.get(key)>freq){
				myStationId = key;
				freq = stopFrequencies.get(key);
			}
		}
		
		return myStationId;
	}

	/**
	 * This function returns a HashMap those key set is constituted of all the zone id that the smart card holder validated in for the first trip of each day.
	 * The value is the number of time he validated in in the zone.
	 * @return a HashMap which keyset is the local zone identifier and the value is the count of time the first validation of day was made in this specific local area.
	 */
	public HashMap<String,Integer> getStationFrequencies() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> stopFrequencies = new HashMap<String, Integer>();
		for(int i = 0; i < myData.get(UtilsSM.cardId).size(); i++){
			String date = myData.get(UtilsSM.date).get(i);
			double time = hourStrToDouble(myData.get(UtilsSM.time).get(i));
			if(isFirst(date, time)){
				String stopId = myData.get(UtilsSM.stationId).get(i);
				if(stopFrequencies.containsKey(stopId)){
					int fr = stopFrequencies.get(stopId) + 1;
					stopFrequencies.put(stopId, fr);
				}
				else{
					stopFrequencies.put(stopId, 1);
				}
			}
		}
		return stopFrequencies;
	}
	
	public void tagFirstTransaction(){
		myData.put(UtilsSM.firstTrans, new ArrayList<String>());	
		for(int i = 0; i < myData.get(UtilsSM.cardId).size(); i++){
			String date = myData.get(UtilsSM.date).get(i);
			double time = hourStrToDouble(myData.get(UtilsSM.time).get(i));
			if(isFirst(date,time)){
				myData.get(UtilsSM.firstTrans).add(UtilsSM.isFirst);
			}
			else{
				myData.get(UtilsSM.firstTrans).add(UtilsSM.isNotFirst);
			}
		}
	}
	
	public boolean isFirst(String date, double time){
		for(int j = 0; j < myData.get(UtilsSM.cardId).size(); j++){
			String date2 = myData.get(UtilsSM.date).get(j);
			double time2 = hourStrToDouble(myData.get(UtilsSM.time).get(j));
			if(date.equals(date2)){
				if(time2 < time){
					return false;
				}
			}
		}
		return true;
	}
	
	public void tagLastTransaction(){
		myData.put(UtilsSM.lastTrans, new ArrayList<String>());	
		for(int i = 0; i < myData.get(UtilsSM.cardId).size(); i++){
			String date = myData.get(UtilsSM.date).get(i);
			double time = hourStrToDouble(myData.get(UtilsSM.time).get(i));
			if(isLast(date,time)){
				myData.get(UtilsSM.lastTrans).add(UtilsSM.isLast);
			}
			else{
				myData.get(UtilsSM.lastTrans).add(UtilsSM.isNotLast);
			}
		}
	}
	
	public boolean isLast(String date, double time){
		for(int j = 0; j < myData.get(UtilsSM.cardId).size(); j++){
			String date2 = myData.get(UtilsSM.date).get(j);
			double time2 = hourStrToDouble(myData.get(UtilsSM.time).get(j));
			if(date.equals(date2)){
				if(time2 > time){
					return false;
				}
			}
		}
		return true;
	}
	
	private double hourStrToDouble(String time) {
		// TODO Auto-generated method stub
		
		time.trim();
		double hour = 0;
		if(time.length()==4){
			hour = 60 * Double.parseDouble(time.substring(0, 2)) + Double.parseDouble(time.substring(2,4));
		}
		else if(time.length() == 3){
			hour = 60 * Double.parseDouble(Character.toString(time.charAt(0))) + Double.parseDouble(time.substring(1,2));
		}
		return hour; //here, hour is in minutes
	}

	public void identifyLivingStation() {
		// TODO Auto-generated method stub
		
		int nAct = getDailyBoardings();
		
		ArrayList<Object> firstDepTime = getAverageFirstDepTime();
		String station = (String)firstDepTime.get(0);
		double depTime = (double)firstDepTime.get(1);
		
		if(nAct <= 1){
			if(depTime < 720 ){
				stationId = station;
			}
			else{
				stationId = getMostFrequentLastStation();
			}
		}
		else{
			stationId = station;
		}
		
	}

	private int getDailyBoardings() {
		// TODO Auto-generated method stub
		int dayCount = 0;
		
		
		for(int j = 0; j < myData.get(UtilsSM.cardId).size(); j++){
			String date2 = myData.get(UtilsSM.date).get(j);
			double time2 = hourStrToDouble(myData.get(UtilsSM.time).get(j));
			if(isFirst(date2,time2)){
				dayCount++;
			}
		}
		return myData.size()/dayCount;
	}

	public void inferDestinations() {
		// TODO Auto-generated method stub
		ArrayList<String> myDates = getDates();
		for(String curDate: myDates){
			inferDestination(curDate);
		}
	}

	private void inferDestination(String curDate) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> dailyData = getOrderedDailyTransaction(curDate);
		for(int i = 0; i < dailyData.size();i++){
			HashMap<String,String> curTapIn = dailyData.get(i);
			GTFSStop alightingStop;
			
			//Treat regular case: when there is a following record the same day
			if(i != dailyData.size()-1){
				HashMap<String, String> nextTapIn = dailyData.get(i+1);
				double distMin = Double.POSITIVE_INFINITY;
				
				
				String curDirectionId = curTapIn.get(UtilsSM.directionId);
				GTFSRoute curRoute = PublicTransitSystem.myRoutes.get(curTapIn.get(UtilsSM.routeId));
				GTFSStop curStop = PublicTransitSystem.myStops.get(curTapIn.get(UtilsSM.stopId));
				GTFSStop nextStop = PublicTransitSystem.myStops.get(nextTapIn.get(UtilsSM.stopId));
				ArrayList<GTFSStop> curVanishingRoute = curRoute.getVanishingRoute(curStop, curDirectionId);
				for(GTFSStop s:curVanishingRoute){
					double dist = s.getDistance(nextStop);
					if(dist < distMin){
						distMin = dist;
						alightingStop = s;
					}
				}
			}
			else if()
		}
		
	}

	private ArrayList<HashMap<String, String>> getOrderedDailyTransaction(String curDate) {
		// TODO Auto-generated method stub
		HashMap<Integer, HashMap<String, String>> tempData = new HashMap<Integer, HashMap<String, String>>();
		ArrayList<HashMap<String,String>> dailyData = new ArrayList<HashMap<String,String>>();
		
		for(int i = 0; i < myData.get(UtilsSM.date).size();i++){
			if(curDate.equals(myData.get(UtilsSM.date).get(i))){
				int curTime = Integer.parseInt(myData.get(UtilsSM.time).get(i));
				HashMap<String,String> curData = new HashMap<String,String>();
				for(String str: myData.keySet()){
					curData.put(str, myData.get(str).get(i));
				}
				tempData.put(curTime, curData);
			}
		}
		ArrayList<Integer> sortedTimes = new ArrayList<Integer>();
		for(int i: tempData.keySet()){
			sortedTimes.add(i);
		}
		sortedTimes.sort(null);
		
		for(int i: sortedTimes){
			HashMap<String,String> curData = tempData.get(i);
			dailyData.add(curData);
		}
		return dailyData;
	}

	private ArrayList<String> getDates() {
		// TODO Auto-generated method stub
		ArrayList<String> dates = new ArrayList<String>();
		for(int i = 0; i < myData.get(UtilsSM.date).size();i++){
			String curDate = myData.get(UtilsSM.date).get(i);
			if(!dates.contains(curDate)){
				dates.add(curDate);
			}
		}
		return dates;
	}
}
