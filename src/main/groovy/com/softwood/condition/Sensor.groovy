package com.softwood.condition

import com.softwood.context.GameState

import java.util.function.Predicate



//might be a subclass of Condition later ...
class Sensor {

    String name
    def observes //sensor will look at something
    Closure<GameState> sense = {GameState.NOT_DEFINED}  //do nothing
    def lowWatermark
    def highWatermark

    //it will return true or false

    void setSense (Closure cls) {

        if (!cls)
            throw NullPointerException
        else {
            sense = cls
            sense.resolveStrategy = Closure.DELEGATE_FIRST
        }
    }
}
