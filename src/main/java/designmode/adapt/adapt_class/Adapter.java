package designmode.adapt.adapt_class;

public class Adapter extends AdaptTargetee implements Traget {
	@Override
	public void doCommon() {
		super.adaptMethod();
	}
}
