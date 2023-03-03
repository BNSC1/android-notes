//https://refactoring.guru/design-patterns/memento

enum class State {
    NORMAL, ERROR
}

/*
1. Determine what class will play the role of the originator.
   It’s important to know whether the program uses one central object of this type or multiple smaller ones.
*/
class VirtualMachine(var context: String, private var state: State = State.NORMAL) {
    fun setError() {
        state = State.ERROR
    }
    fun getState() = state
    /*
    2. Create the memento class.
       One by one, declare a set of fields that mirror the fields declared inside the originator class.
    */
    /*
    3. Make the memento class immutable.
       A memento should accept the data just once, via the constructor.
       The class should have no setters.
    */
    /*
    4. If your programming language supports nested classes, nest the memento inside the originator.
       If not, extract a blank interface from the memento class and make all other objects use it to refer to the memento.
       You may add some metadata operations to the interface, but nothing that exposes the originator’s state.
    */
    inner class VirtualMachineSnapshot(private val state: State, private val context: String) {
        /*
        6. Add a method for restoring the originator’s state to its class.
           It should accept a memento object as an argument.
           If you extracted an interface in the previous step, make it the type of the parameter.
           In this case, you need to typecast the incoming object to the memento class, since the originator needs full access to that object.
        */
        fun restore() {
            this@VirtualMachine.state = this.state
            this@VirtualMachine.context = this.context
        }
    }

    /*
    5. Add a method for producing mementos to the originator class.
       The originator should pass its state to the memento via one or multiple arguments of the memento’s constructor.

       The return type of the method should be of the interface you extracted in the previous step (assuming that you extracted it at all).
       Under the hood, the memento-producing method should work directly with the memento class.
    */
    fun saveToSnapshot(): VirtualMachineSnapshot {
        if (state != State.ERROR) {
            return VirtualMachineSnapshot(state, context)
        } else throw IllegalStateException("VM is abnormal!")
    }
}

/*
7. The caretaker, whether it represents a command object, a history, or something entirely different,
   should know when to request new mementos from the originator, how to store them and when to restore the originator with a particular memento.
*/
/*
8. The link between caretakers and originators may be moved into the memento class.
   In this case, each memento must be connected to the originator that had created it.
   The restoration method would also move to the memento class.
   However, this would all make sense only if the memento class is nested into originator or the originator class provides sufficient setters for overriding its state.
*/
class VirtualMachineManager {
    @Test
    fun main() {
        val vm = VirtualMachine("Working machine! Yay!")
        val snapshot = vm.saveToSnapshot()
        getVirtualMachineInfo(vm) //NORMAL: Working machine! Yay!

        //error occurs
        vm.context = "X_X Error"
        vm.setError()
        getVirtualMachineInfo(vm) //ERROR: X_X Error

        //restore to snapshot
        snapshot.restore()
        getVirtualMachineInfo(vm) //NORMAL: Working machine! Yay!
    }

    private fun getVirtualMachineInfo(vm: VirtualMachine) {
        println("${vm.getState()}: ${vm.context}")
    }
}