package com.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class WorldVerticle extends AbstractVerticle {

    public void start() {
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer("demo.address", message -> {
            System.out.println("WorldVerticle received message : " + message.body());
            message.reply("WorldVerticle reply message");
        });
    }
}
