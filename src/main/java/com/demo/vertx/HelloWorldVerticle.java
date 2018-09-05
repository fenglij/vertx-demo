package com.demo.vertx;

import io.vertx.core.AbstractVerticle;

public class HelloWorldVerticle extends AbstractVerticle {
    public void start(){
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World ! Configuration is : " + config().getString("name"));
        }).listen(8600);
    }
}
