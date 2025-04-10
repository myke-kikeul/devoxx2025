package org.middle.earth.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.text.MessageFormat;

@ApplicationScoped
public class HobbitService {

    final static int PIPE_WEED_RATE = 1234;

    public String payMe(int age, int size) {
        String result;
        if (age < 111) {
            result = MessageFormat.format("You''re a {0} year old hobbit, too young, so keep working hard!", age);
        } else {
            result = MessageFormat.format("You''re an old {0} year old hobbit, you deserve {1} silver pennies this month!", age, PIPE_WEED_RATE * size);
        }
        return result;
    }

}
