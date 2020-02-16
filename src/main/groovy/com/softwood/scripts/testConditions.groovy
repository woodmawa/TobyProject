package com.softwood.scripts

import com.softwood.context.GameObject
import com.softwood.condition.Condition

GameObject myGame = new GameObject()

println "game is called : "+ myGame.name + " with position " + myGame.position


def c1 = new Condition (name:"fred")
def c2 = new Condition (name:"joe")

c1.lowerLimit = -10
c1.upperLimit = 100
c1.measure = 10

Closure cls = c1.closureTest
cls.delegate = c1  //set delegate to this condition

c1.closureTest = {obj -> return c1.measure < obj }  //overide the default


def val = 50

println "c1 is : " + c1.test(val) + " and c2 is : " + c2.test()

println "c1 call () : " + c1.call()
println "c1 resolved to : " + c1.result.get()

println c1.or (c2, val)

