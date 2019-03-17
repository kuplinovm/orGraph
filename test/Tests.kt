import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    private val points = mutableListOf("1", "2", "5", "10", "13")
    private val arcs =
        mutableListOf(Pair(Pair("1", "2"), 1.3),
            Pair(Pair("5", "10"), 2.0),
            Pair(Pair("2", "13"), 0.6),
            Pair(Pair("13", "5"), 1.3),
            Pair(Pair("5", "13"), 1.9))

    @Test
    fun addPoint() {
        val testPoints1 = orGraph(mutableListOf("2", "5", "10", "13"), arcs)
        testPoints1.addPoint("1", 0)
        assertEquals(orGraph(points, arcs), testPoints1)

        val testPoints2 = orGraph(mutableListOf("1", "2", "5", "13"), arcs)
        testPoints2.addPoint("10", 3)
        assertEquals(orGraph(points, arcs), testPoints2)
    }

    @Test
    fun addArc() {
        val testArcs1 = orGraph(points, mutableListOf(Pair(Pair("1", "2"), 1.3),
                                                        Pair(Pair("5", "10"), 2.0),
                                                        Pair(Pair("2", "13"), 0.6),
                                                        //Pair(Pair("13", "5"), 1.3),
                                                        Pair(Pair("5", "13"), 1.9)))
        testArcs1.addArc("13", "5", 1.3, 3)
        assertEquals(orGraph(points, arcs), testArcs1)

        val testArcs2 = orGraph(points, mutableListOf(Pair(Pair("1", "2"), 1.3),
                                                        Pair(Pair("2", "13"), 0.6),
                                                        Pair(Pair("13", "5"), 1.3),
                                                        Pair(Pair("5", "13"), 1.9)))
        testArcs2.addArc("5", "10", 2.0, 1)
        assertEquals(orGraph(points, arcs), testArcs2)
    }

    @Test
    fun removePoint() {
        val testRemove = orGraph(points, arcs)
        testRemove.removePoint("2")
        assertEquals(orGraph(mutableListOf("1", "5", "10", "13"), mutableListOf(
            Pair(Pair("5", "10"), 2.0),
            Pair(Pair("13", "5"), 1.3),
            Pair(Pair("5", "13"), 1.9))), testRemove)
    }

    @Test
    fun renamePoint() {
        val testRename = orGraph(points, arcs)
        testRename.renamePoint("1", "7")
        assertEquals(orGraph(mutableListOf("7", "2", "5", "10", "13"), mutableListOf(Pair(Pair("7", "2"), 1.3),
            Pair(Pair("5", "10"), 2.0),
            Pair(Pair("2", "13"), 0.6),
            Pair(Pair("13", "5"), 1.3),
            Pair(Pair("5", "13"), 1.9))), testRename)
    }

    @Test
    fun changeWeight() {
        val testChange = orGraph(points, arcs)
        testChange.changeWeight("5", "10", 2.0, 3.3)
        assertEquals(orGraph(mutableListOf("1", "2", "5", "10", "13"), mutableListOf(Pair(Pair("1", "2"), 1.3),
            Pair(Pair("5", "10"), 3.3),
            Pair(Pair("2", "13"), 0.6),
            Pair(Pair("13", "5"), 1.3),
            Pair(Pair("5", "13"), 1.9))), testChange)
    }

    @Test
    fun listOfOutgoingArcs() {
        assertEquals(mutableListOf(Pair(Pair("5", "10"), 2.0), Pair(Pair("5", "13"), 1.9)), orGraph(points, arcs).listOfOutgoingArcs("5"))
    }

    @Test
    fun listOfIncomingArcs() {
        assertEquals(mutableListOf(Pair(Pair("13", "5"), 1.3)), orGraph(points, arcs).listOfIncomingArcs("5"))
    }

}