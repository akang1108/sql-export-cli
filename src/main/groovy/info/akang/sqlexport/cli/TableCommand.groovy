package info.akang.sqlexport.cli

import groovy.sql.GroovyRowResult

class TableCommand implements Command {

//    int fit = true
    int padding = 20

    String separator = " | "

    @Override
    def execute(def input) {
        input = input as List<GroovyRowResult>

        StringBuilder sb = new StringBuilder()

        // First print header row
        if (input.size() > 0) {
            List<String> keys = input.first().keySet().collect { key ->
                "$key".padRight(padding)
            }
            sb.append "${keys.join(separator)}\n"
        }

        input.each { row ->
            List<String> values = row.collect { entry ->
                "${entry.value}".padRight(padding)
            }
            sb.append "${values.join(separator)}\n"
        }

        String s = sb.toString()

        nextCommand.execute(s)
    }

}
