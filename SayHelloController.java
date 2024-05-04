package com.balram.firstwebapp.firstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class SayHelloController {
	@RequestMapping("say-Hello")
	@ResponseBody
	public String sayHello() {
		return "helluo! what are you learning today?";
	}
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}

}
