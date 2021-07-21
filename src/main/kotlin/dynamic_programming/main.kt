package dynamic_programming


fun main() {
    print(countConstructTab("abcdef", arrayOf("ab", "abc", "cd", "def", "abcd")))

}

//Print fibonacci number at nth position
//memoization - like memo
// use a hashmap
private fun fib(n: Int, memo: HashMap<Int, Long> = HashMap()): Long {
    if (memo.contains(n)) return memo.getValue(n)
    if (n <= 2) return 1
    memo[n] = fib(n - 1, memo) + fib(n - 2, memo)
    return memo.getValue(n)
}


// Say that you are a traveler on a 2d grid. You begin in the to-left corner and your goal is to travel to
// the bottom-right corner. You may only move down or right (Memoization)
// In how many ways can you travel to the goal on a grid with dimensions m*n
private fun gridTraveler(m: Int, n: Int, memo: HashMap<Pair<Int, Int>, Int> = HashMap()): Int {
    if (memo.contains(Pair(m, n))) return memo.getValue(Pair(m, n))
    if (m == 1 && n == 1) return 1
    if (m == 0 || n == 0) return 0

    memo[Pair(m, n)] = gridTraveler(m - 1, n, memo) + gridTraveler(m, n - 1, memo)

    return memo.getValue(Pair(m, n))
}


// Write a function canSum(targetSum, numbers) that takes i a targetSum, and array of numbers as arguments.
// The function should return a boolean whether or not it is possible to generate a target sum using the numbers in the
// array
// Elements in the array can be used as many times as needed and assume all inputs are non negative
private fun canSum(targetSum: Int, numbers: IntArray, memo: HashMap<Int, Boolean> = HashMap()): Boolean {
    if (memo.contains(targetSum)) return memo.getValue(targetSum)
    if (targetSum == 0) return true
    if (targetSum < 0) return false

    for (i in numbers) {
        if (canSum(targetSum - i, numbers, memo)) {
            memo[targetSum] = true
            return true
        }
    }

    memo[targetSum] = false
    return false
}

// Write a function howSum(targetSum, numbers) that takes in a targetSum and array of numbers as arguments
// The function should return an array containing any combination of elements that add up to exactly the targetSum.
// If there is no combination that adds up to the targetSum, then return null
//If there are multiple combinations possible you may return any one of them
// Time - O(n*m^2)
// Space - O(m^2)
private fun howSum(targetSum: Int, numbers: IntArray, memo: HashMap<Int, List<Int>?> = HashMap()): List<Int>? {
    if (memo.contains(targetSum)) return memo.getValue(targetSum)
    if (targetSum == 0) return emptyList()
    if (targetSum < 0) return null

    for (i in numbers) {
        val result = howSum(targetSum - i, numbers, memo)
        if (result != null) {
            val list = mutableListOf<Int>()
            list.addAll(result)
            list.add(i)
            memo[targetSum] = list
            return memo[targetSum]
        }
    }

    memo[targetSum] = null
    return null
}

// Write a function bestSum(targetSum, numbers) that take in a targetSum and an array of numbers as arguments.
// The function should return an array with the shortest combination of numbers that add up to exactly the targetSum
// If there is a tie for the shortest combination you may return any one of the shortest
private fun bestSum(targetSum: Int, numbers: IntArray, memo: HashMap<Int, List<Int>?> = HashMap()): List<Int>? {
    if (memo.contains(targetSum)) return memo.getValue(targetSum)
    if (targetSum == 0) return emptyList()
    if (targetSum < 0) return null
    var shortestCombination: List<Int>? = null

    for (i in numbers) {
        val result = bestSum(targetSum - i, numbers, memo)
        if (result != null) {
            val list = mutableListOf<Int>()
            list.addAll(result)
            list.add(i)
            // if the combination is shorter thant he current shortest we add it
            if (shortestCombination == null || list.size < shortestCombination.size) {
                shortestCombination = list
            }
        }
    }
    memo[targetSum] = shortestCombination
    return shortestCombination
}

// Write a function canConstruct(target, wordBank) that accepts a tart string and an array of strings.
// Return a boolean indicating whether or not the target can be constructed by concatenating elements of the wordBank
// array .
private fun canConstruct(target: String, wordBank: Array<String>, memo: HashMap<String, Boolean> = HashMap()): Boolean {
    if (memo.contains(target)) return memo.getValue(target)
    if (target == "") return true

    for (i in wordBank) {
        if (target.indexOf(i) == 0) {
            val suffix = target.removePrefix(i)
            if (canConstruct(suffix, wordBank, memo)) {
                memo[target] = true
                return true
            }
        }
    }
    memo[target] = false
    return false
}

