package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycle {

    public static void main(String[] args) {

        System.out.println("1".equals(String.valueOf(1L)));
        Long g = 9999L;
        System.out.println("9999".equals(String.valueOf(9999L)));

//        System.out.println("现在开始初始化容器");
//
//        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:/beans.xml");
//        System.out.println("容器初始化成功");
//        //得到Preson，并使用
//        Person2 person = factory.getBean("person2", Person2.class);
//        System.out.println(person);
//
//        System.out.println("现在开始关闭容器！");
//        ((ClassPathXmlApplicationContext) factory).registerShutdownHook();
    }
}