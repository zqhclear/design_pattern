package designMode.abstractfactory;

public interface AbstractFactory {
	public abstract BenzCar createBenzCar(String car) throws Exception;  
    
    public abstract BmwCar createBmwCar(String car) throws Exception;  
}
