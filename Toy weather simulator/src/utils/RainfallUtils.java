package utils;

public class RainfallUtils {

	public  static String checkCondition( int month, double temperature){
		int randomInt = RadomRange.randInt(0, 100);
		boolean raining =false;
		if (month < 8 && temperature<=25)
			raining = true;
		else if (month > 8 && temperature<15)
			raining = true;
		if (raining) 
			if (temperature>0) return "Rain";
			else return "Snow";
		
		return "Sunny";
	}
}
