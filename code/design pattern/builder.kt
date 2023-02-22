class Main {
    @Test
    fun main() {
        val budgetMouse = MouseBuilder()
            .setLeftMouseButton("budget Chinese switch")
            .setRightMouseButton("budget Chinese switch")
            .setMidMouseButton("budget Chinese switch")
            .setWheel("cheap wheel")
            .setSensor("800dpi sensor")
            .build()

        val premiumGamingMouse = MouseBuilder()
            .setLeftMouseButton("Japanese OMRON switch")
            .setRightMouseButton("Japanese OMRON switch")
            .setMidMouseButton("Japanese OMRON switch")
            .setWheel("free spinning wheel")
            .setSideMouseButton("Japanese OMRON switch")
            .setSensor("6400dpi sensor")
            .build()

        println("showing budget mouse info")
        budgetMouse.showInfo()
        /*
        I am a mouse with
        budget Chinese switch as left mouse button
        budget Chinese switch as right mouse button
        budget Chinese switch as mid mouse button
        null as side mouse button
        cheap wheel as mouse wheel
        800dpi sensor as mouse sensor
        */

        println("showing premium gaming mouse info")
        premiumGamingMouse.showInfo()
        /*
        I am a mouse with
        Japanese OMRON switch as left mouse button
        Japanese OMRON switch as right mouse button
        Japanese OMRON switch as mid mouse button
        Japanese OMRON switch as side mouse button
        free spinning wheel as mouse wheel
        6400dpi sensor as mouse sensor
        */
    }
}

class Mouse {
    var leftMouseButton: String? = null
    var rightMouseButton: String? = null
    var midMouseButton: String? = null
    var wheel: String? = null
    var sideMouseButton: String? = null
    var sensor: String? = null

    fun showInfo() {
        println("I am a mouse with\n" +
                "$leftMouseButton as left mouse button\n" +
                "$rightMouseButton as right mouse button\n" +
                "$midMouseButton as mid mouse button\n" +
                "$sideMouseButton as side mouse button\n" +
                "$wheel as mouse wheel\n" +
                "$sensor as mouse sensor")
    }
}

interface IMouseBuilder {
    fun setWheel(wheel: String): MouseBuilder
    fun setLeftMouseButton(switch: String): MouseBuilder
    fun setRightMouseButton(switch: String): MouseBuilder
    fun setMidMouseButton(switch: String): MouseBuilder
    fun setSideMouseButton(switch: String): MouseBuilder
    fun setSensor(sensor: String): MouseBuilder
    fun build(): Mouse
}

class MouseBuilder: IMouseBuilder {
    private val mouse = Mouse()

    override fun setWheel(wheel: String): MouseBuilder {
        mouse.wheel = wheel
        return this
    }

    override fun setLeftMouseButton(switch: String): MouseBuilder {
        mouse.leftMouseButton = switch
        return this
    }

    override fun setRightMouseButton(switch: String): MouseBuilder {
        mouse.rightMouseButton = switch
        return this
    }

    override fun setMidMouseButton(switch: String): MouseBuilder {
        mouse.midMouseButton = switch
        return this
    }

    override fun setSideMouseButton(switch: String): MouseBuilder {
        mouse.sideMouseButton = switch
        return this
    }

    override fun setSensor(sensor: String): MouseBuilder {
        mouse.sensor = sensor
        return this
    }

    override fun build() = mouse
}