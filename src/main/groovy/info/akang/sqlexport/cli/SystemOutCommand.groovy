package info.akang.sqlexport.cli

class SystemOutCommand implements OutputCommand {

    @Override
    def execute(def input) {
        println input

        nextCommand.execute(input)
    }

}
