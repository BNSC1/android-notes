//https://refactoring.guru/design-patterns/mediator
/*
1. Identify a group of tightly coupled classes which would benefit from being more independent
   (e.g., for easier maintenance or simpler reuse of these classes).
*/
/*
2. Declare the mediator interface and describe the desired communication protocol between mediators and various components.
   In most cases, a single method for receiving notifications from components is sufficient.

   This interface is crucial when you want to reuse component classes in different contexts.
   As long as the component works with its mediator via the generic interface, you can link the component with a different implementation of the mediator.
*/
interface Commander {
    fun notify(sender: Soldier, msg: String)
}

/*
3. Implement the concrete mediator class. Consider storing references to all components inside the mediator.
   This way, you could call any component from the mediator’s methods.
*/
/*
4. You can go even further and make the mediator responsible for the creation and destruction of component objects.
   After this, the mediator may resemble a factory or a facade.
*/
class CommandCenter : Commander {
    lateinit var scout: Scout
    lateinit var mortarOperator1: MortarOperator
    lateinit var mortarOperator2: MortarOperator

    override fun notify(sender: Soldier, msg: String) {
        if (sender is Scout && msg == "Incoming!") {
            mortarOperator1.fire()
            mortarOperator2.fire()
        }
    }
}

/*
5. Components should store a reference to the mediator object.
   The connection is usually established in the component’s constructor, where a mediator object is passed as an argument.
*/
/*
6. Change the components’ code so that they call the mediator’s notification method instead of methods on other components.
   Extract the code that involves calling other components into the mediator class.
   Execute this code whenever the mediator receives notifications from that component.
*/
abstract class Soldier {
    protected abstract val commander: Commander
    open fun notify(msg: String) {}
}

class Scout(override val commander: Commander) : Soldier() {
    override fun notify(msg: String) {
        commander.notify(this, msg)
    }
}

class MortarOperator(override val commander: Commander) : Soldier() {
    fun fire() {
        println("*BANG*")
    }
}

class Main {
    @Test
    fun main() {
        val commander = CommandCenter()
        val scout = Scout(commander)
        val mortarOperator1 = MortarOperator(commander)
        val mortarOperator2 = MortarOperator(commander)
        commander.also {
            it.mortarOperator1 = mortarOperator1
            it.mortarOperator2 = mortarOperator2
            it.scout = scout
        }

        scout.notify("Incoming!")
        //mortarOperator1: *BANG*
        //mortarOperator1: *BANG*
    }
}