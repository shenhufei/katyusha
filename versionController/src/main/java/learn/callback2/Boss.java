package learn.callback2;

public class Boss {
	private Worker work;
	public Boss(Worker work){
		this.work=work;
	}
	public Boss(){
	}
	public void tell(){
		System.out.println("boss发出做事指令");
		Worker worker = new Worker();
		worker.doSomething();
	}
	public void doCallBack(){
		System.out.println("准备执行回调");
		System.out.println("执行回调");
	}
}
