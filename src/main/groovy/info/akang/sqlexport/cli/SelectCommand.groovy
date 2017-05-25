package info.akang.sqlexport.cli

import groovy.sql.GroovyRowResult
import groovy.sql.Sql

import javax.sql.DataSource
import java.sql.SQLException

class SelectCommand implements QueryCommand {

    DataSource ds

    SelectCommand(DataSource ds) {
        this.ds = ds
    }

    @Override
    def execute(def input) {
        input = input as String

        Sql sql = new Sql(ds)

        List<GroovyRowResult> rows

        try {
            rows = sql.rows(input)
        } catch (SQLException ex) {
            ex.printStackTrace()
        }

        nextCommand.execute(rows)
    }


}