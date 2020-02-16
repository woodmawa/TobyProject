package com.softwood.person

import com.softwood.condition.Sensor
import com.softwood.context.Attribute
import com.softwood.context.GameObject
import com.softwood.context.GameState
import com.softwood.work.Action

import java.util.concurrent.ConcurrentHashMap

class Player {

    String name
    GameObject worldState
    GameState goal
    Collection<Sensor> sensors = [] //add concurrentLinkedQueue later
    Collection<Action> actions = []
    Map attributes = new ConcurrentHashMap<String, Attribute>()

    //for now this is a dummy
    def getObjectsInrange (radius) {
        []
    }
}
