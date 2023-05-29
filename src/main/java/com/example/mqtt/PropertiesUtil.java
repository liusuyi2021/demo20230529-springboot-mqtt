package com.example.mqtt;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.util.Properties;
/**
 * @ClassName PropertiesUtil
 * @Description:
 * @Author 刘苏义
 * @Date 2023/5/29 19:56
 * @Version 1.0
 */
public class PropertiesUtil {

    public static String MQTT_HOST;
    public static String MQTT_CLIENT_ID;
    public static String MQTT_USER_NAME;
    public static String MQTT_PASSWORD;
    public static String MQTT_TOPIC;
    public static Integer MQTT_TIMEOUT;
    public static Integer MQTT_KEEP_ALIVE;
    public static Boolean MQTT_ENABLED;
    /**
     *  mqtt配置
     */
    static {
        MQTT_HOST = getYmlNew("spring.mqtt.host");
        MQTT_CLIENT_ID = getYmlNew("spring.mqtt.clientId");
        MQTT_USER_NAME = getYmlNew("spring.mqtt.username");
        MQTT_PASSWORD = getYmlNew("spring.mqtt.password");
        MQTT_TOPIC = getYmlNew("spring.mqtt.topic");
        MQTT_TIMEOUT = Integer.valueOf(getYmlNew("spring.mqtt.timeout"));
        MQTT_KEEP_ALIVE = Integer.valueOf(getYmlNew("spring.mqtt.keepalive"));
        MQTT_ENABLED = Boolean.valueOf(getYmlNew("spring.mqtt.enabled"));
    }

    public static String getYmlNew(String key) {
        Resource resource = new ClassPathResource("application.yml");
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties = yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key).toString();
    }
}

