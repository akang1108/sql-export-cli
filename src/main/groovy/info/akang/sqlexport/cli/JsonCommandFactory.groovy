package info.akang.sqlexport.cli

class JsonCommandFactory implements CommandFactory {

    @Override
    Command createCommand() {
        return new JsonCommand()
    }

    @Override
    String name() {
        return "json"
    }
}