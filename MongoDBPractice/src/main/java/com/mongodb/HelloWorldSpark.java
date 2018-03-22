package com.mongodb;

import spark.Spark;

public class HelloWorldSpark {
    public static void main(String[] args) {

        Spark.get("/", (req, res) -> "Hello Hiram from SparkWorld!");

    }
}
