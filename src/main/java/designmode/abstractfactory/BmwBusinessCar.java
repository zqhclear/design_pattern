package designmode.abstractfactory;

public class BmwBusinessCar extends BmwCar{  
    @Override
    public void drive(){
        System.out.println(this.getName()+"----BmwBusinessCar-----------------------");  
    }  
} 