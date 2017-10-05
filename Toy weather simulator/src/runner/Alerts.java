

package com.interview.casestudy.runner;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.interview.model.Alert;


public class Alerts {
	private final static Logger LOGGER = Logger.getLogger(Alerts.class
			.getName());
	List<Alert> alertList = new ArrayList<Alert>();
    int percentage = 0;

	public Alerts(String alertsFile) throws Exception {
		load(alertsFile);
	}

	public List<Alert> getAlerts() {
		return alertList;
	}

	private void load(String alertFile) throws Exception {

		try (InputStream is = new FileInputStream(alertFile);
				JsonReader rdr = Json.createReader(is)) {
			JsonObject jsonObject = rdr.readObject();
			JsonArray results = jsonObject.getJsonArray("information");
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				Alert alert = new Alert();
//				LOGGER.info("alert Object: " + result.toString());
				try {
					readAlert(result, alert);
					alertList.add(alert);
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			LOGGER.severe("Alerts format is not valid " + e.getMessage());
			throw e;
		}
	}

	private void readAlert(JsonObject jsonItems, Alert alert)
			throws Exception {
		String name;
		if (jsonItems.containsKey("name")) {
			name = jsonItems.getString("name");
			alert.setName(name.toLowerCase());
		}
		JsonArray alerts = jsonItems.getJsonArray("alert");
		for (JsonObject alertObj : alerts
				.getValuesAs(JsonObject.class)) {
				percentage = alertObj.getInt("temperaturechange");
		}

	}
	public int getPercentage (){
		return this.percentage;
	}
}
