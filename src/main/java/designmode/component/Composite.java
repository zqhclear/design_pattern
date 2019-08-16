package designmode.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/16 15:22
 */
public class Composite extends Component {
	public Composite(String name) {
		super(name);
	}

	private List<Component> children = new ArrayList<>();

	@Override
	public void add(Component component) {
		children.add(component);
	}

	@Override
	public void remove(Component component) {
		children.remove(component);
	}

	@Override
	public void display(int level) {
		System.out.println(this.getName() + level);
		children.stream().forEach(component -> component.display(level));
	}
}
