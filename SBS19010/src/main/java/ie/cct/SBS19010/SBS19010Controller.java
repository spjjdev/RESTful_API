package ie.cct.SBS19010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// this annotation is for receiving data from the client, creating an instance
	// of an animal
	// the RequestBody annotation means that we will receive info from the body and
	// not the URL
	@PostMapping("add-animal")
	public List<Animal> addAnimal(@RequestBody Animal animal) {
		animals.add(animal);
		return animals;
	}

	@GetMapping("average-weight")
	public double averageWeight() {
		double avgWeight = 0;
		for (Animal animal : animals) {
			avgWeight += animal.getWeight();
		}
		avgWeight = avgWeight / animals.size();
		// create an object to return JSON instead of double
		return avgWeight;
	}

	@GetMapping("sellable-animals")
	public Map<String, Integer> getSellableAnimals() {
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

	// using requestparam because the url by default doesnt need a parameter but we
	// will make it so
	@GetMapping("farm-value")
	public double totalValueOfAnimals(@RequestParam(required = true) String animalType) {
		// this currently only returns the total price of one kind of animal,need to be
		// all animals
		double valueOfAnimals = 0;
		for (Animal animal : animals) {
			if (animal.getType().contentEquals(animalType)) {
				valueOfAnimals += animal.getPrice();
			}
		}
		if(valueOfAnimals == 0) {
			throw new RuntimeException ("There is no " + animalType + "/s on the farm");
		}
		return valueOfAnimals;
	}

}
