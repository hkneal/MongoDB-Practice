package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

public class FruitFormSubmit {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(
                FruitFormSubmit.class, "/");
        try {
            Template helloTemplate = configuration.getTemplate("fruitPicker.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("fruits",
                    Arrays.asList("Apple", "Orange", "Banana", "Kiwi", "Strawberry", "Pineapple"));

            helloTemplate.process(helloMap, writer);

            System.out.println(writer);

            Spark.get("/", (req, res) -> writer);
            Spark.get("/Test", (req, res) -> "Yolanda");
            Spark.get("/echo/:thing", (req, res) -> req.params(":thing"));


        } catch (
                Exception e)

        {
            halt(500);
            e.printStackTrace();
        }

        Spark.post("/favorite_fruit", (request, response) -> {
            final String fruit = request.queryParams("fruit");
            if(fruit == null) {
                return "Why don't you select a fruit?";
            }
            else {
                return "Your favorite fruit is: " + fruit;
            }
        });
    }

}
