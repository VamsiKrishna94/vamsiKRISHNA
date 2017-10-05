
package model;


public enum ItemUnit {
	 HIGHEST, LOWEST;

	public static ItemUnit getUnit(String str) {
		switch (str.toLowerCase()) {
		case "highest":
			return HIGHEST;
		case "lowest":
			return LOWEST;
		default:
			return null;
		}

	}
}
