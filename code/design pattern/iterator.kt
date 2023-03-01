class Main {
    @Test
    fun main() {
        val backpack = Backpack(listOf("Toilet Paper", "Mirror", "Kettle"))
        val iterator = backpack.createItemIterator()
        /* 
        5. Go over the client code to replace all of the collection traversal code with the use of iterators.
           The client fetches a new iterator object each time it needs to iterate over the collection elements.
        */
        println("Look what I found... ${iterator.getNext().name}") //Toilet Paper
        println("Look what I found... ${iterator.getNext().name}") //Mirror
        println("Look what I found... ${iterator.getNext().name}") //Kettle
        println("Look what I found... ${iterator.getNext().name}") //null
    }
}

data class Item(val name: String)

/*
1. Declare the iterator interface.
   At the very least, it must have a method for fetching the next element from a collection.
   But for the sake of convenience you can add a couple of other methods, such as fetching the previous element, tracking the current position, and checking the end of the iteration.
*/
interface ItemIterator {
    fun getNext(): Item
    fun hasNext() : Boolean
}

/* 
3. Implement concrete iterator classes for the collections that you want to be traversable with iterators.
   An iterator object must be linked with a single collection instance.
   Usually, this link is established via the iterator’s constructor.
*/
class BackpackIterator(private val backpack: Backpack): ItemIterator {
    private var ptr = 0
    override fun getNext(item: String): Item? {
        if (hasNext()) {
            backpack.item[++ptr]
        }
        return null //no more items
    }
    override fun hasNext() = backpack.items.size > ptr
}

/*
2. Declare the collection interface and describe a method for fetching iterators. 
   The return type should be equal to that of the iterator interface.
   You may declare similar methods if you plan to have several distinct groups of iterators.
*/
interface Storage {
    fun createItemIterator(): ItemIterator
}

/* 
4. Implement the collection interface in your collection classes.
   The main idea is to provide the client with a shortcut for creating iterators, tailored for a particular collection class.
   The collection object must pass itself to the iterator’s constructor to establish a link between them.
*/
class Backpack(val items: List<Item>): Storage {
    override fun createItemIterator() : ItemIterator {
        return BackpackIterator(this)
    }
}