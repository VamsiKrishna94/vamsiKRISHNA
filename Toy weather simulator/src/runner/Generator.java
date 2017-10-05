

package runner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import com.interview.model.FeatureDetail;
import com.interview.model.ItemUnit;
import com.interview.utils.DatetimeUtils;
import com.interview.utils.HumidityUtils;
import com.interview.utils.PressureUtils;
import com.interview.utils.RadomRange;
import com.interview.utils.RainfallUtils;

public class Generator {
	WeatherModel<?> baseModel;
	Alerts alerts;
	long globalClosedUseByTime = -1;

	public Generator(WeatherModel<?> model, Alerts alerts) {
		this.baseModel = model;
		this.alerts = alerts;
	}

	public boolean generateData(String startdate, int duration, String path) {
		String dateString = startdate;
		Calendar currentDate = DatetimeUtils.getCalendar(dateString);
		File file = new File(path);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error on create file:");
				e.printStackTrace();
				System.exit(1);
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < duration; i++) {
				int currentMonth = currentDate.get(Calendar.MONTH) + 1;
				int dayOfMonth = 30;
				int currentDay = currentDate.get(Calendar.DATE);

				for (int j = 0; j < 3; j++) {
					// get Temperature
					for (Entry<String, Map<Integer, Map<ItemUnit, FeatureDetail>>> entry : baseModel
							.getModel().entrySet()) {
						Map<ItemUnit, FeatureDetail> value = entry.getValue()
								.get(currentMonth);
						int dailyMax, dailyMin, temperature = 0;
						String time = "";
						
						}
						if (j == 0) {
							temperature = dailyMin;
							time = "T01:22:" + RadomRange.randInt(10, 30) + "Z";
						} else if (j == 1) {
							temperature = dailyMax;
							time = "T12:50:" + RadomRange.randInt(30, 45) + "Z";
						} else if (j == 2) {
							temperature = dailyMin + (dailyMax - dailyMin) / 2;
							time = "T18:22:" + RadomRange.randInt(45, 59) + "Z";
						}
					
						String tempStr = String.format("%.1f", temp).toString();
						if (temp > 0)
							tempStr = "+" + tempStr;
						String conditions = RainfallUtils.checkCondition(
								currentMonth, temp);
						bw.write((new StringBuffer())
								.append(entry.getKey())
								.append("|")
								.append(DatetimeUtils
										.getDateStringFromCalendar(currentDate))
								.append(time).append("|").append(conditions)
								.append("|").append(tempStr).append("|")
								.append(PressureUtils.getPressure())
								.append("|")
								.append(HumidityUtils.getHumidity(conditions))
								.toString());
						bw.newLine();
					}
				}
				currentDate.add(Calendar.DATE, 1);
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Error on write file");
			e.printStackTrace();
			System.exit(1);
		}
		return true;
	}

	public WeatherModel<?> getWeatherModel() {
		return baseModel;
	}

	public void setWeatherModel(WeatherModel<?> model) {
		this.baseModel = model;
	}

	public Alerts getAlerts() {
		return alerts;
	}

	public void setAlerts(Alerts alerts) {
		this.alerts = alerts;
	}

}
