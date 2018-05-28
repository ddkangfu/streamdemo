package com.zc3u.streamdemo;

import com.zc3u.streamdemo.service.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableBinding(value = {Processor.class})
public class StreamdemoApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StreamdemoApplication.class, args);
        System.out.println("注册结果：" + setHander(context));
        System.out.println("发送结果：" + write(context));
	}

    public static boolean setHander(ConfigurableApplicationContext context) {
        Service service = context.getBean(Service.class);
        return service.subscribe(result -> {
            System.out.println("狗子收到消息：" + result.getPayload());
        });
    }

    public static boolean write(ConfigurableApplicationContext context) {
        Service service = context.getBean(Service.class);
        return service.write("狗子在吗?");
    }
}
