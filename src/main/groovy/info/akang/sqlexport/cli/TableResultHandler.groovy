package info.akang.sqlexport.cli

import groovy.sql.GroovyRowResult

import java.sql.ResultSetMetaData

class TableResultHandler implements ResultHandler {

    @Override
    void handleRows(List<GroovyRowResult> rows) {
        rows.each { row ->
            row.each { entry ->
                print "${entry.value} | "
            }
            println ""se
        }
    }

    @Override
    void handleMeta(ResultSetMetaData meta) {

        for (int i = 1 ; i <= meta.columnCount; i++) {
            print "${meta.getColumnName(i)} |"
        }

        println ""

    }
}