package info.akang.sqlexport.cli

import groovy.sql.GroovyRowResult
import groovy.sql.Sql

import javax.sql.DataSource
import java.sql.SQLException

class Select implements Command {

    DataSource ds

    ResultHandler resultHandler

    Select(DataSource ds, ResultHandler resultHandler) {
        this.ds = ds
        this.resultHandler = resultHandler
    }

    @Override
    String name() {
        return "select"
    }

    @Override
    def execute(String line) {
        Sql sql = new Sql(ds)

        Closure metaClosure = resultHandler.&handleMeta

        try {
            List<GroovyRowResult> rows = sql.rows(line, metaClosure)

            resultHandler.handleRows(rows)
            rows
        } catch (SQLException ex) {
            ex.printStackTrace()
        }

    }

    @Override
    Optional<String> description() {
        return Optional.of("perform a database select")
    }

    @Override
    Optional<String> example() {
        return Optional.of("select * from some_table")
    }
}