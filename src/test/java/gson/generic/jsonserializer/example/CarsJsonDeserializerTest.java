package gson.generic.jsonserializer.example;

import java.lang.reflect.Type;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CarsJsonDeserializerTest {
	
	private static final Logger log = Logger.getLogger(CarsJsonDeserializerTest.class);
	private static Gson gson;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GsonBuilder gsonBuilder = new GsonBuilder();
		//TODO register by class or type? I don't know!
		gsonBuilder.registerTypeAdapter(Cars.class, new CarsJsonDeserializer());
		gsonBuilder.setPrettyPrinting();
		gson = gsonBuilder.create();
	}

	@Test
	public void testElectricCars() {
		final String lithiumIon = "Lithium-Ion";
		final String nickleCadmium = "Nickle-Cadmium";
		final String jsonIn = "{ \"carType\": \""+CarType.electric.name()+"\", \"cars\" : [{ \"id\" : 1 , \"battery\" : \""+lithiumIon+"\" }, { \"id\" : 2 , \"battery\" : \""+nickleCadmium+"\" }]}";
		log.debug("jsonIn:\n\r"+jsonIn);
		//TODO, MUST we use type here? It's not type safe with the compiler and would prefer .class :'(
		final Type type = new TypeToken<Cars<ElectricCar>>(){}.getType();
		final Cars<ElectricCar> cars = gson.fromJson(jsonIn, type);
		final String jsonOut = gson.toJson(cars);
		log.debug("jsonOut:\n\r"+jsonOut);
		//Check the deserialized object...
		Assert.assertNotNull(cars);
		Assert.assertNotNull(cars.getCarType());
		Assert.assertEquals(cars.getCarType(), CarType.electric);
		Assert.assertNotNull(cars.getCars());
		Assert.assertTrue(cars.getCars().size()>0);
		for (ElectricCar electricCar : cars.getCars()){
			Assert.assertNotNull(electricCar);
			Assert.assertNotNull(electricCar.getId());
			Assert.assertTrue(electricCar.getId()> 0);
			Assert.assertTrue(lithiumIon.equals(electricCar.getBattery()) || nickleCadmium.equals(electricCar.getBattery()));
		}
		//Check the serialized output...
		Assert.assertTrue(jsonOut.contains(CarType.electric.name()));
		Assert.assertTrue(jsonOut.contains(lithiumIon));
		Assert.assertTrue(jsonOut.contains(nickleCadmium));
	}

	@Test
	public void testFossilCars() {
		final String petrol = "Petrol";
		final String diesel = "Diesel";
		final String jsonIn = "{ \"carType\": \""+CarType.fossil.name()+"\", \"cars\" : [{ \"id\" : 1 , \"fuel\" : \""+petrol+"\" }, { \"id\" : 2 , \"fuel\" : \""+diesel+"\" }]}";
		log.debug("jsonIn:\n\r"+jsonIn);
		//TODO, MUST we use type here? It's not type safe with the compiler and would prefer .class :'(
		final Type type = new TypeToken<Cars<FossilCar>>(){}.getType();
		final Cars<FossilCar> cars = gson.fromJson(jsonIn, type);
		final String jsonOut = gson.toJson(cars);
		log.debug("jsonOut:\n\r"+jsonOut);
		//Check the deserialized object...
		Assert.assertNotNull(cars);
		Assert.assertNotNull(cars.getCarType());
		Assert.assertEquals(cars.getCarType(), CarType.fossil);
		Assert.assertNotNull(cars.getCars());
		Assert.assertTrue(cars.getCars().size()>0);
		for (FossilCar fossilCar : cars.getCars()){
			Assert.assertNotNull(fossilCar);
			Assert.assertNotNull(fossilCar.getId());
			Assert.assertTrue(fossilCar.getId()> 0);
			Assert.assertTrue(petrol.equals(fossilCar.getFuel()) || diesel.equals(fossilCar.getFuel()));
		}
		//Check the serialized output...
		Assert.assertTrue(jsonOut.contains(CarType.fossil.name()));
		Assert.assertTrue(jsonOut.contains(petrol));
		Assert.assertTrue(jsonOut.contains(diesel));
	}	
	
}
