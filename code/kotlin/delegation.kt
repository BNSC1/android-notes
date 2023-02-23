//https://typealias.com/start/kotlin-delegation/
class Main {
    @Test
    fun main() {
        val waiter = Waiter(Chef(), Bartender())

        waiter.prepareBeverage("Soda")
        waiter.prepareBeverage("Red Wine")
        waiter.prepareMeal()
    }
}

interface KitchenStaff {
    fun prepareMeal()
}

interface BarStaff {
    fun prepareBeverage(name: String)
}

class Waiter(
    chef: Chef,
    private val bartender: Bartender,
): KitchenStaff by chef, BarStaff by bartender { //use "by" to delegate method implementations to arguments, so the waiter does not need to know how to make meals
    override fun prepareBeverage(name: String) {
        if(name == "Soda") {
            println("waiter prepares $name") //waiter is capable of making soda
        } else { //if it is something the waiter can't make, delegate it to the bartender
            bartender.prepareBeverage(name)
        }
    }
}

class Chef: KitchenStaff {
    override fun prepareMeal() {
        println("chef prepares meal")
    }
}

class Bartender: BarStaff {
    override fun prepareBeverage(name: String) {
        println("bartender prepares $name")
    }
}