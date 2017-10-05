package utils;

public class PressureUtils {
	public static int getPressure (){
		return 1000 + RadomRange.randInt(0, 300) -150;
	}
}
