package com.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MainVerticle extends AbstractVerticle {
    public void start(){
        /*vertx.deployVerticle(HelloWorldVerticle.class.getName(), res -> {
            if (res.succeeded()) {
                System.out.println("deployment id is : " + res.result());
            } else {
                System.out.println("deployment failed");
            }
        });*/

        /*JsonObject config = new JsonObject().put("name", "fenglij");
        DeploymentOptions options = new DeploymentOptions().setConfig(config);
        vertx.deployVerticle(HelloWorldVerticle.class.getName(), options);*/

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

        JsonObject config = new JsonObject().put("name", "fenglij");
        DeploymentOptions options = new DeploymentOptions().setConfig(config);
        vertx.deployVerticle(HelloWorldVerticle.class.getName(), options);
    }
}
