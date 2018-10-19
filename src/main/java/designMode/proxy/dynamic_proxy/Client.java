package designMode.proxy.dynamic_proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
	public static void main1(String[] args){
		AbstractSubject subject = new AbstractSubjectImpl();
		SubjectHandler handler = new SubjectHandler(subject);
		AbstractSubject proxy = (AbstractSubject)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{AbstractSubject.class}, handler);
		proxy.sayCommon();
	}
	
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, Exception{
		TestClass testClass = new TestClass();
		Class<?> test = testClass.getClass();
		Field[] fields = test.getDeclaredFields();
		for(Field field : fields){	//打印该类的所有属性
			System.out.println("type:" + field.getGenericType());
			if("java.lang.String".equals(field.getGenericType().getTypeName())){
				System.out.println(field.getGenericType().getTypeName() + "---->yes");
			}
			Method method = (Method)test.getDeclaredMethod("get" + getMethodName(field.getName()));
			Object val = method.invoke(testClass);// 调用getter方法获取属性值
		     if (val != null && (val instanceof String)) {
		      System.out.println("name:"+field.getName() +"|value:" + val +"::::::" + field.getGenericType());
		     }
			
		}
	}
	private static String getMethodName(String fildeName) throws Exception{
		  byte[] items = fildeName.getBytes();
		  items[0] = (byte) ((char) items[0] - 'a' + 'A');
		  return new String(items);
		 }
}
