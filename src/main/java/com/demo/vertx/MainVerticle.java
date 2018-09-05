package com.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class MainVerticle extends AbstractVerticle {
    public void start(){
        // demo 1
        /*vertx.deployVerticle(HelloVerticle.class.getName(), res -> {
            if (res.succeeded()) {
                System.out.println("deployment id is : " + res.result());
            } else {
                System.out.println("deployment failed");
            }
        });*/

        /*JsonObject config = new JsonObject().put("name", "fenglij");
        DeploymentOptions options = new DeploymentOptions().setConfig(config);
        vertx.deployVerticle(HelloVerticle.class.getName(), options);*/

        // 服务启动的时候会在控制台打印输出信息
        Context context = vertx.getOrCreateContext();
        if (context.isEventLoopContext()) {
            System.out.println("Context attached to Event Loop");
        } else if (context.isWorkerContext()) {
            System.out.println("Context attached to Worker Thread");
        } else if (context.isMultiThreadedWorkerContext()) {
            System.out.println("Context attached to Work Thread - multi thread worker");
        } else if (!Context.isOnVertxThread()) {
            System.out.println("Context not attached to a thread by vert.x");
        }

        // 传递参数
        JsonObject config = new JsonObject().put("name", "fenglij");
        DeploymentOptions options = new DeploymentOptions().setConfig(config);
        vertx.deployVerticle(HelloVerticle.class.getName(), options);
        vertx.deployVerticle(WorldVerticle.class.getName(), options);

        // 线程休眠3秒，让消费者初始化完，再让生产者发布消息
        // 因为是vert.x 是异步，所以会出现消费者没有初始化完，生产者就发布了消息，导致没有办法消费
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 添加Event Bus
        EventBus eventBus = vertx.eventBus();
        // send: 点对点模式，多个绑定在地址上的处理器，eventbus 寻找其中一个处理器处理
        /*eventBus.send("demo.address", "send, Event Bus", replyHandler -> {
            Object replyMsg = replyHandler.result().body();
            System.out.println("reply message : " + replyMsg);
        });*/

        // publish: 发布订阅模式，所有绑定在地址上的处理器，都能收到消息
        eventBus.publish("demo.address", "publish, Event Bus");
    }
}
