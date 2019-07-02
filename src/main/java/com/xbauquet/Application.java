package com.xbauquet;

import java.util.Base64;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;

public class Application {

    private final static String token = "222lsfhdsqlkfhidp4xfhm<kdsfhjm5464wfjs54dlf5dvg9kbbdhvhfdogfdjohkgjfgb543575xfvjhfwlgf2";

    public static void main(String[] args) {
        path("/token", () -> {

            get("/get", (request, response) -> {
                try {
                    String headers = request.headers("Authorization");
                    if(headers == null) {
                        halt(401, "You are not welcome here. (Authorization header is null) \n");
                    }
                    String basics = new String(Base64.getDecoder().decode(headers.split(" ")[1]));

                    if("user:password".equals(basics)) {
                        return token;
                    } else {
                        halt(401, "You are not welcome here. (Bad credentials " + basics + ") \n");
                    }

                } catch(Throwable t) {
                    t.printStackTrace();
                    throw t;
                }
                return null;
            });
        });
    }

}
