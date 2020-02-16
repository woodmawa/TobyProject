package com.softwood.context

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

//simple container for all the active objects
//if not in the scene then you cant actually do something
class Scene {
    Collection elements = new ConcurrentLinkedQueue<>()
}
