package com.playground.tinderswipe.controller;

import com.playground.tinderswipe.model.Greeting;
import com.playground.tinderswipe.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/greeting")
@RestController
public class GreetingController {
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    public void sendGreeting(@RequestBody Greeting greeting) {
        greetingService.sendGreeting(greeting);
    }

    @GetMapping
    public Map<String, List<Greeting>> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    @GetMapping("/{user}")
    public List<Greeting> getGreetingsFromUser(@PathVariable String user) {
        return greetingService.getGreetingsFromUser(user);
    }
}
