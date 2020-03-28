package ie.cct.SBS19010;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//this is the controller class, the following annotation tells spring this is a controller
@RestController
public class SBS19010Controller {
	
	

	//this annotation connects the method to the HTTP resource
	@GetMapping("hello-world")
	public String hello() {
		return "hello";
	}
	
	//this annotation is for receiving data from the client, creating an instance of an animal
	@PostMapping("add-animal")
	public String addAnimal() {
		return "test";
	}
}
