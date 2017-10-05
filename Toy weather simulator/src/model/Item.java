
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Item implements ItemLine{

	private static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat(
			"dd/MM/yy");

	private String station;
	private float latitude;
	private float longitude;
	private int elevation;
	private ItemUnit unit;
	
	public String getStation() {
		return station;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public int getElevation() {
		return elevation;
	}

	public ItemUnit getUnit() {
		return unit;
	}



	public void setStation(String station) {
		this.station = station;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public void setUnit(ItemUnit unit) {
		this.unit = unit;
	}




	@Override
	public String toString() {
		return "Item [station=" + station + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", elevation=" + elevation]";
	}


}
