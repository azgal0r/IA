package azgalor.ia.pathfinding.dto

/**
 * Says to which node a node is associated and with how much weight
 * Created by Anthony on 11/20/2015.
 */
data class NodeAssociation constructor(var weight:Int, var node: Node) : Comparable<NodeAssociation> {

    override fun compareTo(other: NodeAssociation): Int {
        return this.weight.compareTo(other.weight)
    }
}