// Write a function countConstruct(target, wordBank) that accepts a target string and an array of strings.
// The function should return the number of ways that the target can be constructed by concatenating elements of the
// wordBank array
// You can use the words from the wordBank as many times as you want
private fun countConstruct(target: String, wordBank: Array<String>, memo: HashMap<String, Int> = HashMap()): Int {
    if (memo.contains(target)) return memo.getValue(target)
    if (target == "") return 1

    var count = 0

    for (i in wordBank) {
        if (target.indexOf(i) == 0) {
            val suffix = target.removePrefix(i)
            val numOfWays = countConstruct(suffix, wordBank, memo)
            count += numOfWays
        }
    }
    memo[target] = count
    return count
}

// Write a function allConstruct(target, wordBank) that accepts a target string and an array of strings.
// The function should return a 2d array containing all of the ways the target can be constructed by concatenating
// the elements of the wordBank array. Each element of the 2d array should represent one combination that constructs the
// target
//private fun allConstruct(target: String, wordBank: List<String>): List<List<String>> {
//    if (target == "") return listOf(emptyList())
//
//    val result = mutableListOf<List<String>>()
//
//    for (word in wordBank) {
//        if (target.indexOf(word) == 0) {
//            val suffix = target.removePrefix(word)
//            val suffixWays = allConstruct(suffix, wordBank)
//            val targetWays = mutableListOf<List<String>>()
//            suffixWays.forEach { way ->
//                targetWays.add(listOf(word))
//                targetWays.add(way)
//            }
//
//            for (i in targetWays.indices) {
//                if (targetWays[i].isNotEmpty()) {
//                    result.add(targetWays[i])
//                }
//            }
//
//
//        }
//
//    }
//
//    return result
//}
fun allConstruct(
    target: String, wordBank: List<String>
): List<List<String>> {
    if (target.compareTo("") == 0) {
        return listOf(emptyList())
    }
    val result: MutableList<List<String>> = ArrayList()
    for (word in wordBank) {
        if (target.indexOf(word) == 0) {
            val suffix = target.substring(word.length)
            val suffixWays = allConstruct(suffix, wordBank)
            val targetWays: MutableList<List<String>> = ArrayList()
            for (i in suffixWays.indices) {
                val temp: MutableList<String> = ArrayList(suffixWays[i])
                temp.add(word)
                targetWays.add(temp)
            }
            for (i in targetWays.indices) {
                result.add((targetWays[i]))
            }
        }
    }
    return result
}

// Write a function fib(n) that takes in a number as an argument. The function should return the nth number of the
// Fibonacci sequence.
// Using Tabulation
private fun fibTab(n: Int): Long {
    val table = LongArray(n + 1) { 0 }
    table[1] = 1

    for (i in 2 until table.size) {
        table[i] = table[i - 1] + table[i - 2]
    }

    return table[n]
}

// Say that you are a traveler on a 2d grid. You begin in the to-left corner and your goal is to travel to
// the bottom-right corner. You may only move down or right (Memoization)
// In how many ways can you travel to the goal on a grid with dimensions m*n
// Using tabulation
private fun gridTravelerTab(m: Int, n: Int): Long {
    val table: Array<LongArray> = Array(m + 1) { LongArray(n + 1) }

    for (i in 0 until m + 1)
        table.fill(LongArray(n + 1) { 0 }, i)

    table[1][1] = 1

    for (i in 0..m) {
        for (j in 0..n) {
            val current = table[i][j]

            if (j + 1 <= n)
                table[i][j + 1] += current

            if (i + 1 <= m)
                table[i + 1][j] += current
        }
    }

    return table[m][n]


}

// Write a function canSum(targetSum, numbers) that takes i a targetSum, and array of numbers as arguments.
// The function should return a boolean whether or not it is possible to generate a target sum using the numbers in the
// array
// Elements in the array can be used as many times as needed and assume all inputs are non negative
// Tabulation
private fun canSumTab(targetSum: Int, numbers: IntArray): Boolean {
    val table = BooleanArray(targetSum + 1) { false }
    table[0] = true

    for (i in 0..targetSum) {
        if (table[i]) {
            for (j in numbers) {
                if (i + j <= targetSum)
                    table[i + j] = true
            }
        }
    }
    return table[targetSum]
}

