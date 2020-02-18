package com.softwood.context

import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

/**
 * each person gets one of these - its the context container
 * relative to each player
 */
class GameObject {
    String name = "This Players Game Object"

    //geo position for this instance
    List position = [0,0,0]



}
