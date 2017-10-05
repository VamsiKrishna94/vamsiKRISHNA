

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
			}
}