// Write a function howSum(targetSum, numbers) that takes in a targetSum and array of numbers as arguments
// The function should return an array containing any combination of elements that add up to exactly the targetSum.
// If there is no combination that adds up to the targetSum, then return null
//If there are multiple combinations possible you may return any one of them
// Tabulation
private fun howSumTab(targetSum: Int, numbers: IntArray): IntArray? {
    val table: Array<IntArray?> = Array(targetSum + 1) { null }
    table[0] = intArrayOf()

    for (i in 0..targetSum) {
        if (table[i] != null) {
            for (num in numbers) {
                if (i + num <= targetSum) {
                    val array = arrayListOf<Int>()
                    array.add(num)
                    for (ele in table[i]!!) {
                        array.add(ele)
                    }
                    table[i + num] = array.toIntArray()
                }
            }
        }
    }
    return table[targetSum]
}

// Write a function bestSum(targetSum, numbers) that take in a targetSum and an array of numbers as arguments.
// The function should return an array with the shortest combination of numbers that add up to exactly the targetSum
// If there is a tie for the shortest combination you may return any one of the shortest
// Tabulation
private fun bestSumTab(targetSum: Int, numbers: IntArray): IntArray? {
    val table: Array<IntArray?> = Array(targetSum + 1) { null }
    table[0] = intArrayOf()

    for (i in 0..targetSum) {
        if (table[i] != null) {
            for (num in numbers) {
                val array = arrayListOf<Int>()
                array.add(num)
                for (ele in table[i]!!) {
                    array.add(ele)
                }
                if (i + num <= targetSum && array.size < table[i + num]?.size ?: Int.MAX_VALUE) {
                    table[i + num] = array.toIntArray()
                }
            }
        }
    }
    return table[targetSum]
}

// Write a function canConstruct(target, wordBank) that accepts a tart string and an array of strings.
// Return a boolean indicating whether or not the target can be constructed by concatenating elements of the wordBank
// array .
// Tabulation
private fun canConstructTab(target: String, wordBank: Array<String>): Boolean {
    val table = BooleanArray(target.length + 1) { false }
    table[0] = true

    for (i in 0..target.length) {
        if (table[i]) {
            for (word in wordBank) {
                if (i + word.length <= target.length && target.substring(i, i + word.length) == word) {
                    table[i + word.length] = true
                }
                if (table[target.length])
                    return true
            }
        }
    }
    return table[target.length]
}

// Write a function countConstruct(target, wordBank) that accepts a target string and an array of strings.
// The function should return the number of ways that the target can be constructed by concatenating elements of the
// wordBank array
// You can use the words from the wordBank as many times as you want
// Tabulation
private fun countConstructTab(target: String, wordBank: Array<String>): Int {
    val table = IntArray(target.length + 1) { 0 }
    table[0] = 1

    for (i in 0..target.length) {
        for (word in wordBank) {
            if (i + word.length <= target.length && target.substring(i, i + word.length) == word) {
                table[i + word.length] += table[i]
            }
        }
    }

    return table[target.length]
}

// Write a function allConstruct(target, wordBank) that accepts a target string and an array of strings.
// The function should return a 2d array containing all of the ways the target can be constructed by concatenating
// the elements of the wordBank array. Each element of the 2d array should represent one combination that constructs the
// target
// Tabulation
fun allConstructTab(target: String, wordBank: List<String>): List<List<String>>? {
    val table: MutableList<MutableList<List<String>>> = ArrayList(target.length + 1)
    for (i in 0..target.length) {
        table[i] = ArrayList()
    }
    table[0].add(ArrayList())
    for (i in 0..target.length) {
        for (word in wordBank) {
            if (target.substring(i).indexOf(word) == 0) {
                val newCombinations: MutableList<List<String>> = ArrayList()
                for (subArray in table[i]) {
                    val subArrayTemp: MutableList<String> = ArrayList(subArray)
                    subArrayTemp.add(word)
                    newCombinations.add(subArrayTemp)
                }
                for (subArray in newCombinations) {
                    table[i + word.length].add(ArrayList(subArray))
                }
            }
        }
    }
    return table[target.length]
}

/**
 * How to solve a DP problem:
 * 1-> Notice any overlapping subproblems
 * 2-> Decide what is the trivial input for the smallest input
 * 3-> Think recursively to use memoization
 * 4-> Think iteratively to use tabulation
 * 5-> Draw it out first
 */

