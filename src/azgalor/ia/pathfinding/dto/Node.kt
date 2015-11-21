package azgalor.ia.pathfinding.dto

class Node constructor(val pName:String) : Comparable<Node>  {
    override fun compareTo(other: Node): Int {
        return this.name.compareTo(other.name)
    }

    var associativeList:List<NodeAssociation>?
    var name:String
    var exactCostToReach:Int=0
    var parent:Node?
    var estimatedCost:Int=0
    init {
        name=pName
        associativeList =null
        parent=null
    }

    override fun equals(other: Any?): Boolean {
        if (other is Node) {
            return this.name.equals(other.name)
        }
        else {
            return false
        }
    }

    override fun hashCode(): Int{
        return name.hashCode()
    }

}