package com.softwood.context

/**
 * wrapper for String, and boolean value
 *
 * can add methods etc later if required
 *
 */
class GameState {
    String name
    boolean value

    String toString () {
        "[$name:$value]"
    }

    List asList() {
        [(name), value]
    }
}
