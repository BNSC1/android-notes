class Main {
    @Test
    fun main() {
        val travelPlan = TravelPlan("taipei")
        val transport = "railway"
        travelPlan.setStrategy( //strategy is assigned in neither the context (TravelPlan) nor the strategy object
            when (transport) {
                "railway" -> RailwayStrategy
                "bus" -> BusStrategy
                else -> throw IllegalArgumentException("unknown transport")
            }
        )
        travelPlan.runStrategy() //choo choo to taipei
    }

    class TravelPlan(private val destination: String) {
        private lateinit var strategy: TravelStrategy
        fun setStrategy(strategy: TravelStrategy) {
            this.strategy = strategy
        }

        fun runStrategy() {
            strategy.travel(destination) //delegate executions to a strategy object
        }
    }

    interface TravelStrategy {
        fun travel(destination: String)
    }

    object RailwayStrategy : TravelStrategy {
        override fun travel(destination: String) {
            println("choo choo to $destination")
        }
    }

    object BusStrategy : TravelStrategy {
        override fun travel(destination: String) {
            println("don don don to $destination")
        }
    }
}