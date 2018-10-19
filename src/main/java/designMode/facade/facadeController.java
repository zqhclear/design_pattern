package designMode.facade;

public class facadeController {
		ModuleOne one = new ModuleOne();  
		ModuleTwo b = new ModuleTwo();  
		ModuleThree c = new ModuleThree();  
		
		public void sayOne(){
			one.methodOne();
		}
		
		public void sayTwo(){
			b.sayModuleTwo();
		}
		
		public void sayThree(){
			c.sayModuleTwo();
		}
}
