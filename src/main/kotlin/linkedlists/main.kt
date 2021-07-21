package linkedlists

fun main(args: Array<String>) {

    val list = LinkedList<Int>()
    list.add(3)
    list.add(2)
    list.add(1)
    list.add(6)
    list.add(7)
    list.add(8)

    println(list)
    list.retainAll(listOf(1, 6, 7))
    println(list)
}

