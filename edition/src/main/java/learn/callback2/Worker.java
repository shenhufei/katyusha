package learn.callback2;

public class Worker {
	public void doSomething(Boss boss){
		System.out.println("员工接到指令");
		System.out.println("员工准备做回调");
		boss.doCallBack();
	}
	public void doSomething(){
		Boss boss = new Boss();
		System.out.println("员工接到指令");
		System.out.println("员工准备做回调");
		boss.doCallBack();
	}
}
