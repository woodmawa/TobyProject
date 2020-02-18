package com.softwood.context

import groovy.transform.EqualsAndHashCode
import groovy.transform.MapConstructor

/**
 * wrapper for String, and boolean value
 *
 * can add methods etc later if required
 *
 */
//force map constructor enabled, as i want to define standard constructor from a [String:bool] map
@MapConstructor
// this ensures that the Collection<GameState>.contains() method will correctly calculate true/false
@EqualsAndHashCode (excludes = 'weight')
class GameState {
    static final GameState NOT_DEFINED = new GameState (name:"notDefined", value:true)

    String name
    boolean value
    int weight = 0  //can be set if one GameState is more important than another

    //provide default constructor using a map, but also get groovy default map constructor as well !
    GameState (final String name, final Boolean bool) {
        assert name, bool
        this.name = name
        value = bool
    }

    String toString () {
        "[$name:$value]"
    }

    List asList() {
        [(name), value]
    }

}
