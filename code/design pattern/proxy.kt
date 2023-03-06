//https://refactoring.guru/design-patterns/proxy
/*
1. If there’s no pre-existing service interface, create one to make proxy and service objects interchangeable.
   Extracting the interface from the service class isn’t always possible, because you’d need to change all of the service’s clients to use that interface.
   Plan B is to make the proxy a subclass of the service class, and this way it’ll inherit the interface of the service.
*/
class God private constructor() : DemandsOffering {
    override fun demand() = "fruits"
    val isPubliclyAvailable = false //do not disturb me!

    companion object {
        private var instance: God? = null

        fun getInstance() =
            instance ?: let {
                instance = God()
                instance!!
            }
    }
}

interface DemandsOffering {
    fun demand(): String
}

/*
2. Create the proxy class.
   It should have a field for storing a reference to the service.
   Usually, proxies create and manage the whole life cycle of their services. On rare occasions, a service is passed to the proxy via a constructor by the client.
*/
class Clergy : DemandsOffering {
    /*
    5. Consider implementing lazy initialization for the service object.
    */
    private val god = God.getInstance()
    private var decree: String = ""

    /*
    3. Implement the proxy methods according to their purposes.
       In most cases, after doing some work, the proxy should delegate the work to the service object.
    */
    override fun demand(): String {
        if (god.isPubliclyAvailable) {
            return god.demand()
        } else {
            if (decree.isEmpty()) { //cache
                decree = "The god wants ${god.demand()} today"
            }
        }
        return decree
    }
}

/*
4. Consider introducing a creation method that decides whether the client gets a proxy or a real service.
   This can be a simple static method in the proxy class or a full-blown factory method.
*/
class Main {
    @Test
    fun main() {
        val demandsOffering = Clergy()
        println(demandsOffering.demand())
    }
}