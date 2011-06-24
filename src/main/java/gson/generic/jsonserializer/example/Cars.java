package gson.generic.jsonserializer.example;

import java.util.Collection;

public final class Cars<T extends AbstractCar> {
	
	private CarType carType;
	private Collection<T> cars;
	
	public Cars(Class<T> classOfT){
			try {
				T t = classOfT.newInstance();
				if (t instanceof FossilCar) {
					carType = CarType.fossil;
				} else if (t instanceof ElectricCar) {
					carType = CarType.electric;
				}
			} catch (InstantiationException e) {
				throw new IllegalArgumentException("STOP. Hammer Time! :)~");
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException("STOP. Hammer Time! :)~");
			}
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public Collection<T> getCars() {
		return cars;
	}

	public void setCars(Collection<T> cars) {
		this.cars = cars;
	}

}
