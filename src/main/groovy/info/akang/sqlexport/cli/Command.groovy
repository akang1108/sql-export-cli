package info.akang.sqlexport.cli

trait Command {

    abstract String name()

    abstract Object execute(String line)

    Optional<String> description() {
        return Optional.empty()
    }

    Optional<String> example() {
        return Optional.empty()
    }

}