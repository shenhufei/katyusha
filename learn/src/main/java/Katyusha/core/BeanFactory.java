package Katyusha.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanFactory implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
   
    private static BeanFactory beanFactory;
    public  static synchronized BeanFactory getInstance(){
    	if(null==beanFactory){
    		beanFactory = new BeanFactory();
    	}
    	return beanFactory;
    }

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
    	BeanFactory.applicationContext = applicationContext;
    }

    /**
     * 获得spring上下文
     * 
     * @return ApplicationContext spring上下文
     */
    public  ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取bean
     * 
     * @param name
     *            service注解方式name为小驼峰格式
     * @return Object bean的实例对象
     */
    public  Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

}
