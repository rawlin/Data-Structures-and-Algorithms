import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val linkedList = LinkedList<Int>()
    linkedList.addLast(1)
    linkedList.addLast(2)
    linkedList.addLast(2)
    linkedList.addLast(3)
    linkedList.addLast(4)
    linkedList.addLast(5)
    linkedList.addLast(6)
    linkedList.addLast(5)
    removeDuplicatesFromLinkedList(linkedList)
    linkedList.forEach {
        print("$it->")
    }
}

private fun removeDuplicatesFromLinkedList(linkedList: LinkedList<Int>) {
    val hashSet = HashSet<Int>()
    linkedList.forEach { element ->
        hashSet.add(element)
    }
    linkedList.clear()
    hashSet.forEach { element ->
        linkedList.add(element)
    }
}