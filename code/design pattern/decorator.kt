//https://refactoring.guru/design-patterns/decorator
class Main {
    @Test
    fun main() {
        //1. Make sure your business domain can be represented as a primary component with multiple optional layers over it.
        var person: Funny = FunnyPerson()
        person.beFunny() //funny!
        //7. The client code must be responsible for creating decorators and composing them in the way the client needs.
        person = RegularClownSuit(person)
        person.beFunny() //funny! extra funny!

        person = SpookyClownSuit(person)
        person.beFunny() //funny! extra funny! now that's a bit spooky...
    }
}

interface Funny {
    //2. Figure out what methods are common to both the primary component and the optional layers.
    // Create a component interface and declare those methods there.
    fun beFunny()
}

class FunnyPerson: Funny {
    //3. Create a concrete component class and define the base behavior in it.
    override fun beFunny() {
        println("funny!")
    }
}

//Base decorator
abstract class ClownSuit(private val wearer: Funny): Funny {
    //4. Create a base decorator class.
    // It should have a field for storing a reference to a wrapped object.
    // The field should be declared with the component interface type to allow linking to concrete components as well as decorators.
    // The base decorator must delegate all work to the wrapped object.
    //5. Make sure all classes implement the component interface.
    override fun beFunny() {
        wearer.beFunny()
    }
}

//Concrete decorators
class RegularClownSuit(wearer: Funny): ClownSuit(wearer) {
    //6. Create concrete decorators by extending them from the base decorator.
    // A concrete decorator must execute its behavior before or after the call to the parent method (which always delegates to the wrapped object).
    override fun beFunny() {
        super.beFunny()
        println("extra clown funny!")
    }
}

class SpookyClownSuit(wearer: Funny): ClownSuit(wearer) {
    override fun beFunny() {
        super.beFunny()
        println("now that's a bit spooky...")
    }
}