package com.hyq.configurationproperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * BeanPostProcessor:
 * 对spring bean生命周期进行管理，可以在bean初始化前后做文章
 * 检查每一个初始化成功的Bean，如果使用了我们的自定义注解，那么就把从配置文件中解析出来的数据，使用反射技术注入进去。
 *
 * @author Yuqiu.He
 * @date 2021/1/14
 */
@Component
public class ConfigPostProcess implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPostProcess.class);
    private static final String FILE_NAME = "config.properties";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //获取使用了我们自定义注解@Config的类
        Config configAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), Config.class);
        //如果这个对象使用了此注解
        if (configAnnotation != null) {
            //解析配置文件，并将解析结果放入Map中
            Map<String, String> configProperties = getConfigPropertiesFromFile(configAnnotation);
            //将对应的值，使用反射技术，注入到这个bean中
            bindBeanValue(bean, configProperties);
        }
        return bean;
    }

    /**
     * 将对应的值，使用反射技术，注入到这个bean中
     */
    private void bindBeanValue(Object bean, Map<String, String> configProperties) {
        if (configProperties.size() > 0) {
            configProperties.forEach((key, value) -> {
                setFieldValueByFieldName(key, bean, value);
            });
        }
    }

    /**
     *  从配置文件中读取配置好的键值对，并放入到Map中
     */
    private Map<String, String> getConfigPropertiesFromFile(Config configAnnotation) {
        //get prefix from annotation
        String prefix = configAnnotation.prefix();

        //read value from resource file
        Properties properties = getClassNameFromResource(FILE_NAME);
        Map<String, String> configProperties = new HashMap<>();
        Set<String> keys = properties.stringPropertyNames();
        List<String> keyList = new ArrayList<>(keys);
        for (String key : keyList) {
            if (key.startsWith(prefix)) {
                //default.password ==> password
                String realKey = key.substring(key.indexOf(prefix) + prefix.length() + 1);
                String value = properties.getProperty(key);
                configProperties.put(realKey, value);
            }
        }


        return configProperties;
    }

    /**
     * 读取配置文件，返回一个流对象
     */
    private Properties getClassNameFromResource(String fileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = ConfigPostProcess.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 为指定字段使用反射技术设值(只支持String 与 int 类型)
     */
    private void setFieldValueByFieldName(String fieldName, Object object, String value) {
        try {
            Class c = object.getClass();
            if (checkFieldExists(fieldName, c)) {
                Field f = c.getDeclaredField(fieldName);
                f.setAccessible(true);
                //如果不是String，那么就是int。其它类型不支持
                if(f.getType().equals(String.class)){
                    f.set(object, value);
                }else{

                    int number = Integer.valueOf(value);
                    f.set(object, number);
                }

            }
        } catch (Exception e) {
            LOGGER.error("设置" + fieldName + "出错");
        }
    }

    /**
     * 检查这个Bean是否有配置文件中配置的字段
     * 没有就不设置了
     */
    private boolean checkFieldExists(String fieldName, Class c) {
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
