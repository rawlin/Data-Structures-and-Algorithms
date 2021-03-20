package LinkedLists

data class Node<T>(var value: T, var next: Node<T>) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> $next"
        } else {
            "$value"
        }
    }
}