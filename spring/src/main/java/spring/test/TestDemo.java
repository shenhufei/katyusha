package spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import spring.bean.MyBean;

@Component
@Configuration
//@ComponentScan("spring.bean")
public class TestDemo {
    public static void main(String[] args) {
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("spring.bean");
        MyBean bean = (MyBean) annotationConfigApplicationContext.getBean("MyBean");
        System.out.println(bean);
    }
}
