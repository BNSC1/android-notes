//https://refactoring.guru/design-patterns/composite

/*
1. Make sure that the core model of your app can be represented as a tree structure.
   Try to break it down into simple elements and containers.
   Remember that containers must be able to contain both simple elements and other containers.
*/
/*
2. Declare the component interface with a list of methods that make sense for both simple and complex components.
*/
interface ProductionLine {
    fun produce(product: String): String
}

/*
3. Create a leaf class to represent simple elements. A program may have multiple different leaf classes.
*/
class Worker: ProductionLine {
    override fun produce(product: String): String {
        println(product)
        return product
    }
}

/*
4. Create a container class to represent complex elements.
   In this class, provide an array field for storing references to sub-elements.
   The array must be able to store both leaves and containers, so make sure it’s declared with the component interface type.

   While implementing the methods of the component interface, remember that a container is supposed to be delegating most of the work to sub-elements.
*/
class WorkerLeader: ProductionLine {
    private val workers = mutableListOf<Worker>()
    override fun produce(product: String): String {
        val assemble = StringBuilder()
        if (workers.isNotEmpty()) {
            var start = 0F
            val offset = product.length / workers.size.toFloat()
            workers.forEach {
                val end = start + offset
                val p = it.produce(product.substring(start.roundToInt(), end.roundToInt()))
                assemble.append(p)
                start = end
            }
        }
        return assemble.toString()
    }
    fun addWorker(worker: Worker) = workers.add(worker)
}

/*
5. Finally, define the methods for adding and removal of child elements in the container.

   Keep in mind that these operations can be declared in the component interface.
   This would violate the Interface Segregation Principle because the methods will be empty in the leaf class.
   However, the client will be able to treat all the elements equally, even when composing the tree.
*/
class Main {
    @Test
    fun main() {
        val workerLeader = WorkerLeader().apply {
            addWorker(Worker()) //gold
            addWorker(Worker()) //en w
            addWorker(Worker()) //atch
        }
        println(workerLeader.produce("golden watch")) //golden watch
    }
}