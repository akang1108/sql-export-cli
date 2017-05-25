package info.akang.sqlexport.cli

trait CommandFactory {

    abstract Command createCommand()

    abstract String name()

    Optional<String> description() {
        return Optional.empty()
    }

    Optional<String> example() {
        return Optional.empty()
    }
}