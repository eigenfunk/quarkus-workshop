package org.example.exercise2;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return "Allaaaas:  " + name;
    }

}
