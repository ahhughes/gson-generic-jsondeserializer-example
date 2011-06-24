package gson.generic.jsonserializer.example;

public class FossilCar extends AbstractCar {

	private String fuel;

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	@Override
	public String toString() {
		return "FossilCar [fuel=" + fuel + ", getId()=" + getId() + "]";
	}

}