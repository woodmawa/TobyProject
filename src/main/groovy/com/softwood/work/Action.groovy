package com.softwood.work

import com.softwood.condition.Condition
import com.softwood.context.GameState
import groovy.transform.ToString

import java.util.concurrent.ConcurrentLinkedDeque

@ToString
class Action {
    //delegate (closure) hash lookup, takes a noun (first key) to get a hash, then tries to index using a verb to get the delegate
    static Map actionsMap = new HashMap () <<
            [eat : [Grass: {println "eat grass baby"},
                    Sandwich: {println "eating my sandwich, leave me in peace"}],
             walk: [Park: {println "taking a stroll in the park"}]
             //add others here, or add from helper class that declares the hashes and the delegates, and adds to this hash
             //you could add a constructor to load this on first use for example ...
             //just left it simple directly in the class at the mo to remove clutter
            ]

    //static initialiser block to augment the actionsMap
    static {
        println "running Action static initialiser"
        ActionMapBuilder.refLookup.each {verb, nounMap ->
            Map existingVerbNounMap
            if (existingVerbNounMap = actionsMap.(verb))  {
                //just update existing non map for this verb
                existingVerbNounMap.putAll(nounMap)
            }  else {
                //else add the whole new verb and noun map entries to actionMap
                actionsMap.put (verb, nounMap)
            }
        }
        ActionMapBuilder.dynamicActionsLoader()  //load any dynamic actions
   }

    Closure unknownVerbAction = {println "cant find a matching action for '$name'"}
    String name

    //action can only be performed if all preConditions are true
    Set<Condition> preConditions = []

    //not sure how to do effects - this is just a set of closures?
    //is this different than the action performed ?
    //the effects are a set of modifications to the players worldState,
    //so would remove previous matched entry and adds new replacement state
    Set<GameState> effects = []

    //accept a string, like aCamelString
    //return a list containing strings, in this case, [a, Camel, String]
    public static LinkedList<String> splitCamelCaseString(String s){
        LinkedList<String> result = new LinkedList<String>()
        for (String w : s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            result.add(w)
        }
        return result

    }

    def performAction (param) {
        String noun, verb
        assert actionsMap

        def verbNounList = splitCamelCaseString(name)

        //lookup the work action by verb, then noun  - if we have a closure invoke that to do the work
        //have to careful that the key for the hash is String and not a Gstring
        verb = verbNounList[0].trim()
        noun  = verbNounList[1].trim()

        //need to 'eval' the verb, and noun to get the actual value as the key
        Closure work = actionsMap?.(verb)?.(noun)

        if (work) {
            //invoke the work action
            work()
        } else {
            //if null invoke the default
            unknownVerbAction()
        }

        applyEffects()
    }
    
    private  applyEffects () {
        //todo how do i determine who my player is ?
        if (effects) {
            effects.each {
                /*
                if worldState.contains( it.key)
                remove entry from players worldstate
                add entry entry it to world state
                 */
            }
        }
    }
}
