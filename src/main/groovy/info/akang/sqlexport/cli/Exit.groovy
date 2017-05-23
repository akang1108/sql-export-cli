package info.akang.sqlexport.cli

class Exit implements Command {

    @Override
    String name() {
        return "exit"
    }

    @Override
    def execute(String line) {
        println "Exiting..."

        System.exit(0)
    }
}