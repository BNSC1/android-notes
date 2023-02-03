class Main {
    lateinit var factory: Factory //flavor unknown until init
    @Test
    fun main() {
        factory = when (FLAVOR) {
            "Gaming" -> GamingFactory()
            "Office" -> OfficeFactory()
            else -> throw IllegalStateException("Unknown flavor!")
        }
        factory.makeMouse() `should be instance of` GamingMouse::class
        factory.makeKeyboard() `should be instance of` GamingKeyboard::class //got gaming mouse/keyboard
    }
    companion object {
        const val FLAVOR = "Gaming"

    }
}

interface Mouse

class GamingMouse: Mouse

class OfficeMouse: Mouse

interface Keyboard

class GamingKeyboard: Keyboard

class OfficeKeyboard: Keyboard

interface Factory {
    abstract fun makeKeyboard(): Keyboard
    abstract fun makeMouse(): Mouse
}

class GamingFactory: Factory{
    override fun makeKeyboard() = GamingKeyboard()
    override fun makeMouse() = GamingMouse()
}

class OfficeFactory: Factory {
    override fun makeKeyboard() = OfficeKeyboard()
    override fun makeMouse() = OfficeMouse()
}