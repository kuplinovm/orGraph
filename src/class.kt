import kotlin.Exception

data class arcs(val arcs: MutableList<Pair<Pair<String, String>, Double>>) {

    fun addArc(point1: String, point2: String, weight: Double) =
        arcs.add(Pair(Pair(point1, point2), weight))

    fun changeWeight(point1: String, point2: String, oldWeight: Double, newWeight: Double) {

        for (i in arcs.size - 1 downTo 0) {
            if (arcs[i] == Pair(Pair(point1, point2), oldWeight)) {
                arcs.add(i, Pair(Pair(point1, point2), newWeight))
                arcs.removeAt(i + 1)
            }
        }
    }


    fun listOfOutgoingArcs (name: String): MutableList<Pair<Pair<String, String>, Double>> {
        val res: MutableList<Pair<Pair<String, String>, Double>> = mutableListOf(Pair(Pair("0", "0"), 0.0))
        res.removeAt(0)
            for (element in arcs)
                if (element.first.first == name) {
                    res.add(element)
                }
        return res
    }

    fun listOfIncomingArcs (name: String): MutableList<Pair<Pair<String, String>, Double>> {
        val res: MutableList<Pair<Pair<String, String>, Double>> = mutableListOf(Pair(Pair("0", "0"), 0.0))
        res.removeAt(0)
            for (element in arcs)
                if (element.first.second == name)
                    res.add(element)
        return res
    }

}

data class orGraph(val points: MutableList<String>, val arcs: arcs) {

    fun addPoint(name: String) = points.add(name)

    fun addArc(point1: String, point2: String, weight: Double) =
        if (points.contains(point1) && points.contains(point2))
            arcs.addArc(point1, point2, weight)
        else
            throw Exception()


    fun removePoint(name: String) =
        if (points.contains(name)) {
            points.remove(name)
            for (i in arcs.arcs.size - 1 downTo 0)
                if (arcs.arcs[i].first.first == name || arcs.arcs[i].first.second == name)
                    arcs.arcs.removeAt(i)
        }
        else throw Exception()

    fun renamePoint(old: String, new: String) =
        if (points.contains(old)) {
            points.add(points.indexOf(old), new)
            points.remove(old)
            for (i in arcs.arcs.size - 1 downTo 0) {
                if (arcs.arcs[i].first.first == old) {
                    arcs.arcs.add(i, Pair(Pair(new, arcs.arcs[i].first.second), arcs.arcs[i].second))
                    arcs.arcs.removeAt(i + 1)
                }
                else if (arcs.arcs[i].first.second == old) {
                    arcs.arcs.add(i, Pair(Pair(arcs.arcs[i].first.first, new), arcs.arcs[i].second))
                    arcs.arcs.removeAt(i + 1)
                }
            }
        }
        else throw Exception()

    fun changeWeight (point1: String, point2: String, oldWeight: Double, newWeight: Double) =
        if (points.contains(point1) && points.contains(point2) && newWeight >= 0)
            arcs.changeWeight(point1, point2, oldWeight, newWeight)
        else throw Exception()

    fun listOfOutgoingArcs (name: String) =
        if (points.contains(name))
            arcs.listOfOutgoingArcs(name)
        else throw Exception()


    fun listOfIncomingArcs (name: String) =
        if (points.contains(name))
            arcs.listOfIncomingArcs(name)
        else throw Exception()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as orGraph

        if (points != other.points) return false
        if (arcs != other.arcs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = points.hashCode()
        result = 31 * result + arcs.hashCode()
        return result
    }

}