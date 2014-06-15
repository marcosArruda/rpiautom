package br.com.rpiautom.serial.arduino.listeners;

import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;

public class MySerialDataListener implements SerialDataListener {
	private String lastData;
	@Override
	public void dataReceived(SerialDataEvent dataEvent) {
		this.lastData = dataEvent.getData();
	}
	
	public String getLastData() {
		return lastData;
	}
	
	public synchronized void nullData() {
		lastData = null;
	}
	
	public boolean hasData(){
		return lastData != null;
	}
}
