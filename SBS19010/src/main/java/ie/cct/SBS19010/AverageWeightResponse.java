package ie.cct.SBS19010;

public class AverageWeightResponse {

	String type;
	Double avgWeight;

	AverageWeightResponse() {
	}

	public AverageWeightResponse(String type, Double avgWeight) {
		super();
		this.type = type;
		this.avgWeight = avgWeight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAvgWeight() {
		return avgWeight;
	}

	public void setAvgWeight(Double avgWeight) {
		this.avgWeight = avgWeight;
	}

}
