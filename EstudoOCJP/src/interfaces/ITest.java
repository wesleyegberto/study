package interfaces;

public abstract interface ITest {
	public void doStuff();

	public abstract void doDof();
}

interface ITestF extends ITest {
	public abstract void doBuf();
}

class T1 implements ITestF {
	ITest t;

	@Override
	public void doStuff() {

	}

	@Override
	public void doDof() {

	}

	@Override
	public void doBuf() {

	}

}

class T2 implements ITest {
	@Override
	public void doDof() {

	}

	@Override
	public void doStuff() {

	}

}
