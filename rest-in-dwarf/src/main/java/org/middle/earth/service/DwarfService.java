package org.middle.earth.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.text.MessageFormat;

@ApplicationScoped
public class DwarfService {

    public String payMe(int age, int waist) {
        String result;
        if (age < 150) {
            result = MessageFormat.format("You''re a {0}-year-old dwarf, too young, so keep working hard!", age);
        } else {
            result = MessageFormat.format("You''re a {0}-year-old dwarf, you deserve {1} mithril coins this month!", age, 100 * waist);
        }
        return result;
    }
}
