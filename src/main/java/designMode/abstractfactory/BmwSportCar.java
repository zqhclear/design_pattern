package designMode.abstractfactory;

public class BmwSportCar extends BmwCar{  
	
	public BmwSportCar(String car){
		super();
		setName(car);
	}
    public void drive(){  
        System.out.println(this.getName()+"----BmwSportCar-----------------------");  
    }  
} 