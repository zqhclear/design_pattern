package designMode.adapt.adapt_class;

public class Adapter extends AdaptTragetee implements Traget {
	@Override
	public void doCommon() {
		super.adaptMethod();
	}
}
