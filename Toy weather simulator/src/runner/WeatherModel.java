
package runner;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.interview.model.FeatureDetail;
import com.interview.model.ItemLine;
import com.interview.reader.ItemReader;
import com.interview.model.ItemUnit;

public class WeatherModel<T extends ItemLine> {
	
	Map<String, Map<Integer, Map<ItemUnit, FeatureDetail>>> itemsModel = new HashMap<>();

	private final ItemReader<T> itemReader;

	public WeatherModel(ItemReader<T> itemReader) throws ParseException {
		this.itemReader = itemReader;
		load();
	}

	
	public void load() {

		T line;

		// Map<String,Map<Integer,Map<ItemUnit,Integer>>> Map<Station,
		// Map<months, Map<features,value>>>
		while ((line = readNextLine()) != null) {
			Map<Integer, Map<ItemUnit, FeatureDetail>> stationMap = null;
			// Integer value range from 1-12,
			Map<ItemUnit, FeatureDetail> featureMap = null; 
			String key = line.getStation() + "|" + line.getLatitude() + ","
					+ line.getLongitude() + "," + line.getElevation();
			if (!itemsModel.containsKey(key)) {
				itemsModel.put(key,
						new HashMap<Integer, Map<ItemUnit, FeatureDetail>>());
			}
			stationMap = itemsModel.get(key);


		}
	}

	
	public T readNextLine() {
		try {
			return itemReader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Read line error", e);
		}
	}

	public Map<String, Map<Integer, Map<ItemUnit, FeatureDetail>>> getModel() {
		return itemsModel;
	}
}
