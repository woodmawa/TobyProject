package com.softwood.scripts

import com.softwood.condition.Sensor
import com.softwood.context.Scene
import com.softwood.person.Player
import com.softwood.person.PlayerPrefab

Scene scene = new Scene()

Player player = new PlayerPrefab<Player>().newPlayer("Will Woodman")
//add player to scene
scene.elements << player

Sensor sensor = player.sensors[0]
def val =  sensor.sense()
assert val == ["Energy",false]
assert player.name == "Will Woodman"
assert player.goal.asList() == ["hasSword",true]
assert player.actions.size() == 0  //havent added actions yet
assert player.attributes.size() == 2
assert player.sensors.size() == 1
