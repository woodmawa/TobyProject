package com.softwood.condition

import java.util.function.Predicate

class Condition implements Predicate {

    def lowerLimit  = 0
    def upperLimit = 0
    def measure = 0
    String name = "unnamed"
    boolean conditionResult

    //default condition evaluation
    Closure closureTest = {obj -> return false}

    @Override
    boolean test(Object obj = null) {
        if (closureTest) {
            if (obj)
                closureTest.delegate = obj
            else
                closureTest.delegate = this //else set delegte to be this condition instance

            return conditionResult = closureTest(obj)
        }
        return conditionResult = false
    }

    boolean and (Condition condition, Object obj = null) {
        boolean res1 = test(obj)
        boolean res2 = condition.test (obj)
        return conditionResult = test(obj) && condition.test (obj)
    }


    boolean or (Condition condition, Object obj = null) {
        return conditionResult = test(obj) || condition.test (obj)

    }

    def call (Object obj = null) {
        conditionResult = test (obj)
        return this
    }

}
