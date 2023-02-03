class Main {
    lateinit var button: Button //not aware of what button to display

    @Test
    fun main() {
        button = when (SYSTEM) {
            "Android" -> AndroidButton
            "iOS" -> IOsButton
            else -> throw IllegalArgumentException("Can't detect OS!")
        }
        button.onClick() //I am Android button
    }

    companion object {
        const val SYSTEM = "Android"
    }
}

abstract class Button {
    fun onClick() {
        println(msg.getMessage())
    }

    abstract val msg: Message //not aware of what message to display
}

object AndroidButton : Button() {
    override val msg = AndroidMessage
}

object IOsButton : Button() {
    override val msg = IOsMessage
}

abstract class Message {
    abstract fun getMessage(): String
}

object AndroidMessage : Message() {
    override fun getMessage() = "I am Android button"
}

object IOsMessage : Message() {
    override fun getMessage() = "I am iOS button"
}
