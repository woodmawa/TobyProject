package com.softwood.work

import jdk.internal.loader.Resource

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ActionMapBuilder {

    static Map refLookup = [drink : [Water: {println "fresh clean water, aaah"},
                                     Wine: {println "a relaxing tiple at last ..."}],
                            drive : [Town: {println "taking car into town"}]
    //add others here, or add from helper class that declares the hashes and the delegates, and adds to this hash
    //you could add a constructor to load this on first use for example ...
    //just left it simple directly in the class at the mo to remove clutter
    ]

    /**
     * this reads the actionsMapFileDefinition.csv  and parses the content,
     * constructs new dynamic entries from that file, and adds to the
     * Actions.actionsMap
     * Note: not defensivley coded!
     */
    static void dynamicActionsLoader () {
        GroovyShell shell = new GroovyShell()

        ClassLoader cl = Thread.currentThread().getContextClassLoader()

        //nightmare loading files - in script had otr use / as relative base
        //put the code into a static method of class at it failed
        //so as fix - get the currentThreads classloader (callee's) this doesnt appear to need '/' pre pended
        URL  fileRef = cl.getResource("actionMapFileDefinition.csv")

        Path fPath = Paths.get(fileRef.toURI())
        List<String> lines = Files.readAllLines(fPath)

        // iterate through all the lines and dynamically add the entries to Actions.actionsMap
        lines?.each {

            if (it.startsWith("//")) {/*do nothing*/}
            else if (it == "") {/*do nothing*/}
            else {
                //split the line on the @ token to get, noun, verb, and closure as text
                StringTokenizer st = new StringTokenizer (it, "@") //break on @
                List<String> listOfTokens = st.toList()

                //evaluate uses return + the closure string to get returned Closure ref, which you can then invoke
                Closure actionClosure  =  shell.evaluate ("return " + listOfTokens[2])
                assert actionClosure instanceof Closure

                //'eval ()' to set the string value as the key in the hash
                Map nounMap = [(listOfTokens[1].trim()) : actionClosure]
                Map verbMap = [(listOfTokens[0].trim()) : nounMap]

                //now dynamically add the entries  read from a file to Actions.actionsMap
                Map existingVerbsNounMap
                if (existingVerbsNounMap = Action.actionsMap.(listOfTokens[0].trim()) ) {
                    //moun is already in the map with Verb map, so add our new verbs to that verbMap
                    existingVerbsNounMap.putAll(nounMap)
                } else {
                    //else add new whole new verb map
                    Action.actionsMap.putAll(verbMap)
                }

            }
        }

    }
}
