package com.softwood.condition

import java.util.function.Predicate

class Condition implements Predicate {

    def lowerLimit  = 0
    def upperLimit = 0
    def measure = 0

    //default condition evaluation
    Closure closureTest = {obj -> return false}  

    @Override
    boolean test(Object obj = null) {
        if (closureTest) {
            closureTest.delegate = obj

            return closureTest(obj)
        }
        return false
    }

    boolean and (Condition condition, Object obj = null) {
        return test(obj) && condition.test (obj)
    }

    boolean or (Condition condition, Object obj = null) {
        return test(obj) || condition.test (obj)

    }

}
