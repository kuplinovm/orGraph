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

    }
}

