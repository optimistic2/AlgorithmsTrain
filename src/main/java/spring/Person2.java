package spring;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

;

/**
 * @author qsk
 */
public class Person2 extends InstantiationAwareBeanPostProcessorAdapter implements BeanFactoryAware,
        BeanNameAware,
        InitializingBean, DisposableBean, BeanFactoryPostProcessor/*, BeanPostProcessor*/ {

    private String name;
    private String address;
    private String phone;

    private BeanFactory beanFactory;
    private String beanName;

    public Person2() {
        System.out.println("【构造器】调用Person的构造器实例化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【注入属性】注入属性name");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("【注入属性】注入属性address");
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        System.out.println("【注入属性】注入属性phone");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person [address=" + address + ", name=" + name + ", phone="
                + phone + "]";
    }

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out
                .println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory() " + arg0
                        .getClass());
        this.beanFactory = arg0;
    }

    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String arg0) {
        System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = arg0;
    }

    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out
                .println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    // 这是DiposibleBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        System.out.println("【BeanFactoryPostProcessor】调用postProcessBeanFactory方法");
    }

    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) {
        System.out.println(String.format(
                "【InstantiationAwareBeanPostProcessorAdapter】调用predictBeanType方法 beanClass:{%s}, beanName:{%s}",
                beanClass, beanName));
        return super.predictBeanType(beanClass, beanName);
    }

    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName)
            throws BeansException {
        System.out.println(String.format(
                "【InstantiationAwareBeanPostProcessorAdapter】调用 determineCandidateConstructors 方法 beanClass:{%s}, beanName:{%s}",
                beanClass, beanName));
        return super.determineCandidateConstructors(beanClass, beanName);
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.out.println(String.format(
                "【InstantiationAwareBeanPostProcessorAdapter】调用 getEarlyBeanReference 方法 bean:{%s}, beanName:{%s}",
                bean, beanName));
        return super.getEarlyBeanReference(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
            throws BeansException {
        System.out.println(String.format(
                "【InstantiationAwareBeanPostProcessorAdapter】调用 postProcessBeforeInstantiation 方法 beanClass:{%s}, beanName:{%s}",
                beanClass, beanName));
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName)
            throws BeansException {
        System.out.println(String.format(
                "【InstantiationAwareBeanPostProcessorAdapter】调用 postProcessAfterInstantiation 方法 bean:{%s}, beanName:{%s}",
                bean, beanName));
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds,
            Object bean, String beanName) throws BeansException {
        System.out.println(String.format(
                "【InstantiationAwareBeanPostProcessorAdapter】调用 postProcessPropertyValues 方法 pvs:{%s}, pds:{%s}, bean:{%s}, beanName:{%s}",
                pvs, pds, bean, beanName));
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println(String.format(
                "【InstantiationAwareBeanPostProcessorAdapter】调用 postProcessBeforeInitialization 方法 bean:{%s}, beanName:{%s}",
                bean, beanName));
        return super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println(String.format(
                "【InstantiationAwareBeanPostProcessorAdapter】调用 postProcessAfterInitialization 方法 bean:{%s}, beanName:{%s}",
                bean, beanName));
        return super.postProcessAfterInitialization(bean, beanName);
    }
}