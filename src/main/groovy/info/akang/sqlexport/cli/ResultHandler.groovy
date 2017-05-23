package info.akang.sqlexport.cli

import groovy.sql.GroovyRowResult

import java.sql.ResultSetMetaData

trait ResultHandler {

    abstract void handleRows(List<GroovyRowResult> rows)

    abstract void handleMeta(ResultSetMetaData meta)

}