package learn.ObjectSize;

import org.apache.lucene.util.RamUsageEstimator;

public class TestObjectSize{
public static void main(String[] args) {
	String a = new String("aa");
	String b = a+"1212";
	//计算指定对象及其引用树上的所有对象的综合大小，单位字节
	long sizeOf  = RamUsageEstimator.sizeOf(a);

	//计算指定对象本身在堆空间的大小，单位字节
	long shallowSizeOf = RamUsageEstimator.shallowSizeOf(a);

	//计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB

	String  humanSizeOf= RamUsageEstimator.humanSizeOf(a);
	
	
	System.out.println("综合大小:"+sizeOf);
	System.out.println("堆空间的大小:"+shallowSizeOf);
	System.out.println("引用树上的所有对象的综合大小:"+humanSizeOf);
	}
 
}