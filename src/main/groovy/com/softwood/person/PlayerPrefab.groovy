package com.softwood.person

import com.softwood.condition.Sensor
import com.softwood.context.Attribute
import com.softwood.context.GameObject
import com.softwood.context.GameState

/**
 * Factory Builder class - genericise later
 *
 * @param <Player>
 */
class PlayerPrefab<T> {

    T newPlayer (name) {
        Player player = new Player(name: name)

        //build initial worldState for a player
        player.currentGame = new GameObject(name:name)
        player.worldState <<  new GameState (name:"newbie", value: true)
        player.worldState << new GameState (name:"hasEnergy", value: true)
        player.worldState << new GameState (name:"isAlive", value: true)
        player.worldState << new GameState (name:"hasSword", value: false)


        //set player's initial goal
        player.currentGoal = new GameState (name:"hasSword", value:true)
        player.goals << player.currentGoal << new GameState (name:"killEnemy", value:true)

        //set player attributes into player.attributes map
        Attribute energy = new Attribute (name:"Energy", value:100)
        player.attributes << [Energy:energy]
        Attribute att = new Attribute (name:"Bravery", value:3)
        player.attributes << [Bravery:att]

        //add one sensor to check if player is tired
        //set delegate for closure to be the player who has this sensor
        Sensor sensor = new Sensor(observes:energy, name:"isTired", lowWatermark: 35)
        //attributes will be resolved to the player instance delegate
        sensor.sense = { param ->
            GameState senseAttributeState = new GameState(name:"isTired", value:attributes.get("Energy")?.value < sensor.lowWatermark )
        }
        sensor.sense.delegate = player

        //add sensor to players list of sensors
        player.sensors << sensor

        return player


    }
}
