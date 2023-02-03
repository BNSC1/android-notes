class Main {
    @Test
    fun main() {
        val euSocket = EuSocket()
        val usPlug = UsPlug(10)
        val euPlug = EuPlug(20)
        euSocket.plugin(euPlug) //works
        //euSocket.plugin(usPlug) //type mismatch because EuSocket expects Europe plug
        euSocket.plugin(UsToEuPlugAdapter(usPlug)) //works now
    }
}

class UsPlug(val watt: Int)

open class EuPlug(val watt: Int)

class EuSocket() {
    private var plug: EuPlug? = null
    fun plugin(plug: EuPlug) {
        if (this.plug == null) {
            this.plug = plug
        } else throw IllegalStateException("Unplug first!")
    }

    fun unplug() {
        plug = null
    }
}

class UsToEuPlugAdapter(plug: UsPlug) : EuPlug(plug.watt)