class Main {
    @Test
    fun main() {
        val pc = Computer()
        pc.boot() //despite the complexity of a boot process, the user only needs to know how to press the power up button
    }
}

interface Component {
    fun init()
}

class CPU: Component {
    override fun init() {
        //init CPU
    }
}

class RAM: Component {
    override fun init() {
        //init RAM
    }
}

class Motherboard: Component {
    private val cpu = CPU()
    private val ram = RAM()
    override fun init() {
        //init components
        cpu.init()
        ram.init()
    }
}

class Computer {
    private val motherboard = Motherboard()

    fun boot() {
        motherboard.init()
        println("beep")
    }
}