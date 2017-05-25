package info.akang.sqlexport.cli

class CommandPipeline {

    Map<String, Command> commandMap = [:]

    List<Command> commands = []

    CommandPipeline(String line, Map<String, Command> commandMap) {
        this.commandMap = commandMap
    }

    def execute() {
        def lastOutput

        commands.each { command ->
            lastOutput = command.execute(lastOutput)
        }

//        if (commands.last() instanceof Query) {
//            commandMap['table'].execute(lastOutput)
//        }
    }

    private Command findCommand(String commandString) {
        int index = line.indexOf(" ")
        String commandName = (index == -1) ? line : line.substring(0, index)
        commandName = commandName.toLowerCase()

        Command command = commands[commandName]

        if (command == null) {
            throw new UnknownCommandException("Unknown command string $commandString")
        }

        command
    }


}