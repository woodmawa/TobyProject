package com.softwood.condition

import java.util.function.Predicate



//might be a subclass of Condition later ...
class Sensor implements Predicate {

    String name
    def observes //sensor will look at something

    //it will return true or valse

    @Override
    boolean test(Object o) {
        return false
    }
}
