package com.softwood.person

import com.softwood.condition.Sensor
import com.softwood.context.Attribute
import com.softwood.context.GameObject
import com.softwood.context.GameState
import com.softwood.work.Action

import java.util.concurrent.ConcurrentHashMap

class Player {

    String name
    GameObject currentGame
    Collection<GameState> worldState = []
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

    //make chainable
    def leftShift (Action action, String scope = Action.Scope.PLAYER) {
        assert action
        action.player = this
        action.scope = scope
        actions.add(action)
        return this
    }

    //should return player instance
    def addAction (Action action) {
        assert action
        actions << action
    }

    //todo needs to watch for concurrent access exceptions - do later
    void removeAction (Action action) {
        if (actions.contains (action)) {
            action.player = null    //remove reference to this player
            actions.remove(action)
        }
    }
}
