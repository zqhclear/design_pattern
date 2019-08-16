package designmode.abstractfactory;

public interface AbstractFactory {
	BenzCar createBenzCar(String car) throws Exception;
    
    BmwCar createBmwCar(String car) throws Exception;
}
