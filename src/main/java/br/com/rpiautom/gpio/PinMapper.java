package br.com.rpiautom.gpio;

import java.util.ResourceBundle;

public class PinMapper {
	private static ResourceBundle bundle = ResourceBundle.getBundle("pins");

	public static String getPinName(int pin) {
		return bundle.getString(String.valueOf(pin));
	}
}
