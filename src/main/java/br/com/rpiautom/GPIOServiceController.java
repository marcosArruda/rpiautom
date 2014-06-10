package br.com.rpiautom;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

/**
 * Handles requests to the gpio. /gpio/led-on.html /gpio/led-off.html
 */
@Controller
@RequestMapping("/gpio")
public class GPIOServiceController {
	// create gpio controller instance
	// TODO: Injetar isso.

	static final GpioController gpio = GpioFactory.getInstance();

	@RequestMapping(value = "/led-on", method = RequestMethod.GET)
	@ResponseBody
	public String ledOn() {
		// explicitly set a state on the pin object
		// myLed.setState(PinState.HIGH);
		// use convenience wrapper method to set state on the pin object
		// myLed.low();
		// myLed.pulse(3000);
		// use toggle method to apply inverse state on the pin object
		// myLed.toggle();
		// use pulse method to set the pin to the HIGH state for
		// an explicit length of time in milliseconds
		// myLed.pulse(1000);
		// import com.pi4j.wiringpi.Serial;
		// Serial.serialOpen(arg0, arg1)
		// SerialFactory.createInstance().open(arg0, arg1);
		GpioPinDigitalOutput myLed = getLed();
		myLed.high();
		return "led ON";
	}

	@RequestMapping(value = "/led-off", method = RequestMethod.GET)
	@ResponseBody
	public String ledOff() {
		GpioPinDigitalOutput myLed = getLed();
		myLed.low();
		return "led OFF";
	}
	
	@RequestMapping(value = "/arduino0", method = RequestMethod.GET)
	@ResponseBody
	public String getArduino0Response() {
		return DataStatic.lastArduinoResponse;
	}

	@RequestMapping(value = "/hello-arduino0", method = RequestMethod.GET)
	@ResponseBody
	public String sendHelloToArduino0() {

		final Serial serial = SerialFactory.createInstance();

		// create and register the serial data listener
		serial.addListener(new SerialDataListener() {
			@Override
			public void dataReceived(SerialDataEvent event) {
				// print out the data received to the console
				System.out.print(event.getData());
				DataStatic.lastArduinoResponse = event.getData();
				ledOn();
			}
		});

		try {
			serial.open("/dev/ttyUSB0", 38400);
			try {
				serial.write("Ola Arduino");
			} catch (IllegalStateException ex) {
				ex.printStackTrace();
				return " ==>> SERIAL SETUP SUCCESS BUT...: " + ex.getMessage();
			}
		} catch (SerialPortException ex) {
			System.out.println();
			return " ==>> SERIAL SETUP FAILED : " + ex.getMessage();
		}
		return " ==>> SERIAL SETUP SUCCESS AND MESSAGE SEND!";
	}

	/**
	 * RaspiPin.GPIO_05 = pin 18 GPIO_24
	 */
	private GpioPinDigitalOutput getLed() {
		Collection<GpioPin> gpioPins = gpio.getProvisionedPins();
		GpioPinDigitalOutput myLed;
		if (gpioPins.isEmpty()) {
			myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "My LED5");
		} else {
			myLed = (GpioPinDigitalOutput) gpioPins.iterator().next();
		}
		return myLed;
	}
}
