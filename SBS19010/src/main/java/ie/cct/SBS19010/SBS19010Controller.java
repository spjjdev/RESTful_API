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
		animals.add(new Animal("Cow", 300));
		animals.add(new Animal("Pig", 100));
		animals.add(new Animal("Chicken", 0.5));
		animals.add(new Animal("Cow", 300));
		animals.add(new Animal("Pig", 100));
		animals.add(new Animal("Chicken", 0.5));
	}

	// Endpoint 1: This adds an Animal object to the ArrayList 'animals', can be
	// tested by using Postman, posting a body of type JSON eg. {"type": "Cow",
	// "weight": 300.0} using the URL http://localhost:8080/add-animal
	@PostMapping("add-animal")
	public List<Animal> addAnimal(@RequestBody Animal animal) {
		animals.add(animal);
		return animals;
	}

	// Endpoint 2: This calculates the average weight of each type of animal when
	// the animal is specified in the URL eg.
	// http://localhost:8080/average-weight?animalType=Cow
	@GetMapping("average-weight")
	public AverageWeightResponse averageWeight(@RequestParam(required = true) String animalType) {
		double avgWeight = 0;
		ArrayList<Animal> type = new ArrayList<Animal>();
		for (Animal animal : animals) {
			if (animal.getType().contentEquals(animalType)) {
				type.add(animal);
				avgWeight += animal.getWeight();
			}
		}
		avgWeight = avgWeight / type.size();
		return new AverageWeightResponse(animalType, avgWeight);
	}

	// Endpoint 3: This returns the quantity of each type of animal in the farm
	// using the URL http://localhost:8080/animal-type-quantity
	@GetMapping("animal-type-quantity")
	public Map<String, Integer> getAnimalTypeQuantity() {
		Map<String, Integer> quantity = new HashMap<String, Integer>();
		for (Animal animal : animals) {
			if (quantity.get(animal.getType()) == null) {
				quantity.put(animal.getType(), 1);
			} else {
				Integer count = quantity.get(animal.getType());
				switch (animal.getType()) {
				case "Cow":
					if (animal.getWeight() >= 300) {
						quantity.put(animal.getType(), ++count);
					}
					break;
				case "Pig":
					if (animal.getWeight() >= 100) {
						quantity.put(animal.getType(), ++count);
					}
					break;
				case "Chicken":
					if (animal.getWeight() >= 0.5) {
						quantity.put(animal.getType(), ++count);
					}
					break;
				}
				
//				quantity.put(animal.getType(), ++count);
			}
		}
		return quantity;
	}

	// Endpoint 4: This calculates the total value of the farm with the
	// market value of each animal using the URL http://localhost:8080/farm-value
	@GetMapping("farm-value")
	public double totalValueOfAnimals() {
		double valueCow = 500;
		double valuePig = 250;
		double valueChicken = 5;
		double valueOfAnimals = 0;
		for (Animal animal : animals) {
			if (animal.getType().contains("Cow"))
				valueOfAnimals += valueCow;
			if (animal.getType().contains("Pig"))
				valueOfAnimals += valuePig;
			if (animal.getType().contains("Chicken"))
				valueOfAnimals += valueChicken;
		}
		return valueOfAnimals;
	}

	// Endpoint 5: This calculates the value of the farm with a user determined
	// value of each animal using the URL
	// http://localhost:8080/user-value?cowValue=1&pigValue=1&chickenValue=1
	@GetMapping("user-value")
	public Price userValueOfAnimals(@RequestParam(required = true) Double cowValue,
			@RequestParam(required = true) Double pigValue, @RequestParam(required = true) Double chickenValue) {
		double farmValue = 0;
		for (Animal animal : animals) {
			switch (animal.getType()) {
			case "Cow":
				if (animal.getWeight() >= 300) {
					farmValue += cowValue;
				}
				break;
			case "Pig":
				if (animal.getWeight() >= 100) {
					farmValue += pigValue;
				}
				break;
			case "Chicken":
				if (animal.getWeight() >= 0.5) {
					farmValue += chickenValue;
				}
				break;
			}
		}
		return new Price(farmValue);
	}

}
