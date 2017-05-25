package info.akang.sqlexport.cli

class HelpCommandFactory implements CommandFactory {

    Collection<CommandFactory> commandFactories

    HelpCommandFactory(Collection<CommandFactory> commandFactories) {
        this.commandFactories = commandFactories
    }

    @Override
    Command createCommand() {
        return new HelpCommand(commandFactories)
    }

    @Override
    String name() {
        return "help"
    }
}