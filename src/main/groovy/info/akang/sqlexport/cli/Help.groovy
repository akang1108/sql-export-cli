package info.akang.sqlexport.cli

class Help implements Command {

    private Collection<Command> commands

    Help(Collection<Command> commands) {
        this.commands = commands
    }

    @Override
    String name() {
        return "help"
    }

    @Override
    def execute(String line) {
        println ""
        Colors.printlnHeader "Help"
        Colors.printlnHeader "----"

        this.commands.each { command ->
            Colors.printKey "command: "
            print "${command.name()}"

            if (command.description().isPresent()) {
                Colors.printKey "  description: "
                print "${command.description().get()}"
            }

            if (command.example().isPresent()) {
                Colors.printKey "  example: "
                print "${command.example().get()}"
            }

        }

        println ""
    }
}