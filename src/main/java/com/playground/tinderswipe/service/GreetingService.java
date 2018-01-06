package com.playground.tinderswipe.service;

import com.playground.tinderswipe.model.Greeting;

import java.util.List;
import java.util.Map;

public interface GreetingService {
    void sendGreeting(Greeting greeting);

    Map<String, List<Greeting>> getAllGreetings();

    List<Greeting> getGreetingsFromUser(String user);
}
