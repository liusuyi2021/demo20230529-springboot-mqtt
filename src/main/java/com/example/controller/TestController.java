package com.example.controller;

import com.example.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName TestController
 * @Description:
 * @Author 刘苏义
 * @Date 2023/5/29 20:08
 * @Version 1.0
 */
@RestController
public class TestController {

    @Resource
    TestService testService;

    @GetMapping("/")
    String test() throws InterruptedException {
        // 开始时间
        long stime = System.currentTimeMillis();


        for (int i = 0; i < 5000; i++) {
            Thread.sleep(50);
            testService.run(i);
        }


        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.println("查询API执行时长："+(etime-stime)+"毫秒");
        return "OK";
    }
}
