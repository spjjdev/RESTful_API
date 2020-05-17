package ie.cct.SBS19010;

public class Animal {

	private String type;
	private double weight;

	// Without this default constructor spring will not create the object through
	// JSON
	// because it wont use the overloaded constructor below.This allows spring to
	// build an empty object
	public Animal() {
		super();
	}

	public Animal(String type, double weight) {
		super();
		this.type = type;
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
