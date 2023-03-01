//https://refactoring.guru/design-patterns/flyweight
/*
1. Divide fields of a class that will become a flyweight into two parts:
- the intrinsic state (color): the fields that contain unchanging data duplicated across many objects
- the extrinsic state (coordinate): the fields that contain contextual data unique to each object
*/

/*
2. Leave the fields that represent the intrinsic state in the class, but make sure they’re immutable.
   They should take their initial values only inside the constructor.
*/
data class Color(val red: Int, val green: Int, val blue: Int)
data class GrassAppearance(val textures: String, val color: Color)

/*
3. Go over methods that use fields of the extrinsic state.
   For each field used in the method, introduce a new parameter and use it instead of the field.
*/
class Grass(val x: Int, val y: Int, val appearance: GrassAppearance) {
    fun draw() {
        println("Grass at ($x,$y) with color R:${appearance.color.red}, G:${appearance.color.green}, B:${appearance.color.blue}")
    }
}

/*
4. Optionally, create a factory class to manage the pool of flyweights.
   It should check for an existing flyweight before creating a new one.
   Once the factory is in place, clients must only request flyweights through it.
   They should describe the desired flyweight by passing its intrinsic state to the factory.
*/
class GrassFactory {
    companion object {
        private val grassAppearances = mutableListOf<GrassAppearance>()
        fun getOrAddGrassAppearance(textures: String, color: Color): GrassAppearance {
            val result = grassAppearances.firstOrNull {
                it.textures == textures &&
                        it.color == color
            }
            return if (result != null) {
                result
            } else {
                val newAppearance = GrassAppearance(textures, color)
                grassAppearances.add(newAppearance)
                newAppearance
            }
        }
    }
}

class Main {
    private val grasses = mutableListOf<Grass>()
    @Test
    fun main() {
        /*
        5. The client must store or calculate values of the extrinsic state (context) to be able to call methods of flyweight objects.
        For the sake of convenience, the extrinsic state along with the flyweight-referencing field may be moved to a separate context class.
        */
        addGrass(3,9, "Sand", Color(203, 176, 0))
        addGrass(3,10, "Glass", Color(25, 25, 25))
        addGrass(4,9, "Sand", Color(200, 100, 0))
        drawAll()
    }

    private fun addGrass(x: Int, y: Int, textures: String, color: Color) {
        val appearance = GrassFactory.getOrAddGrassAppearance(textures, color)
        grasses.add(Grass(x, y, appearance))
    }

    private fun drawAll() {
        grasses.forEach {
            it.draw()
        }
    }
}