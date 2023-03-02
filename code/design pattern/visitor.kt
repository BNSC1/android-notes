//https://refactoring.guru/design-patterns/visitor

/*
1. Declare the visitor interface with a set of “visiting” methods, one per each concrete element class that exists in the program.
*/
interface Visitor {
    fun visitDepressed(d: Depressed)
    fun visitAnxious(a: Anxious)
    fun visitRetarded(r: Retarded)
}

/*
2. Declare the element interface. If you’re working with an existing element class hierarchy, add the abstract “acceptance” method to the base class of the hierarchy.
   This method should accept a visitor object as an argument.
*/
interface Patient {
    fun interview() {
        println("getting interviewed...")
    }

    fun acceptAdvice(v: Visitor)
}

/*
3. Implement the acceptance methods in all concrete element classes.
   These methods must simply redirect the call to a visiting method on the incoming visitor object which matches the class of the current element.
*/
/*
4. The element classes should only work with visitors via the visitor interface.
   Visitors, however, must be aware of all concrete element classes, referenced as parameter types of the visiting methods.
*/
class Depressed : Patient {
    override fun acceptAdvice(v: Visitor) {
        v.visitDepressed(this)
    }
}

class Anxious : Patient {
    override fun acceptAdvice(v: Visitor) {
        v.visitAnxious(this)
    }
}

class Retarded : Patient {
    override fun acceptAdvice(v: Visitor) {
        v.visitRetarded(this)
    }
}

class Psychologist : Visitor {
    override fun visitDepressed(d: Depressed) {
        println("Gives anti-depressant drug")
    }

    override fun visitAnxious(a: Anxious) {
        println("Gives anti-anxiety drug")
    }

    override fun visitRetarded(r: Retarded) {
        println("Gives anti-psychotic drug")
    }
}

/*
5. For each behavior that can’t be implemented inside the element hierarchy, create a new concrete visitor class and implement all of the visiting methods.

  You might encounter a situation where the visitor will need access to some private members of the element class.
  In this case, you can either make these fields or methods public, violating the element’s encapsulation, or nest the visitor class in the element class.
  The latter is only possible if you’re lucky to work with a programming language that supports nested classes.
*/
class Main {
    @Test
    fun main() {
        /*
        6. The client must create visitor objects and pass them into elements via “acceptance” methods.
        */
        val patients = listOf(Depressed(), Anxious(), Retarded())
        val psychologist = Psychologist()
        patients.forEach {
            it.interview()
            it.acceptAdvice(psychologist)
        }
    }
}