package com.softwood.condition

import java.util.function.Predicate



//might be a subclass of Condition later ...
class Sensor {

    String name
    def observes //sensor will look at something
    Closure<List> sense = {[]}  //do nothing
    def lowWatermark
    def highWatermark

    //it will return true or false

}
