package com.softwood.context

import java.util.concurrent.ConcurrentLinkedQueue

/**
 * each person gets one of these - its the context container
 * relative to each player
 */
class GameObject {
    String name = "This Players Game Object"

    //holds initial queue of game states established by the prefab
    Collection states = [] // todo change to new ConcurrentLinkedQueue<GameState>()

    //geo position for this instance
    List position = [0,0,0]


}
