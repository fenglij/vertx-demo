package com.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class HelloVerticle extends AbstractVerticle {
    public void start(){
        // demo 1
        /*vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World ! Configuration is : " + config().getString("name"));
        }).listen(8600);*/

        EventBus eventBus = vertx.eventBus();
        eventBus.consumer("demo.address", message -> {
            System.out.println("HelloVerticle received message : " + message.body());
            message.reply("HelloVerticle reply message");
        });
    }
}
