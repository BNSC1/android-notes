// https://refactoring.guru/design-patterns/template-method
/*
1. Analyze the target algorithm to see whether you can break it into steps. Consider which steps are common to all subclasses and which ones will always be unique.
*/
class Main {
    @Test
    fun main() {
        val japaneseBartender = JapaneseBartender()
        val taiwaneseBartender = TaiwaneseBartender()
        japaneseBartender.reviewRecipes()
        taiwaneseBartender.reviewRecipes()

        japaneseBartender.makeSpecialityDrinks() //I just made [sake, matcha, umeshu]
        taiwaneseBartender.makeSpecialityDrinks() //I just made [watermelon juice, bubble tea, salted cream cheese tea]
    }
}

/*
2. Create the abstract base class and declare the template method and a set of abstract methods representing the algorithm’s steps.
Outline the algorithm’s structure in the template method by executing corresponding steps.
Consider making the template method final to prevent subclasses from overriding it.
*/
abstract class Bartender {
    protected val specialityDrinks = mutableListOf<String>()
    abstract fun reviewRecipes()
    /*
    3. It’s okay if all the steps end up being abstract.
       However, some steps might benefit from having a default implementation.
       Subclasses don’t have to implement those methods.
    */
    /*
    4. Think of adding hooks between the crucial steps of the algorithm.
    */
    fun makeSpecialityDrinks() =
        println("I just made $specialityDrinks")
}

/*
5. For each variation of the algorithm, create a new concrete subclass.
   It must implement all of the abstract steps, but may also override some of the optional ones.
*/
class JapaneseBartender: Bartender() {
    override fun reviewRecipes() {
        specialityDrinks.addAll(listOf("sake", "matcha", "umeshu"))
    }
}

class TaiwaneseBartender: Bartender() {
    override fun reviewRecipes() {
        specialityDrinks.addAll(listOf("watermelon juice", "bubble tea", "salted cream cheese tea"))
    }
}