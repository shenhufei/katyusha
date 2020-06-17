package ifelse;

/**
 * 消减过多的if else 类。
 * 1.将每个if else 分支的的功能抽取出一个接口的；
 * 2.每一个分支执行的功能的方法名称必须是和 这个抽象出来的接口的名称是一样的；
 * 3.必须要使用预先处理的方式，所谓预先处理也就是（把对象提前new 出来，根据不同的分支状态，存储在Map集合中）
 * 4.根据传递的分支状态从Map集合中获取到相关的执行的类；
 * 5.获得到执行的类之后，再调用方法（这个方法也就是实现的抽象类中的方法）；
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200617
 */
public class TestMain {
    public static void main(String[] args) {
        PeopleMethodInterface man = FactoryHandlers.getPeopleMethodInterfaceObject("man");
        man.doMethod();
    }
}
