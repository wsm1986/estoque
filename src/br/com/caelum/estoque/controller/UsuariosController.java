package br.com.caelum.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class UsuariosController{
	
	@RequestMapping(value="/login")
	public String index(){
		return "usuario/login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(){
		System.out.println("Logout ---");
		return "usuario/login";
	}

}