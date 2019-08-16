package designmode.component;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/16 15:21
 */
public class Leaf extends Component {

	public Leaf(String name) {
		super(name);
	}

	@Override
	public void add(Component component) {
		System.out.println("add component with leaf");
	}

	@Override
	public void remove(Component component) {
		System.out.println("remove component with leaf");
	}

	@Override
	public void display(int level) {
		System.out.println(getName() + level);
	}
}
