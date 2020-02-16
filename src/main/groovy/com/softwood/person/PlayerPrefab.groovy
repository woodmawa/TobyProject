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
        player.worldState = new GameObject(name:name)
        player.worldState.states << new GameState (name:"hasEnergy", value: true)
        player.worldState.states << new GameState (name:"isAlive", value: true)
        player.worldState.states << new GameState (name:"hasSword", value: false)


        //set player's initial goal
        player.goal = new GameState (name:"hasSword", value:true)

        //set player attributes into player.attributes map
        Attribute energy = new Attribute (name:"Energy", value:100)
        player.attributes << [Energy:energy]
        Attribute att = new Attribute (name:"Bravery", value:3)
        player.attributes << [Bravery:att]

        //add one sensor to check if player is tired
        //set delegate for closure to be the player who has this sensor
        Sensor sensor = new Sensor(observes:energy, name:"isTired", lowWatermark: 35)
        sensor.sense = { param ->
            [attributes.get("Energy")?.name, attributes.get("Energy")?.value < sensor.lowWatermark]
            }
        sensor.sense.delegate = player
        sensor.sense.resolveStrategy = Closure.DELEGATE_FIRST

        player.sensors << sensor

        return player


    }
}
