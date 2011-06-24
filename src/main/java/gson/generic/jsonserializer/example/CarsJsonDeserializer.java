package gson.generic.jsonserializer.example;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class CarsJsonDeserializer implements JsonDeserializer<Cars<?>> {
	
	private final Type electricCarsType = new TypeToken<Collection<ElectricCar>>(){}.getType();
	private final Type fossilCarsType = new TypeToken<Collection<FossilCar>>(){}.getType();

	public Cars<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx)
			throws JsonParseException {
		final CarType carType = getCarType(jsonElement);
		if (CarType.electric.equals(carType)){
			Cars<ElectricCar> electricCars = new Cars<ElectricCar>(ElectricCar.class);
			JsonElement carsJsonArray = jsonElement.getAsJsonObject().get("cars").getAsJsonArray();
			electricCars.setCars((Collection<ElectricCar>)ctx.deserialize(carsJsonArray, electricCarsType));
			return electricCars;
		} else if (CarType.electric.equals(carType)){
			Cars<FossilCar> fossilCars = new Cars<FossilCar>(FossilCar.class);
			JsonElement carsJsonArray = jsonElement.getAsJsonObject().get("cars").getAsJsonArray();
			fossilCars.setCars((Collection<FossilCar>)ctx.deserialize(carsJsonArray, fossilCarsType));			
			return fossilCars;			
		} else {
			throw new JsonParseException("Bad carType!!!!!");
		}
	}
	
	//Is there a cleaner way that this?
	private CarType getCarType(JsonElement jsonElement){
		if (jsonElement == null){
			return null;
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		if (jsonObject == null){
			return null;
		}
		JsonElement carTypeJsonElement = jsonObject.get("carType");
		if (carTypeJsonElement == null){
			return null;
		}
		String value = carTypeJsonElement.getAsString();
		return CarType.valueOf(value);
	}

}
