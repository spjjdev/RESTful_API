package ie.cct.SBS19010;

public class Animal {

	private String type;
	private double weight;
	private double price;
	
	//Without this default constructor spring will not create the object through JSON 
	//because it wont use the overloaded constructor below.This allows spring to build an empty object
	public Animal () {}
	
	public Animal(String type, double weight, double price) {
		super();
		this.type = type;
		this.weight = weight;
		this.price = price;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
