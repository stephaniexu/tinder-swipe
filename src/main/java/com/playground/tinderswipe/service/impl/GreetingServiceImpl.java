package com.playground.tinderswipe.service.impl;

import com.playground.tinderswipe.model.Greeting;
import com.playground.tinderswipe.repository.GreetingRepository;
import com.playground.tinderswipe.repository.entity.GreetingEntity;
import com.playground.tinderswipe.service.GreetingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GreetingServiceImpl implements GreetingService {
    private static final Logger logger = LogManager.getLogger();

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingServiceImpl(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public void sendGreeting(Greeting greeting) {
        greetingRepository.save(Greeting.toEntity(greeting));
    }

    @Override
    public Map<String, List<Greeting>> getAllGreetings() {
        List<GreetingEntity> entities = greetingRepository.findAll();
        return entities.stream().map(Greeting::fromEntity).collect(Collectors.groupingBy(Greeting::getGreeter));
    }

    @Override
    public List<Greeting> getGreetingsFromUser(String user) {
        List<GreetingEntity> entities = greetingRepository.findByUser(user);
        logger.debug("Greetings from {}: {}", user, entities);
        return entities.stream().map(Greeting::fromEntity).collect(Collectors.toList());
    }
}
