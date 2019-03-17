import kotlin.Exception

data class orGraph(val points: MutableList<String>, val arcs: MutableList<Pair<Pair<String, String>, Double>>) {

    fun addPoint(name: String, index: Int) = points.add(index, name)

    fun addArc(point1: String, point2: String, weight: Double, index: Int) =
        if (points.contains(point1) && points.contains(point2))
            arcs.add(index, Pair(Pair(point1, point2), weight))
        else
            throw Exception()

    fun removePoint(name: String) =
        if (points.contains(name)) {
            points.remove(name)
            for (i in arcs.size - 1 downTo 0)
                if (arcs[i].first.first == name || arcs[i].first.second == name)
                    arcs.removeAt(i)
        }
        else throw Exception()

    fun renamePoint(old: String, new: String) =
        if (points.contains(old)) {
            points.add(points.indexOf(old), new)
            points.remove(old)
            for (i in arcs.size - 1 downTo 0) {
                if (arcs[i].first.first == old) {
                    arcs.add(i, Pair(Pair(new, arcs[i].first.second), arcs[i].second))
                    arcs.removeAt(i + 1)
                }
                else if (arcs[i].first.first == old) {
                    arcs.add(i, Pair(Pair(arcs[i].first.first, new), arcs[i].second))
                    arcs.removeAt(i + 1)
                }
            }
        }
        else throw Exception()

    fun changeWeight (point1: String, point2: String, oldWeight: Double, newWeight: Double) =
        if (points.contains(point1) && points.contains(point2))
            for (i in arcs.size - 1 downTo 0) {
                if (arcs[i] == Pair(Pair(point1, point2), oldWeight)) {
                    arcs.add(i, Pair(Pair(point1, point2), newWeight))
                    arcs.removeAt(i + 1)
                }
            }
        else throw Exception()

    fun listOfOutgoingArcs (name: String): MutableList<Pair<Pair<String, String>, Double>> {
        val res = mutableListOf(Pair(Pair("0", "0"), 0.0))
        if (points.contains(name)) {
            for (element in arcs)
                if (element.first.first == name)
                    res.add(element)
        }
        else throw Exception()
        res.removeAt(0)
        return res
    }

    fun listOfIncomingArcs (name: String): MutableList<Pair<Pair<String, String>, Double>> {
        val res = mutableListOf(Pair(Pair("0", "0"), 0.0))
        if (points.contains(name)) {
            for (element in arcs)
                if (element.first.second == name)
                    res.add(element)
        }
        else throw Exception()
        res.removeAt(0)
        return res
    }

}