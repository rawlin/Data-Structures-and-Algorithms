package linkedlists

fun main(args: Array<String>) {

    val list = LinkedList<Int>()
    list.append(1).append(2).append(3)

    for (i in list) {
        println("Double: ${i*2}")
    }
}

