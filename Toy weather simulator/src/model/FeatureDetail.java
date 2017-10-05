package model;

public class FeatureDetail {
	public FeatureDetail(){
	}
	public FeatureDetail(int dailyMax, int dailyMin){
		this.dailyMax = dailyMax;
		this.dailyMin = dailyMin;
	}
	public int getdailyMax() {
		return dailyMax;
	}
	public void setdailyMax(int dailyMax) {
		this.dailyMax = dailyMax;
	}

	public int getdailyMin() {
		return dailyMin;
	}
	public void setdailyMin(int dailyMin) {
		this.dailyMin = dailyMin;
	}
	private int dailyMax;
	private int dailyMin;

	
}
