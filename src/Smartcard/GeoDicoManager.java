/**
 * 
 */
package Smartcard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;


import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import ActivityChoiceModel.DataManager;
import ActivityChoiceModel.UtilsTS;

/**
 * @author Antoine
 *
 */
public class GeoDicoManager extends DataManager{
	
	public HashMap<Double,ArrayList<Integer>> getDico(String pathGeoDico) throws IOException{
		HashMap<Double,ArrayList<Integer>> myDico = new HashMap<Double,ArrayList<Integer>>();
		initialize(pathGeoDico);
		for(int i = 0; i < myData.get(UtilsSM.zoneId).size(); i++){
			double nextRecordZone = Double.parseDouble(myData.get(UtilsSM.zoneId).get(i));
			int newStation = (int) Double.parseDouble(myData.get(UtilsST.stationId).get(i));
			if(myDico.containsKey(nextRecordZone)){
				myDico.get(nextRecordZone).add(newStation);
			}
			else{
				myDico.put(nextRecordZone, new ArrayList<Integer>());
				myDico.get(nextRecordZone).add(newStation);
			}
		}
		return myDico;
	}
	
	public HashMap<String, ArrayList<String>> getDico(String pathGeographicZones, ArrayList<GTFSStop> stops) throws IOException{
		HashMap<String,ArrayList<String>> myDico = new HashMap<String,ArrayList<String>>();
		
		File file = new File(pathGeographicZones);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", file.toURI().toURL());
		
		DataStore dataStore = DataStoreFinder.getDataStore(map);
	    String typeName = dataStore.getTypeNames()[0];
	    
	    FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
	    Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM, 10,20,30,40)")

	    FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
	    try (FeatureIterator<SimpleFeature> features = collection.features()) {
	        while (features.hasNext()) {
	            SimpleFeature feature = features.next();
	            addCloseStops(feature, stops, myDico);
	            System.out.print(feature.getID());
	            System.out.print(": ");
	            System.out.println(feature.getDefaultGeometryProperty().getValue());
	        }
	    }
	    
		return myDico;
		
	}

	private void addCloseStops(SimpleFeature feature, ArrayList<GTFSStop> stops, HashMap<String, ArrayList<String>> myDico) {
		// TODO Auto-generated method stub
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		
		for(GTFSStop curStop: stops){
			Coordinate coord = new Coordinate(curStop.lon, curStop.lat);
			Point geographicStop = geometryFactory.createPoint(coord);
			boolean isWithin = ((Geometry) feature).isWithinDistance(geographicStop, UtilsSM.distanceThreshold);
			if(isWithin){
				String zoneId = UtilsSM.areaId;
				if(myDico.containsKey(feature.getAttribute(zoneId))){
					myDico.get(zoneId).add(curStop.myId);
				}
				else{
					myDico.put(zoneId, new ArrayList<String>());
					myDico.get(zoneId).add(curStop.myId);
				}
			}
		}
	}
	
}
