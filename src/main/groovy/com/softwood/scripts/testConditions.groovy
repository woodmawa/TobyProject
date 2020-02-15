package com.softwood.scripts

import com.softwood.condition.Condition

def c1 = new Condition ()
def c2 = new Condition ()

c1.lowerLimit = -10
c1.upperLimit = 100
c1.measure = 10

Closure cls = c1.closureTest
cls.delegate = c1  //set delegate to this condition

c1.closureTest = {return c1.measure < it }  //overide the default


def val = 50

println "c1 is : " + c1.test(val) + " and c2 is : " + c2.test()

println c1.or (c2)

