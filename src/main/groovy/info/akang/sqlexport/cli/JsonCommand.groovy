package info.akang.sqlexport.cli

import groovy.json.JsonBuilder
import groovy.sql.GroovyRowResult

class JsonCommand implements Command {

    @Override
    def execute(def input) {
        input = input as List<GroovyRowResult>
        StringBuilder sb = new StringBuilder()

        input.each { row ->
            row.each { entry ->
                sb.append " -- ${entry.value} | "
            }
            sb.append "\n"
        }

        String s = sb.toString()

        s = new JsonBuilder(input).toPrettyString()

        nextCommand.execute(s)
    }

}
