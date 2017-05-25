package info.akang.sqlexport.cli

trait Command {

    Command nextCommand

    abstract def execute(def input)

}