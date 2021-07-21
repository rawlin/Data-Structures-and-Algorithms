package trees

import java.lang.StringBuilder
import kotlin.math.max

fun main(args: Array<String>) {

    print(countBits(4))

}

fun runLengthEncoding(inputString: String): String {


    val n: Int = inputString.length
    val string = ""
    val stringBuilder = StringBuilder(string)
    var i = 0
    while (i < n) {

        var count = 1
        while (i < n - 1 &&
            inputString[i] === inputString[i + 1]
        ) {
            count++
            i++
        }
        stringBuilder.append(inputString[i])
        stringBuilder.append(count)
        i++
    }

    return stringBuilder.toString()

}

fun singleNumber(nums: IntArray): Int {
    val hashMap = HashMap<Int, Int>()
    var ans: Int? = null
    for (i in nums) {
        hashMap[i] = hashMap[i]?.plus(1) ?: 1
    }
    for (i in hashMap) {
        if (i.value == 1) {
            ans = i.key
        }
    }

    return ans!!
}

fun singleNumberXOR(nums: IntArray): Int {
    var ans = 0
    for (i in nums) {
        ans = ans.xor(i)
    }
    return ans
}

fun inorderTraversal(root: TreeNode?): List<Int?> {
    val inorderList = mutableListOf<Int?>()

    if (root == null)
        inorderList.add(null)

    inorderTraversal(root?.left)
    inorderList.add(root?.`val`)
    inorderTraversal(root?.right)

    return inorderList
}


fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {

    if (root1 == null)
        return root2
    if (root2 == null)
        return root1

    root1.`val` += root2.`val`
    root1.left = mergeTrees(root1.left, root2.left)
    root1.right = mergeTrees(root1.right, root2.right)

    return root1
}

fun maxDepth(root: TreeNode?): Int {
    if (root == null)
        return 0

    val leftDepth = maxDepth(root.left)
    val rightDepth = maxDepth(root.right)

    return max(leftDepth, rightDepth) + 1
}

fun invertTree(root: TreeNode?): TreeNode? {

    if (root == null)
        return root

    val left = invertTree(root.left)
    val right = invertTree(root.right)
    root.left = right
    root.right = left

    return root
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun countBits(n: Int): IntArray {
    val result = IntArray(n + 1)
    for (i in 0..n) {
        if (i == 0) {
            result[i] = 0
            continue
        }

        if (i % 2 == 0) {
            result[i] = result[i-2]
        } else {
            result[i] = result[i/2] + 1
        }

    }
    return result
}

private fun getSetBits(_n: Int): Int {
    val binaryNum = IntArray(32)
    var n = _n
    var i = 0

    while (n > 0) {
        binaryNum[i] = n % 2
        n /= 2
        i++
    }

    var count = 0
    for (j in binaryNum.indices) {
        if (binaryNum[j] == 1) {
            count++
        }
    }
    return count
}