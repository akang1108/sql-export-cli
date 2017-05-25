package info.akang.sqlexport.cli

class FileOutCommand implements OutputCommand {

    File file

    boolean append

    FileOutCommand(String path, boolean append) {

        this.file = new File(path)

        this.append = append
    }

    @Override
    def execute(def input) {

        input = input as String

        if (this.append) {
            file.append input
        } else {
            file.write input
        }


        nextCommand.execute(input)
    }

}
