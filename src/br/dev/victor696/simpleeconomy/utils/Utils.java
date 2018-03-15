package br.dev.victor696.simpleeconomy.utils;

public class Utils {
	
	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
