package designmode.abstractfactory;

public class BmwSportCar extends BmwCar{  
	
	public BmwSportCar(String car){
		super();
		setName(car);
	}
    @Override
	public void drive(){
        System.out.println(this.getName()+"----BmwSportCar-----------------------");  
    }  
} 