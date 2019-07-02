package com.xbauquet;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;

public class Application {

    private final static String token = "222lsfhdsqlkfhidp4xfhm<kdsfhjm5464wfjs54dlf5dvg9kbbdhvhfdogfdjohkgjfgb543575xfvjhfwlgf2";

    public static void main(String[] args) {
        path("/token", () -> {

            get("/get", (request, response) -> {
                String headers = request.headers("Authorization");
                if(headers == null) {
                    halt(401, "You are not welcome here. (Authorization header is null) \n");
                }
                String[] basics = headers.split(" ")[1].split(":");
                if("user".equals(basics[0].toLowerCase()) && "password".equals(basics[1].toLowerCase())) {
                    return token;
                } else {
                    halt(401, "You are not welcome here. (Bad credentials " + basics[0] + ":" + basics[1] + ") \n");
                }
                return null;
            });
        });
    }

}
