package info.akang.sqlexport.cli

import info.akang.sqlexport.database.DataSourceFactory

class Main {

    Map<String, CommandFactory> commandFactories = [:]

    DataSourceFactory dsFactory = new DataSourceFactory()

    static void main(String[] args) {
        println "$programNamePretty"

        def main = new Main()

        // TODO: Load config

        main.loadCommandFactories()
        main.run()
    }

    def loadCommandFactories() {
        addCommandFactory new ExitCommandFactory()
        addCommandFactory new HelpCommandFactory(commandFactories.values())
        addCommandFactory new SelectCommandFactory(dsFactory.ds)
        addCommandFactory new JsonCommandFactory()
    }

    def addCommandFactory(CommandFactory commandFactory) {
        commandFactories[ commandFactory.name() ] = commandFactory
    }

    def run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))

        while (true) {
            try {
                print "sql > "
                def line = br.readLine()
                line = line.trim()

                if (line == '') {
                    continue
                }

                Tuple2<Command, String> commandAndPhrase = createCommand(line)

                commandAndPhrase.first.execute(commandAndPhrase.second)
            } catch (UnknownCommandException ex) {
                println "unrecognized command ${ex.message}"
            }
        }
    }

    Tuple2<Command, String> createCommand(String line) {
        String[] lineSplit = line.split("\\|")

        List<Command> commands = []
        String firstPhrase

        def processPhrase = { phrase ->
            phrase = phrase.trim()

            if (! firstPhrase) {
                firstPhrase = phrase
            }

            int index = phrase.indexOf(" ")
            String commandName = (index == -1) ? phrase : phrase.substring(0, index)
            commandName = commandName.toLowerCase()

            CommandFactory commandFactory = commandFactories[commandName]

            if (commandFactory == null) {
                throw new UnknownCommandException(commandName)
            }

            commands << commandFactory.createCommand()
        }

        lineSplit.each { phrase ->

            if (phrase.indexOf(">") != -1) {
                def phraseSplit = phrase.split(">")
                processPhrase(phraseSplit[0])

            }

            if (phrase.indexOf(">>") != -1) {

            }

            processPhrase(phrase)
        }

        Command firstCommand
        Command previousCommand

        // Default query format is tabular format and default output is System.out
        if (commands.last() instanceof QueryCommand) {
            commands << new TableCommand()
        }
        if (! (commands.last() instanceof OutputCommand)) {
            commands << new SystemOutCommand()
        }

        commands << new VoidCommand()

        commands.each { command ->
            if (! firstCommand) {
                firstCommand = command
                previousCommand = command
            } else {
                previousCommand.nextCommand = command
            }

            previousCommand = command
        }

        new Tuple2<Command, String>(firstCommand, firstPhrase)
    }

    static String programNamePretty = """
      _/_/_/    _/_/      _/           _/_/_/_/                                            _/           _/_/_/  _/        _/_/_/   
   _/        _/    _/    _/           _/        _/    _/  _/_/_/      _/_/    _/  _/_/  _/_/_/_/     _/        _/          _/      
    _/_/    _/  _/_/    _/           _/_/_/      _/_/    _/    _/  _/    _/  _/_/        _/         _/        _/          _/       
       _/  _/    _/    _/           _/        _/    _/  _/    _/  _/    _/  _/          _/         _/        _/          _/        
_/_/_/      _/_/  _/  _/_/_/_/     _/_/_/_/  _/    _/  _/_/_/      _/_/    _/            _/_/       _/_/_/  _/_/_/_/  _/_/_/       
    """
}



