//https://refactoring.guru/design-patterns/command
/*
1. Declare the command interface with a single execution method.
*/
abstract class Command(protected val textEditor: TextEditor) {
    abstract fun run()
}

/*
2. Start extracting requests into concrete command classes that implement the command interface.
   Each class must have a set of fields for storing the request arguments along with a reference to the actual receiver object.
   All these values must be initialized via the command’s constructor.
*/
class ActionOpenFile(textEditor: TextEditor) : Command(textEditor) {
    override fun run() {
        println("file opened in ${textEditor}...")
    }
}

class ActionSaveFile(textEditor: TextEditor) : Command(textEditor) {
    override fun run() {
        println("file saved in ${textEditor}...")
    }
}

class TextEditor

/*
3. Identify classes that will act as senders.
   Add the fields for storing commands into these classes.
   Senders should communicate with their commands only via the command interface.
   Senders usually don’t create command objects on their own, but rather get them from the client code.
*/
/*
4. Change the senders so they execute the command instead of sending a request to the receiver directly.
*/
class Toolbar(private val onOpenClick: Command, private val onSaveClick: Command) {
    fun clickOpen() = onOpenClick.run()
    fun clickSave() = onSaveClick.run()
}

/*
5. The client should initialize objects in the following order:
   - Create receivers.
   - Create commands, and associate them with receivers if needed.
   - Create senders, and associate them with specific commands.
*/
class Main {
    @Test
    fun main() {
        val textEditor = TextEditor() //receiver
        val actionOpenFile = ActionOpenFile(textEditor)
        val actionSaveFile = ActionSaveFile(textEditor) //commands
        val toolbar = Toolbar(actionOpenFile, actionSaveFile) //sender
        toolbar.clickOpen() //file opened in TextEditor@e57b96d...
        toolbar.clickSave() //file saved in TextEditor@e57b96d...
    }
}