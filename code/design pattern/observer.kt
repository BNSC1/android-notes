class Main {
    @Test
    fun main() {
        val streamer = Streamer()
        val subscriber1 = Subscriber()
        val subscriber2 = Subscriber()
        val subscriber3 = Subscriber()

        streamer.addSubscriber(subscriber1)
        streamer.addSubscriber(subscriber2)
        streamer.startStream()

        subscriber1.notified `should be equal to` true
        subscriber2.notified `should be equal to` true
        subscriber3.notified `should be equal to` false //not subscribed to the streamer
    }
}

class Streamer {
    val subs = mutableListOf<Subscriber>()
    fun addSubscriber(sub: Subscriber) {
        subs.add(sub)
    }
    private fun notifyStream() {
        subs.forEach {
            it.update()
        }
    }
    fun startStream() {
        //start streaming
        notifyStream()
    }
}

class Subscriber {
    var notified = false
    fun update() {
        println("Streamer has started streaming")
        notified = true
    }
}