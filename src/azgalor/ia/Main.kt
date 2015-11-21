package azgalor.ia

import azgalor.ia.pathfinding.algorithm.AStarAlgorithm
import azgalor.ia.pathfinding.dto.Node
import azgalor.ia.pathfinding.dto.NodeAssociation
import java.util.*

fun main(args: Array<String>) {
    var graph = buildGraph()

    var nodeStart = graph.getOrImplicitDefault("E")
    var nodeEnd = graph.getOrImplicitDefault("B")

    var aStar = AStarAlgorithm(nodeStart, nodeEnd)
    var path = aStar.findPath()
    for (node in path) {
        println(node.name)
    }
}


fun buildGraph(): Map<String, Node> {

    var nodeA = Node("A")
    var nodeB = Node("B")
    var nodeC = Node("C")
    var nodeD = Node("D")
    var nodeE = Node("E")

    nodeA.associativeList = listOf(NodeAssociation(1, nodeB), NodeAssociation(2, nodeC))
    nodeB.associativeList = listOf(NodeAssociation(3, nodeD), NodeAssociation(2, nodeE))
    nodeC.associativeList = listOf(NodeAssociation(1, nodeE))
    nodeD.associativeList = listOf(NodeAssociation(4, nodeC), NodeAssociation(3, nodeB))
    nodeE.associativeList = listOf(NodeAssociation(5, nodeA), NodeAssociation(1, nodeD))

    return buildMap(nodeA)
}

fun buildMap(nodeA: Node): Map<String, Node> {
    var map = HashMap<String, Node>()
    map.putIfAbsent(nodeA.name, nodeA)
    var stack = Stack<Node>()
    stack.push(nodeA)

    while(!stack.empty()) {
        var currentNode = stack.pop()
        for (neighbor in currentNode.associativeList.orEmpty()) {
            if (!map.containsKey(neighbor.node.name)) {
                stack.push(neighbor.node)
                map.put(neighbor.node.name, neighbor.node)
            }
        }
    }

    return map
}


