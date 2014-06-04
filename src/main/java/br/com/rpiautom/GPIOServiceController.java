package br.com.rpiautom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.rpiautom.gpio.CommandExecutor;
import br.com.rpiautom.gpio.PinMapper;

/**
 * Handles requests for to the gpio.
 */
@Controller
@RequestMapping("/gpio")
public class GPIOServiceController {
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	@ResponseBody
	public String activate(@RequestParam("pin") int pin) {
		// URI : /gpio/activate?pin=67
		return CommandExecutor.execute("echo " + pin + " > /sys/class/gpio/export");
	}
	
	@RequestMapping(value = "/set-direction", method = RequestMethod.GET)
	@ResponseBody
	public String setDirection(@RequestParam("pin") int pin, @RequestParam("dir") String dir) {
		// URI : /gpio/set-direction?pin=67&dir=out
		String pinName = PinMapper.getPinName(pin);
		return CommandExecutor.execute("echo " + dir + " > /sys/class/gpio/" + pinName + "/direction");
	}
	
	@RequestMapping(value = "/set-value", method = RequestMethod.GET)
	@ResponseBody
	public String setValue(@RequestParam("pin") int pin, @RequestParam("val") int val) {
		// URI : /gpio/set-value?pin=67&val=1
		String pinName = PinMapper.getPinName(pin);
		return CommandExecutor.execute("echo " + val + " > /sys/class/gpio/" + pinName + "/value");
	}
	
	@RequestMapping(value = "/get-value", method = RequestMethod.GET)
	@ResponseBody
	public String readValue(@RequestParam("pin") int pin) {
		// URI : /gpio/get-value?pin=67
		String pinName = PinMapper.getPinName(pin);
		return CommandExecutor.execute("cat /sys/class/gpio/" + pinName + "/value");
	}
}
