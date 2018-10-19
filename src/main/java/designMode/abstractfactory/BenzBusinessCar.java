package designMode.abstractfactory;

public class BenzBusinessCar extends BenzCar{
	public BenzBusinessCar(String car){
		super();
		this.setName(car);
	}
    public void drive(){  
        System.out.println(this.getName()+"----BenzBusinessCar-----------------------");  
    }
}  