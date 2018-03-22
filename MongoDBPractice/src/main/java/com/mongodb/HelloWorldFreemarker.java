package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

public class HelloWorldFreemarker {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(
                HelloWorldFreemarker.class, "/");
        try {
            Template helloTemplate = configuration.getTemplate("template.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "Hirams");

            helloTemplate.process(helloMap, writer);

            System.out.println(writer);

            Spark.get("/", (req, res) -> writer);
            Spark.get("/Test", (req, res) -> "Yolanda");
            Spark.get("/echo/:thing", (req, res) -> req.params(":thing"));

        } catch (Exception e) {
            halt(500);
            e.printStackTrace();
        }





    }
}
