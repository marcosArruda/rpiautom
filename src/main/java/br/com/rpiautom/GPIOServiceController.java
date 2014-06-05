package br.com.rpiautom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests to the gpio.
 */
@Controller
@RequestMapping("/gpio")
public class GPIOServiceController {
	
	// create gpio controller instance
	//TODO: Injetar isso.
	//final GpioController gpio = GpioFactory.getInstance();
	
	@RequestMapping(value = "/led-toggle", method = RequestMethod.GET)
	@ResponseBody
	public String activate() {
		// URI : /gpio/activate
		
		// provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
		//GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
		//                                                           "My LED");           // PIN FRIENDLY NAME (optional)
		 // explicitly set a state on the pin object
        //myLed.setState(PinState.HIGH);

        // use convenience wrapper method to set state on the pin object
        //myLed.low();
        //myLed.high();

        // use toggle method to apply inverse state on the pin object
        //myLed.toggle();

        // use pulse method to set the pin to the HIGH state for
        // an explicit length of time in milliseconds
        //myLed.pulse(1000);
		//import com.pi4j.wiringpi.Serial;
		//Serial.serialOpen(arg0, arg1)
        return "OK";
        
	}
}
