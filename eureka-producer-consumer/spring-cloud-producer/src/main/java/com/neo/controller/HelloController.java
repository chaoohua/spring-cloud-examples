package com.neo.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is producer1 send first messge";
    }

    @RequestMapping("/user")
    public String getUserInfo(@RequestParam String name) {
        String user = "";
        try {
            Map map = new HashMap<>();
            map.put("id","123");
            map.put("name","producer1:"+name);
            map.put("sex","man");
            user = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return user;
    }
}