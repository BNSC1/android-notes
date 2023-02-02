class Main {
    @Test
    fun main() {
        val s1 = Singleton.getInstance()
        val s2 = Singleton.getInstance()
        //val s3 = Singleton() //cannot access
        s1 `should be equal to` s2 //test passes
    }
}

class Singleton private constructor() { //forbid public constructor access
    companion object {
        private lateinit var instance: Singleton

        fun getInstance(): Singleton {
            if (!this::instance.isInitialized) {
                instance = Singleton() //init instance if it is not initialized
            }
            return instance
        }
    }
}