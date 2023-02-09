class Main {
    @Test
    fun main() {
        val robot = Robot()
        println("walking at ${robot.walkSpeed}") //0 because it defaults to stop state
        robot.walk(420)
        println("walking at ${robot.walkSpeed}") //now the robot walks at 420
        robot.stop()
        println("walking at ${robot.walkSpeed}") //the robot stops
        robot.walk(1000)
        println("walking at ${robot.walkSpeed}") //now the robot walks even faster
        robot.stop()
        println("walking at ${robot.walkSpeed}") //the robot stops again
        robot.stop() //prints "I am not moving already" because the robots is already at stop state
    }

    class Robot {
        private var state: State = StopState(this)
        var walkSpeed = 0
        private set

        fun setState(state: State) {
            this.state = state
        }
        fun setWalkSpeed(speed: Int) {
            walkSpeed = speed
        }
        fun walk(speed: Int) { //delegate executions to a state object
            state.walk(speed)
        }
        fun stop() {
            state.stop()
        }
    }

    abstract class State {
        abstract val robot: Robot //has reference to the context
        open fun stop() {}
        open fun walk(speed: Int) {}
    }
    class WalkState(override val robot: Robot): State() {
        override fun walk(speed: Int) {
            robot.setWalkSpeed(speed)
        }

        override fun stop() {
            robot.setWalkSpeed(0)
            robot.setState(StopState(robot)) //set the context to another state inside a state, the main difference between Strategy pattern
        }
    }
    class StopState(override val robot: Robot): State() {
        override fun walk(speed: Int) {
            robot.setWalkSpeed(speed)
            robot.setState(WalkState(robot))
        }

        override fun stop() {
            println("I am not moving already")
        }
    }
}