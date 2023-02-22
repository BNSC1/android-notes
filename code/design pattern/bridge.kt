//https://refactoring.guru/design-patterns/bridge
class Main {
    @Test
    fun main() {
        //1. Identify the orthogonal dimensions in your classes. These independent concepts could be: abstraction/platform, domain/infrastructure, front-end/back-end, or interface/implementation.
        val desktop = Desktop()
        val laptop = Laptop()
        val standardWirelessMouse = StandardWirelessMouse()
        val gamingWirelessMouse = GamingWirelessMouse()

        standardWirelessMouse.apply {
            //7. The client code should pass an implementation object to the abstraction’s constructor to associate one with the other. After that, the client can forget about the implementation and work only with the abstraction object.
            setControlledDevice(desktop)
            clickLeftMouseButton() //desktop click left button

            setControlledDevice(laptop) //reassign to laptop
            moveCursor(30, 50) //laptop moving cursor with dx 30, dy 50
        }
        gamingWirelessMouse.apply {
            setControlledDevice(laptop)
            clickSideMouseButton() //laptop click side button
        }

    }
}

interface ControlledDevice {
    //3. Determine the operations available on all platforms. Declare the ones that the abstraction needs in the general implementation interface.
    fun clickLeft()
    fun clickRight()
    fun moveCursor(dx: Int, dy: Int)
    fun clickSide()
}

class Desktop : ControlledDevice {
    override fun clickLeft() {
        println("desktop click left button")
    }

    override fun clickRight() {
        println("desktop click right button")
    }

    override fun moveCursor(dx: Int, dy: Int) {
        println("desktop moving cursor with dx $dx, dy $dy")
    }

    override fun clickSide() {
        println("desktop click side button")
    }
}

class Laptop : ControlledDevice {
    override fun clickLeft() {
        println("laptop click left button")
    }

    override fun clickRight() {
        println("laptop click right button")
    }

    override fun moveCursor(dx: Int, dy: Int) {
        println("laptop moving cursor with dx $dx, dy $dy")
    }

    override fun clickSide() {
        println("laptop click side button")
    }
}

abstract class WirelessMouse {
    //5. Inside the abstraction class, add a reference field for the implementation type. The abstraction delegates most of the work to the implementation object that’s referenced in that field.
    @JvmField
    protected var controlledDevice: ControlledDevice? = null //reference for the implementation type

    //2. See what operations the client needs and define them in the base abstraction class.
    fun setControlledDevice(device: ControlledDevice) {
        controlledDevice = device
    }

    fun clickLeftMouseButton() {
        controlledDevice?.clickLeft()
    }

    fun clickRightMouseButton() {
        controlledDevice?.clickRight()
    }

    fun moveCursor(dx: Int, dy: Int) {
        controlledDevice?.moveCursor(dx, dy)
    }
}

class StandardWirelessMouse : WirelessMouse()

class GamingWirelessMouse : WirelessMouse() {
    //4. For all platforms in your domain create concrete implementation classes, but make sure they all follow the implementation interface.

    //6. If you have several variants of high-level logic, create refined abstractions for each variant by extending the base abstraction class.
    fun clickSideMouseButton() {
        controlledDevice?.clickSide()
    }
}