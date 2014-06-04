package br.com.rpiautom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/a")
public class PlaceController {
	@RequestMapping(value = "/sala", method = RequestMethod.GET)
	public String sala(Model model)
	{
		return "a/sala";
	}
	
	@RequestMapping(value = "/cozinha", method = RequestMethod.GET)
	public String cozinha(Model model)
	{
		return "a/cozinha";
	}
	
	@RequestMapping(value = "/escritorio", method = RequestMethod.GET)
	public String escritorio(Model model)
	{
		return "a/escritorio";
	}
	
	@RequestMapping(value = "/quarto-marcos", method = RequestMethod.GET)
	public String quartoMarcos(Model model)
	{
		return "a/quarto-marcos";
	}
	
	@RequestMapping(value = "/quarto-pais", method = RequestMethod.GET)
	public String quartoPais(Model model)
	{
		return "a/quarto-pais";
	}
	
	@RequestMapping(value = "/configuracoes", method = RequestMethod.GET)
	public String configuracoes(Model model)
	{
		return "a/configuracoes";
	}
}
