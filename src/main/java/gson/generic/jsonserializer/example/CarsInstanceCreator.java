package gson.generic.jsonserializer.example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

public class CarsInstanceCreator implements InstanceCreator<Cars<?>>{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cars<?> createInstance(Type type) {
	    Type[] typeParameters = ((ParameterizedType)type).getActualTypeArguments();
	    Type carType = typeParameters[0]; // Cars<T> has only one parameterized type, hence [0]==T
	    Cars<?> cars = new Cars((Class)carType);
	    return cars;
	}
	
}
