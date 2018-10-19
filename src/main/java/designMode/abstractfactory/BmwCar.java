package designMode.abstractfactory;

public abstract class BmwCar{  
    private String name;  
    public BmwCar(){};
    public BmwCar(String name){
    	this.name = name;
    }
    public abstract void drive();  
      
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
}  