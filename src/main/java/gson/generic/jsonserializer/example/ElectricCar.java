package gson.generic.jsonserializer.example;

public class ElectricCar extends AbstractCar {
	
	private String battery;

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	@Override
	public String toString() {
		return "ElectricCar [battery=" + battery + ", getId()=" + getId() + "]";
	}

}
