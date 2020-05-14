package ie.cct.SBS19010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//this is the controller class, the following annotation tells spring this is a controller
@RestController
public class SBS19010Controller {

	private List<Animal> animals;

	public SBS19010Controller() {
		animals = new ArrayList<Animal>();
		animals.add(new Animal("Cow", 300, 500));
		animals.add(new Animal("Pig", 100, 250));
		animals.add(new Animal("Chicken", 0.5, 5));
	}

	// this annotation connects the method to the HTTP resource
	@GetMapping("hello-world")
	public String hello() {
		return "hello";
	}

	// this annotation POST is for receiving data from the client, creating an instance
	// of an animal
	// the RequestBody annotation means that we will receive info from the body and
	// not the URL
	//eg { "name":"test"
//	       "weight" : "32"
	@PostMapping("add-animal")
	public List<Animal> addAnimal(@RequestBody Animal animal) {
		animals.add(animal);
		return animals;
	}

	// added request parameters here because the brief said Calculate the average
	// weight of each type of animal and one endpoint is sufficient, making the
	// request parameter necessary fulfills this
	//Endpoint 2
	@GetMapping("average-weight")
	public AverageWeightResponse averageWeight(@RequestParam(required = true) String animalType) {
		float avgWeight = 0;
		
		for (Animal animal : animals) {
			if (animal.getType().contentEquals(animalType)) {
			avgWeight += animal.getWeight();
			}
		}                           //number of animals of each type instead of total animals
		avgWeight = avgWeight / animals.size();
		// create an object to return JSON instead of double     TO DO!!!!!!!!!
		
		return new AverageWeightResponse(animalType, avgWeight);
//				"The average weight of the " + animalType + "/s is " + avgWeight + "kg";
		//return success response
	}

	
	//this counts each type of animal similar to 3 and 4
	//Endpoint 3
	@GetMapping("animal-type-quantity")
	public Map<String, Integer> getAnimalTypeQuantity() {
		Map<String, Integer> summary = new HashMap<String, Integer>();
		for (Animal animal : animals) {
			if (summary.get(animal.getType()) == null) {
				summary.put(animal.getType(), 1);
			} else {
				Integer count = summary.get(animal.getType());
				summary.put(animal.getType(), ++count);
			}
		}
		return summary;
	}
	
	
	//Endpoint 4
	@GetMapping ("farm-value")
	public double totalValueOfAnimals () {
		double valueOfAnimals = 0;
		for (Animal animal: animals) {
			valueOfAnimals += animal.getPrice();
		}
		return valueOfAnimals;
	}

	// using requestparam because the url by default doesnt need a parameter but we
	// will make it so
	//8080/farm-value
	//filters to animal of certain type
	//Endpoint 5
	@GetMapping("user-value")
	public double valueOfAnimalType(@RequestParam(required = true) String animalType) {
		// this currently only returns the total price of one kind of animal,need to be
		// all animals
		double valueOfAnimalType = 0;
		for (Animal animal : animals) {
			if (animal.getType().contentEquals(animalType)) {
				valueOfAnimalType += animal.getPrice();
			}
		}
		if (valueOfAnimalType == 0) {

			throw new RuntimeException("Item not found");
		}

		return valueOfAnimalType;
	}
	

}
