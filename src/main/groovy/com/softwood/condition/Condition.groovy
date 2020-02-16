package com.softwood.condition

import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.Predicate

class Condition implements Predicate {

    def lowerLimit  = 0
    def upperLimit = 0
    def measure = 0
    String name = "unnamed"
    AtomicBoolean result

    //default condition evaluation
    Closure closureTest = {obj -> return false}

    @Override
    boolean test(Object obj = null) {
        if (closureTest) {
            closureTest.resolveStrategy = Closure.DELEGATE_FIRST
            if (obj)
                closureTest.delegate = obj

            return (result = new AtomicBoolean (closureTest(obj))).get()
        }
        return (result = new AtomicBoolean (false)).get()
    }

    boolean and (Condition condition, Object obj = null) {
        boolean res1 = test(obj)
        boolean res2 = condition.test (obj)
        return (result = new AtomicBoolean (test(obj) && condition.test (obj))).get()
    }


    boolean or (Condition condition, Object obj = null) {
        return (result = new AtomicBoolean (test(obj) || condition.test (obj))).get()

    }

    def call (Object obj = null) {
        result = new AtomicBoolean(test (obj))
        return this
    }

}
