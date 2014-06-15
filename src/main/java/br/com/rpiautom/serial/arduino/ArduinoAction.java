package br.com.rpiautom.serial.arduino;

public enum ArduinoAction {
	ON13(1), OFF13(2), GET_STATUS(3), RF_MESSAGE(4);
	private int value;

	private ArduinoAction(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}
