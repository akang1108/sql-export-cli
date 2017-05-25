package info.akang.sqlexport.cli

class ExitCommandFactory implements CommandFactory {

    @Override
    Command createCommand() {
        return new ExitCommand()
    }

    @Override
    String name() {
        return "exit"
    }
}