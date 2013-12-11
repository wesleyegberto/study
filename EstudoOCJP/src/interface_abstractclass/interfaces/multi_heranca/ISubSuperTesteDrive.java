package interface_abstractclass.interfaces.multi_heranca;

public class ISubSuperTesteDrive implements ISubSuper {
	public String getMsg1() {
		return msg1;
	}

	public String getMsg2() {
		return msg2;
	}

	public String get2Msg() {
		return msg;
	}

	public static void main(String[] args) {
		ISubSuperTesteDrive t = new ISubSuperTesteDrive();

		System.out.println(t.getMsg1());

		System.out.println(t.getMsg2());

		System.out.println(t.get2Msg());
	}
}
