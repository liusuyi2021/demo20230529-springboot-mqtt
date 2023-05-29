package com.example.mqtt;

/**
 * @ClassName MqttConsumerCallback
 * @Description:
 * @Author 刘苏义
 * @Date 2023/5/29 19:57
 * @Version 1.0
 */
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import java.util.Arrays;

/**
 * mqtt回调处理类
 */
@Slf4j(topic = "mqtt")
public class MqttConsumerCallback implements MqttCallbackExtended {

    private MqttClient client;
    private MqttConnectOptions options;
    private String[] topic;
    private int[] qos;

    public MqttConsumerCallback(MqttClient client, MqttConnectOptions options, String[] topic, int[] qos) {
        this.client = client;
        this.options = options;
        this.topic = topic;
        this.qos = qos;
    }

    /**
     * 断开重连
     */
    @Override
    public void connectionLost(Throwable cause) {
        log.info("MQTT连接断开，发起重连......");
        try {
            while (!client.isConnected()) {
                Thread.sleep(5000);
                if (null != client && !client.isConnected()) {
                    client.reconnect();
                    log.error("尝试重新连接");
                } else {
                    client.connect(options);
                    log.error("尝试建立新连接");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收到消息调用令牌中调用
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

        //log.info("deliveryComplete---------" + Arrays.toString(topic));
    }

    /**
     * 消息处理
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            // subscribe后得到的消息会执行到这里面
          //  log.info("接收消息 【主题】:" + topic + " 【内容】:" + new String(message.getPayload()));
            //进行业务处理

        } catch (Exception e) {
            log.info("处理mqtt消息异常:" + e);
        }
    }

    /**
     * mqtt连接后订阅主题
     */
    @Override
    public void connectComplete(boolean b, String s) {
        try {
            if (null != topic && null != qos) {
                if (client.isConnected()) {
                    client.subscribe(topic, qos);
                    log.info("mqtt连接成功，客户端ID：" + PropertiesUtil.MQTT_CLIENT_ID);
                    log.info("--订阅主题:：" + Arrays.toString(topic));
                } else {
                    log.info("mqtt连接失败，客户端ID：" + PropertiesUtil.MQTT_CLIENT_ID);
                }
            }
        } catch (Exception e) {
            log.info("mqtt订阅主题异常:" + e);
        }
    }
}

