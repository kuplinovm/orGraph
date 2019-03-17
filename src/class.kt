import kotlin.Exception

data class orGraph(val points: List<String>, val arcs: List<Pair<Pair<String, String>, Double>>) {

    fun addPoint(name: String) = points.plus(name)

    fun addArc(point1: String, point2: String, weight: Double) {
        if (points.contains(point1) && points.contains(point2))
            arcs.plus(Pair(Pair(point1, point2), weight))
        else
            throw Exception()
    }

    fun removePoint(name: String) {
        if (points.contains(name)) {
            points.minus(name)
            arcs.forEach { if (it.first.first == name || it.first.second == name) arcs.minus(it) }
        }
        else
            throw Exception()
    }

    fun renamePoint(old: String, new: String) {
        if (points.contains(old)) {
            points.minus(old)
            points.plus(new)
            arcs.forEach { if (it.first.first == old) {
                arcs.minus(it)
                arcs.plus(Pair(Pair(new, it.first.second), it.second))
            }
            else if (it.first.second == old) {
                arcs.minus(it)
                arcs.plus(Pair(Pair(it.first.first, new), it.second))
            }
            }
        }
        else throw Exception()
    }

    fun changeWeight (point1: String, point2: String, oldWeight: Double, newWeight: Double) {
        if (points.contains(point1) && points.contains(point2))
            arcs.forEach { if (it == Pair(Pair(point1, point2), oldWeight)) {
                arcs.minus(it)
                arcs.plus(Pair(Pair(point1, point2), newWeight))
                }
            }
        else throw Exception()
    }

    fun listOfOutgoingArcs (name: String): List<Pair<Pair<String, String>, Double>> {
        val res = listOf(Pair(Pair("0", "0"), 0.0))
        if (points.contains(name))
            for (element in arcs)
                if (element.first.first == name)
                    res.plus(element)
        else throw Exception()
        return res.drop(0)
    }

    fun listOfIncomingArcs (name: String): List<Pair<Pair<String, String>, Double>> {
        val res = listOf(Pair(Pair("0", "0"), 0.0))
        if (points.contains(name))
            for (element in arcs)
                if (element.first.second == name)
                    res.plus(element)
        else throw Exception()
        return res.drop(0)
    }
}