package com.softwood.scripts

import com.softwood.condition.Condition
import com.softwood.condition.Sensor
import com.softwood.context.GameObject
import com.softwood.context.GameState
import com.softwood.context.Scene
import com.softwood.person.Player
import com.softwood.person.PlayerPrefab
import com.softwood.work.Action

Scene scene = new Scene()

Player player = new PlayerPrefab<Player>().newPlayer("Will Woodman")


//add player to scene
scene.elements << player

Sensor sensor = player.sensors[0]
def val =  sensor.sense()
assert val == ["Energy",false]
assert player.name == "Will Woodman"
assert player.currentGoal.asList() == ["hasSword",true]
assert player.attributes.size() == 2
assert player.sensors.size() == 1
assert player.goals.size() == 2
//need to figure out how to compare a Set<GameState> with simple typed entry, so use spread dot to Set then toString() and compare that
assert  player.goals*.toString() == ["[hasSword:true]","[killEnemy:true]"] as ArrayList

println ">> players initial world state before actions is $player.worldState"

//create an action and attach this to the player - not all actions will require players at the mo
Action action = new Action(name:"myFirstAction")
action.preConditions << new GameState (name:"blueSky", value:true)
action.effects << new GameState (name:"runForTheHills", value:true) <<  new GameState (name:"newbie", value:false)
def p = player << action

Action action2 = new Action(name:"mySecondAction")
action2.preConditions << new GameState (name:"isAlive", value:true)
action2.effects << new GameState (name:"runForTheHills", value:true) <<  new GameState (name:"newbie", value:false)
p = player << action2

assert player.actions.size() == 2  //haven't added actions yet
assert action.player == player

//ask player to perform the action - if preconditions are all ok - then check that effects have been updated on players worldState
println "\n>> perform eatGrass action "
player.actions[0].performAction("eatGrass")
println player.worldState  //action should fail as preconditions were not met on action[0]

println "\n>> perform walkPark action "
player.actions[1].performAction("walkPark")
println player.worldState  //should contain "run for the hills", and set newbie to false

//this bit is just checking that setting Conditions expectedGameState and then running a test on either a GameState, or worldState list works as expected
println "\n>> check dynamic condition.test works for param of GameState, or worldStateList<GameState> "
GameState gs1 = new GameState("imHungry", true )
Condition c1 = new Condition()
Condition c2 =        c1.setExpectedGameState (new GameState(name:"imHungry", value:true ))
def c3 =        (c1.expectedGameState = new GameState(name:"imHungry", value:true ))  //straight assignment just returns GameState

def worldState = [new GameState(name:"imHungry", value:true ), new GameState (name:"shortHair", value:true)]

def res = c1.test(gs1)
def res2 = c1.test (worldState)

println "$res, $res2"

