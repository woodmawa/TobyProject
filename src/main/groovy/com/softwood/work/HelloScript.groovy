package com.softwood.work


println "drinks actions >" + Action.actionsMap.drink

def a1 = new Action (name:"eatGrass")
def a2 = new Action (name:"eatSandwich")
def a3 = new Action (name:"goFishing")  //no match, will call default action
def a4 = new Action (name:"drinkWine") //was added from helper class

a1.performAction()
a2.performAction()
a3.performAction()
a4.performAction()



//now invoke action that came from file
def a5 = new Action (name:"getKnife")  //was added from file
a5.performAction()
