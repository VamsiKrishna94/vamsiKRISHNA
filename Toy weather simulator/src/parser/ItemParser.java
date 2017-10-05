
package parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.interview.model.Item;
import com.interview.model.ItemUnit;


public class ItemParser implements LineParser<Item> {
	private final static Logger LOGGER = Logger.getLogger(ItemParser.class
			.getName());
	public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat(
			"dd/MM/yy");
	static Date todayDate = null;

	public ItemParser() throws Exception {
		todayDate = SHORT_DATE_FORMAT.parse(SHORT_DATE_FORMAT
				.format(new Date()));
	}

	public Item parseLine(String line) {
		final List<String> parts = split(line);
		final int numberOfTokens = parts.size();
		if (numberOfTokens < 17) {
			return null;
		}
		final Item result = new Item();
		final String station = parts.get(0);
		try {
			final float latitude = Float.valueOf(parts.get(1));
			final ItemUnit unit = ItemUnit.getUnit(parts.get(4));
			if (unit == null)
				return null;

			result.setStation(station.toUpperCase());
			result.setLatitude(latitude);
			result.setLongitude(Float.valueOf(parts.get(2)));
			result.setElevation(Integer.valueOf(parts.get(3)));
			result.setUnit(unit);
	
		} catch (Exception e) {
			LOGGER.severe("One Item in csv file format is not valid. The name of this Item is "
					+ station + ":" + parts.get(2) + "/n " + e.getMessage());
			return null;
		}
		return result;
	}


}
