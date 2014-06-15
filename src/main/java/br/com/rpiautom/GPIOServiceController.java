package br.com.rpiautom;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.rpiautom.serial.arduino.Arduino;
import br.com.rpiautom.serial.arduino.ArduinoAction;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Handles requests to the gpio. /gpio/led-on.html /gpio/led-off.html
 */
@Controller
@RequestMapping("/gpio")
public class GPIOServiceController {
	@Autowired
	@Qualifier("gpioController")
	private GpioController gpio;
	
	@Autowired
	@Qualifier("arduino0")
	private Arduino arduino0;
	
	@Autowired
	@Qualifier("arduino1")
	private Arduino arduino1;
	
	@RequestMapping(value = "/test-led13-on", method = RequestMethod.GET)
	@ResponseBody
	public String turnLed13On() {
		return arduino0.applyAction(ArduinoAction.ON13, "liga 0!") + " " +
		arduino1.applyAction(ArduinoAction.ON13, "liga 1!");
	}
	
	@RequestMapping(value = "/test-led13-off", method = RequestMethod.GET)
	@ResponseBody
	public String turnLed13Off() {
		return arduino0.applyAction(ArduinoAction.OFF13, "desliga 0!") + " " +
				arduino1.applyAction(ArduinoAction.OFF13, "desliga 1!");
	}
	/**
	 * ====================================================================================================
	 * legacy resources
	 * ====================================================================================================
	 */
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

	/**
	 * =============================================================
	 * RaspiPin.GPIO_05 = pin 18 GPIO_24
	 * =============================================================
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
	/**
	 * ===========================================================================================
	 * END legacy
	 * ===========================================================================================
	 */
}
