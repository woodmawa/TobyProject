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
    GameState currentGoal
    //todo probably best done as a Set of goals
    Set<GameState>  goals = []
    Collection<Sensor> sensors = [] //add concurrentLinkedDequeue later
    Collection<Action> actions = []
    Map attributes = new ConcurrentHashMap<String, Attribute>()

    //for now this is a dummy
    def getObjectsInRange (radius) {
        []
    }
}
