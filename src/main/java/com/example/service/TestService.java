package com.example.service;

import com.example.mqtt.MqttConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestService
 * @Description:
 * @Author 刘苏义
 * @Date 2023/5/29 20:03
 * @Version 1.0
 */
@Slf4j
@Service
public class TestService {
    @Async("mqtt")
    public void run(Integer num) {
        synchronized (this) {
            MqttConsumer.publish(1, false, "test", "test" + num);
        }
    }
}
