package br.com.rpiautom;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homepage(Model model)
	{
		try{
			model.addAttribute("CurrPrincipal",
			SecurityContextHolder.getContext().getAuthentication().getName());
		}
		catch (Exception e)
		{
			model.addAttribute("CurrPrincipal",
					"-NAO LOGADO-");
		}
		return "home";
	}
	
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String homepage2(Model model)
	{
		try{
			model.addAttribute("CurrPrincipal",
			SecurityContextHolder.getContext().getAuthentication().getName());
		}
		catch (Exception e)
		{
			model.addAttribute("CurrPrincipal",
					"-NAO LOGADO-");
		}
		return "home2";
	}
}
