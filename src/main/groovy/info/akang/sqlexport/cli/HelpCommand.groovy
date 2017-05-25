package info.akang.sqlexport.cli

class HelpCommand implements Command {

    private Collection<CommandFactory> commandFactories

    HelpCommand(Collection<CommandFactory> commandFactories) {
        this.commandFactories = commandFactories
    }

    @Override
    def execute(def input) {
        println ""
        Colors.printlnHeader "Help"
        Colors.printlnHeader "----"

        this.commandFactories.each { commandFactory ->
            Colors.printKey "command: "
            print "${commandFactory.name()}"

            if (commandFactory.description().isPresent()) {
                Colors.printKey "  description: "
                print "${commandFactory.description().get()}"
            }

            if (commandFactory.example().isPresent()) {
                Colors.printKey "  example: "
                print "${commandFactory.example().get()}"
            }

            println ""
        }

        println ""
    }
}