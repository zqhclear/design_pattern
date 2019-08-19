package designmode.visitor;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/19 10:38
 */
public class Example {
	public static void main(String[] args) {
		new MaterialWin();
	}
}

/**
 * 窗体类
 */
class MaterialWin extends JFrame implements ItemListener {
	private static final long serialVersionUID = 1L;
	JPanel CenterJP;
	/**
	 * 材料集对象
	 */
	SetMaterial os;
	/**
	 * 访问者对象
	 */
	Company visitor1, visitor2;
	String[] select;

	MaterialWin() {
		super("利用访问者模式设计艺术公司和造币公司");
		JRadioButton Art;
		JRadioButton mint;
		os = new SetMaterial();
		os.add(new Cuprum());
		os.add(new Paper());
		//艺术公司
		visitor1 = new ArtCompany();
		//造币公司
		visitor2 = new Mint();
		this.setBounds(10, 10, 750, 350);
		this.setResizable(false);
		CenterJP = new JPanel();
		this.add("Center", CenterJP);
		JPanel SouthJP = new JPanel();
		JLabel yl = new JLabel("原材料有：铜和纸，请选择生产公司：");
		Art = new JRadioButton("艺术公司", true);
		mint = new JRadioButton("造币公司");
		Art.addItemListener(this);
		mint.addItemListener(this);
		ButtonGroup group = new ButtonGroup();
		group.add(Art);
		group.add(mint);
		SouthJP.add(yl);
		SouthJP.add(Art);
		SouthJP.add(mint);
		this.add("South", SouthJP);
		//获取产品名
		select = (os.accept(visitor1)).split(" ");
		//显示产品
		showPicture(select[0], select[1]);
	}

	/**
	 * 显示图片
	 *
	 * @param Cuprum
	 * @param paper
	 */
	void showPicture(String Cuprum, String paper) {
		//清除面板内容
		CenterJP.removeAll();
		//刷新屏幕
		CenterJP.repaint();
		String FileName1 = "src/visitor/Picture/" + Cuprum + ".jpg";
		String FileName2 = "src/visitor/Picture/" + paper + ".jpg";
		JLabel lb = new JLabel(new ImageIcon(FileName1), JLabel.CENTER);
		JLabel rb = new JLabel(new ImageIcon(FileName2), JLabel.CENTER);
		CenterJP.add(lb);
		CenterJP.add(rb);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		JRadioButton jc = (JRadioButton) arg0.getSource();
		if (jc.isSelected()) {
			if (jc.getText() == "造币公司") {
				select = (os.accept(visitor2)).split(" ");
			} else {
				select = (os.accept(visitor1)).split(" ");
			}
			//显示选择的产品
			showPicture(select[0], select[1]);
		}
	}
}

/**
 * 抽象访问者:公司
 */
interface Company {
	String create(Paper element);

	String create(Cuprum element);
}

/**
 * 具体访问者：艺术公司
 */
class ArtCompany implements Company {
	@Override
	public String create(Paper element) {
		return "讲学图";
	}

	@Override
	public String create(Cuprum element) {
		return "朱熹铜像";
	}
}

/**
 * 具体访问者：造币公司
 */
class Mint implements Company {
	@Override
	public String create(Paper element) {
		return "纸币";
	}

	@Override
	public String create(Cuprum element) {
		return "铜币";
	}
}

/**
 * 抽象元素：材料
 */
interface Material {
	String accept(Company visitor);
}

/**
 * 具体元素：纸
 */
class Paper implements Material {
	public String accept(Company visitor) {
		return (visitor.create(this));
	}
}

/**
 * 对象结构角色:材料集
 */
class Cuprum implements Material {
	@Override
	public String accept(Company visitor) {
		return (visitor.create(this));
	}
}

/**
 * 对象结构角色:材料集
 */
class SetMaterial {
	private List<Material> list = new ArrayList<>();

	public String accept(Company visitor) {
		Iterator<Material> i = list.iterator();
		String tmp = "";
		while (i.hasNext()) {
			tmp += (i.next()).accept(visitor) + " ";
		}
		//返回某公司的作品集
		return tmp;
	}

	public void add(Material element) {
		list.add(element);
	}

	public void remove(Material element) {
		list.remove(element);
	}
}