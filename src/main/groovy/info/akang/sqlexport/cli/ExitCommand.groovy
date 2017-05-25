package info.akang.sqlexport.cli

class ExitCommand implements Command {

    @Override
    def execute(def input) {
        println "Exiting..."

        System.exit(0)
    }
}