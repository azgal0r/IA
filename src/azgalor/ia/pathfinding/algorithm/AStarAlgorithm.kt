package azgalor.ia.pathfinding.algorithm

import azgalor.ia.pathfinding.dto.Node
import java.util.*

/**
 * A* pathfinding algorithm
 */
class AStarAlgorithm constructor(var startingNode: Node, var goalNode: Node) {

    fun findPath(): List<Node> {
        var openList = arrayListOf(startingNode)
        var closedList = ArrayList<Node>()

        var currentNode: Node? = null;
        while (openList.isNotEmpty()) {

            if (currentNode == null) {
                currentNode = getLowestCostNode(openList)
            } else {
                currentNode = getLowestCostNode(openList)
            }
            //Is it the goal?
            if (currentNode.equals(goalNode)) {
                return makePathList(currentNode)
            } else {
                closedList.add(currentNode)
                openList.remove(currentNode)
                //Consider the neighbors
                for (neighbor in currentNode.associativeList.orEmpty()) {
                    //That neighbor is already explored, is our current node faster ?
                    val isInClosedLisAndFaster = closedList.containsRaw(neighbor.node) && neighbor.node.exactCostToReach < currentNode.exactCostToReach
                    val isInOpenListAndFaster = openList.containsRaw(neighbor.node) && neighbor.node.exactCostToReach < currentNode.exactCostToReach
                    if (!isInClosedLisAndFaster && !isInOpenListAndFaster) {
                        neighbor.node.exactCostToReach = currentNode.exactCostToReach + neighbor.weight
                        neighbor.node.estimatedCost = neighbor.node.exactCostToReach + calculateHeuristic(neighbor.node)
                        neighbor.node.parent = currentNode
                        openList.add(neighbor.node)
                    }
                }
            }
        }
        return emptyList()
    }

    private fun calculateHeuristic(node: Node): Int {
        return 0;
    }

    private fun getLowestCostNode(listNode: List<Node>): Node {
        Collections.sort(listNode, Comparator { a, b -> a.estimatedCost.compareTo(b.estimatedCost) })
        return listNode[0]
    }

    private fun makePathList(node: Node): List<Node> {
        var listNode = ArrayList<Node>()
        var currentNode: Node? = node
        while (currentNode != null) {
            listNode.add(currentNode)
            currentNode = currentNode.parent
        }
        return listNode.reversed()
    }

}