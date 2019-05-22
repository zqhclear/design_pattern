package designMode.decorator;

/**
 * 装饰器模式：
 */
public class Client {  
  public static void main(String[] args) {  
      Human person = new Person();  
      Decorator decorator = new Decorator_two(new Decorator_first(
              new Decorator_zero(person)));
      decorator.wearClothes();  
      decorator.walkToWhere();  
  }  
}  