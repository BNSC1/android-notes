//https://refactoring.guru/design-patterns/chain-of-responsibility
class Main {
    @Test
    fun main() {
        /*
        5. The client may either assemble chains on its own or receive pre-built chains from other objects.
        In the latter case, you must implement some factory classes to build chains according to the configuration or environment settings.
        */
        val boss = Boss()
        val manager = Manager().apply {
            setNextHandler(boss)
        }
        val waiter = Waiter().apply {
            setNextHandler(manager)
        }
        /*
        6. Due to the dynamic nature of the chain, the client should be ready to handle the following scenarios:

        - The chain may consist of a single link.
        - Some requests may not reach the end of the chain.
        - Others may reach the end of the chain unhandled.
        */
        waiter.handle("Hi, I want to order") //Hi I'm a waiter, what would you like today?
        waiter.handle("Hi, may I have a talk to your boss for cooperation") //Hi I'm the boss, what would you like today?
        waiter.handle("Hey, I want to talk to your manager for suggestions") //Hi I'm the manager, what would you like today?
        waiter.handle("Adududuhawa?") //Waiter handles this maniac and does not need the manager or the boss to show up
    }
}

/*
1. Declare the handler interface and describe the signature of a method for handling requests.

Decide how the client will pass the request data into the method.
The most flexible way is to convert the request into an object and pass it to the handling method as an argument.
 */
interface Handler {
    fun setNextHandler(handler: Handler)
    fun handle(request: String)
}

/*
2. To eliminate duplicate boilerplate code in concrete handlers, it might be worth creating an abstract base handler class, derived from the handler interface.

This class should have a field for storing a reference to the next handler in the chain. Consider making the class immutable.
However, if you plan to modify chains at runtime, you need to define a setter for altering the value of the reference field.

You can also implement the convenient default behavior for the handling method, which is to forward the request to the next object unless there’s none left. Concrete handlers will be able to use this behavior by calling the parent method.
*/
abstract class BaseHandler : Handler {
    @JvmField
    protected var nextHandler: Handler? = null
    override fun setNextHandler(handler: Handler) {
        nextHandler = handler
    }

    override fun handle(request: String) {
        nextHandler?.handle(request)
    }
}

/*
3. One by one create concrete handler subclasses and implement their handling methods. Each handler should make two decisions when receiving a request:

- Whether it’ll process the request.
- Whether it’ll pass the request along the chain.
*/
class Waiter : BaseHandler() {
    override fun handle(request: String) {
        if (request.contains("manager") || request.contains("boss")) {
            nextHandler?.handle(request) //let the next handler (manager) handle this person
        } else {
            println("Hi I'm a waiter, what would you like today?")
        }
    }
}

class Manager : BaseHandler() {
    override fun handle(request: String) {
        if (request.contains("boss")) {
            nextHandler?.handle(request) //let the next handler (boss) handle this person
        } else {
            println("Hi I'm the manager, what would you like today?")
        }
    }
}

class Boss : BaseHandler() {
    override fun handle(request: String) {
        println("Hi I'm the boss, what would you like today?")
    }
}