package com.softwood.condition

import com.softwood.context.GameState

import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.Predicate

class Condition implements Predicate {

    def lowerLimit  = 0
    def upperLimit = 0
    def measure = 0
    String name = "unnamed"
    AtomicBoolean result
    private GameState expectedGameState = GameState.NOT_DEFINED     //default gameState to check for - youd expect match to false

    //make this set method as chainable
    Condition setExpectedGameState (GameState gameState) {
        assert gameState
        expectedGameState = gameState
        return this
    }

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

    /**
     * if we get passed worldState for a player - look to see if we have a GameState defined for the condition
     * if this is so then try and look for a match with this state in the list. if we have a match then return
     * true else false
     * @param worldStateList
     * @return
     */
    boolean test(Collection<GameState> worldStateList) {
        assert worldStateList

        //if expectedGameState has not been overriden - then it wont be players worldstate - so return false
        if (expectedGameState == GameState.NOT_DEFINED)
            return (result = new AtomicBoolean (false)).get()
        else {
            if (worldStateList.contains(expectedGameState))
                return (result = new AtomicBoolean(true)).get()
            else
                return (result = new AtomicBoolean (false)).get()  //no match for conditions gameState
        }
    }

    /**
     * if we get passed worldState for a player - look to see if we have a GameState defined for the condition
     * if this is so then try and look for a match with this state in the list. if we have a match then return
     * true else false
     * @param worldStateList
     * @return
     */
    boolean test(GameState gameState) {
        assert gameState

        if (gameState == expectedGameState)
            return (result = new AtomicBoolean(true)).get()
        else
            return (result = new AtomicBoolean (false)).get()  //no match for conditions gameState

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
