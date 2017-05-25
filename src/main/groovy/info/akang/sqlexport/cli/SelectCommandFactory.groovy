package info.akang.sqlexport.cli

import javax.sql.DataSource

class SelectCommandFactory implements CommandFactory {

    DataSource ds

    SelectCommandFactory(DataSource ds) {
        this.ds = ds
    }

    @Override
    Command createCommand() {
        return new SelectCommand(ds)
    }

    @Override
    String name() {
        return "select"
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