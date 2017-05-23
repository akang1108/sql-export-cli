package info.akang.sqlexport.cli

import info.akang.sqlexport.database.DataSourceFactory

class Main {

    Map<String, Command> commands = [:]

    DataSourceFactory dsFactory = new DataSourceFactory()

    static void main(String[] args) {
        println "$programNamePretty"

        def main = new Main()

        // Load config
        main.loadCommands()
        main.input()
    }

    def loadCommands() {
        addCommand new Exit()
        addCommand new Help(commands.values())
        addCommand new Select(dsFactory.ds, new TableResultHandler())
    }

    def addCommand(Command command) {
        commands[ command.name() ] = command
    }

    def input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))

        while (true) {
            try {
                print "sql > "
                def line = br.readLine()
                line = line.trim()

                if (line == '') {
                    continue
                }



                def command = findCommand(line)

                command.execute(line)
            } catch (UnknownCommandException ex) {
                println "unrecognized command"
            }
        }
    }

    Command findCommand(String line) {
        int index = line.indexOf(" ")
        String commandName = (index == -1) ? line : line.substring(0, index)
        commandName = commandName.toLowerCase()

        println "$commandName  $commands"


        Command command = commands[commandName]

        if (command == null) {
            throw new UnknownCommandException()
        }

        command
    }

    static String programNamePretty = """
      _/_/_/    _/_/      _/           _/_/_/_/                                            _/           _/_/_/  _/        _/_/_/   
   _/        _/    _/    _/           _/        _/    _/  _/_/_/      _/_/    _/  _/_/  _/_/_/_/     _/        _/          _/      
    _/_/    _/  _/_/    _/           _/_/_/      _/_/    _/    _/  _/    _/  _/_/        _/         _/        _/          _/       
       _/  _/    _/    _/           _/        _/    _/  _/    _/  _/    _/  _/          _/         _/        _/          _/        
_/_/_/      _/_/  _/  _/_/_/_/     _/_/_/_/  _/    _/  _/_/_/      _/_/    _/            _/_/       _/_/_/  _/_/_/_/  _/_/_/       
    """
}



