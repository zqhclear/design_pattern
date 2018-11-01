package collection;

import java.util.function.Function;

/**
 * @Description: 描述
 * @Author: zhongqionghua
 * @CreateDate: 2018/11/1 11:40
 */
public class FunctionTest {
	public static void main(String[] args) {
		User user = new User();
		user.setAge(20+"");
		user.setName("二蛋");
		System.out.println(operation(user, x -> addAge(user)));


	}



	static String operation(User user, Function<User, String> action) {
		return action.apply(user);
	}

	private static String addAge(User user){
		return user.getAge() + "age";
	}

	static class User{
		private String name;
		private String age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}
	}
}
