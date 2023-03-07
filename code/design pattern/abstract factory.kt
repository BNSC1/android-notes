//https://refactoring.guru/design-patterns/abstract-factory
/* 
1. Map out a matrix of distinct product types versus variants of these products.
*/

/* 
2. Declare abstract product interfaces for all product types. Then make all concrete product classes implement these interfaces.
*/
interface Mouse

class GamingMouse: Mouse

class OfficeMouse: Mouse

interface Keyboard

class GamingKeyboard: Keyboard

class OfficeKeyboard: Keyboard

/* 
3. Declare the abstract factory interface with a set of creation methods for all abstract products.
*/
interface Factory {
    abstract fun makeKeyboard(): Keyboard
    abstract fun makeMouse(): Mouse
}

/* 
4. Implement a set of concrete factory classes, one for each product variant.
*/
class GamingFactory: Factory{
    override fun makeKeyboard() = GamingKeyboard()
    override fun makeMouse() = GamingMouse()
}

class OfficeFactory: Factory {
    override fun makeKeyboard() = OfficeKeyboard()
    override fun makeMouse() = OfficeMouse()
}

class Main {
    lateinit var factory: Factory //flavor unknown until init
    @Test
    fun main() {
        /* 
        5. Create factory initialization code somewhere in the app.
           It should instantiate one of the concrete factory classes, depending on the application configuration or the current environment.
           Pass this factory object to all classes that construct products.
        */
        /* 
        6. Scan through the code and find all direct calls to product constructors.
           Replace them with calls to the appropriate creation method on the factory object.
        */
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