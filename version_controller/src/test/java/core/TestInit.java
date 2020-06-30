package core;

import com.shenhufei.Katyusha.core.FilterMethodHandler;
import com.shenhufei.Katyusha.core.MethodMap;
import com.shenhufei.Katyusha.core.PathHandler;
import com.shenhufei.Katyusha.core.VersionHandler;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.utils.CollectionUtils;
import com.shenhufei.Katyusha.utils.FileUtils;
import org.reflections.Reflections;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200630
 */
public class TestInit {

    public static Map<String, Methods> mapMethod = new ConcurrentHashMap<>();

    public static List<String> listString;
    public static List<String> getListString() {
        return listString;
    }

    public static void setListString(List<String> listString) {
        VersionHandler.listString = listString;
    }

    // TODO 此处需要将测试的main方法全部去掉
    public static void main(String[] args) {
        // 获取一个接口下所有的实现类
        Reflections reflections = new Reflections("learn.test");
        Set<Class<? extends PathHandler>> classes = reflections.getSubTypesOf(PathHandler.class);
        for (Class<?> clazz : classes) {
            System.out.println("Found: " + clazz.getName());
        }
        CollectionUtils.getVersionListClass(FileUtils.getClassSet("learn.test.impl"));
        // TODO初始化一个接口名称和 code对应关系的集合；
        listString = CollectionUtils.getClassNameList(mapMethod);
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(2);
        MethodMap task1 = new MethodMap(latch);
        FilterMethodHandler task2 = new FilterMethodHandler(latch);
        executor.execute(task1);
        executor.execute(task2);
        executor.shutdown();
    }
}
