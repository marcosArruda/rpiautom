package br.com.rpiautom.serial.arduino;

import br.com.rpiautom.serial.arduino.listeners.MySerialDataListener;
import br.com.rpiautom.serial.protocols.DefaultProtocol;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

public class Arduino {
	private Serial serial;
	private final int num;
	private MySerialDataListener myListener = new MySerialDataListener();

	public Arduino(int arduinoNum) {
		serial = SerialFactory.createInstance();
		this.num = arduinoNum;
		serial.addListener(myListener);
	}

	public synchronized String applyAction(ArduinoAction action) {
		return applyAction(action, "_");
	}

	public synchronized String applyAction(ArduinoAction action, String message) {

		if (serial.isClosed() || serial.isShutdown()) {
			try{
				serial.open("/dev/ttyUSB" + num, 9600);
			}catch (SerialPortException ex) {
				System.out.println();
				return " ==>> SERIAL SETUP FAILED : " + ex.getMessage();
			}
		}
		myListener.nullData();
		try{
			serial.write(buildMessage(action, message));
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
			return " ==>> SERIAL SETUP SUCCESS BUT...: " + ex.getMessage();
		}
		waitResponse(myListener);
		return myListener.getLastData();
	}

	private String buildMessage(ArduinoAction action, String message) {
		StringBuffer m = new StringBuffer();
		m.append(DefaultProtocol.ACTION).append(action.getValue()).append(DefaultProtocol.END)
		.append(DefaultProtocol.MESSAGE).append(message).append(DefaultProtocol.END);
		return m.toString(); //ex: ACT:2;MSG:BLA BLA BLA;
	}

	private void waitResponse(MySerialDataListener listener) {
		int count = 0;
		try {
			
			for (;;) {
				count++;
				Thread.sleep(2000);
				if(listener.hasData() || count > 3)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}