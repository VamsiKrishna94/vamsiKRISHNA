package utils;

public class HumidityUtils {
	public static int getHumidity( String condition){
		switch (condition) {
		case "Rain":
			return RadomRange.randInt(80, 97);
		case "Snow":
			return RadomRange.randInt(50, 70);
		default:
			return RadomRange.randInt(20, 40);
		}
	}
}
