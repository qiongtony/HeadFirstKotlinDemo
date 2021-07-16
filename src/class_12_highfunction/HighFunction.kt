package class_12_highfunction

import java.lang.StringBuilder

data class Grocery(val name : String, val category : String,
                   val unit : String, val unitPrice : Double,
                   val quantity : Int
                   )

fun main() {
    val groceries = listOf<Grocery>(
            Grocery("Tomatoes", "Vegetable", "1b", 3.0, 3),
        Grocery("Mushrooms", "Vegetable", "1b", 4.0, 1),
        Grocery("Bagels", "Bakery", "Pack", 1.5, 2),
        Grocery("Olive oil", "Pantry", "Bottle", 6.0, 1),
        Grocery("Ice cream", "Frozen", "Pack", 3.0, 2))

    /*val highestUnitPrice = groceries.maxBy {
        it.unitPrice * 5
    }
    println("highestUnitPrice:$highestUnitPrice")
    // 找出quantity最小的元素
    val lowestQuantity = groceries.minBy { it.quantity }
    println("lowestQuantity:$lowestQuantity")
    // 求出quantity的总值，返回类型为Int
    val sumQuantity = groceries.sumBy {
        it.quantity
    }
    println("sumQuantity:$sumQuantity")
    //
    val totalPrice = groceries.sumByDouble {
        it.quantity * it.unitPrice
    }
    println("totalPrice:$totalPrice")

    val map = groceries.map { it.unitPrice * 0.5 }
    val intList = listOf<Int>(1, 2, 3, 4)
    val doubleInts = intList.map { it * 2 }
    println("doubleInts = ${doubleInts.toString()}")*/

    val vegetables = groceries.filter {
        it.category == "Vegetable"
    }
    println("vegetables：$vegetables")
    val notFrozen = groceries.filterNot {
        it.category == "Frozen"
    }
    println("notFrozen：$notFrozen")

    val groceryName = groceries.map { it.name }
    println("groceryName:$groceryName")

    val halfUnitPrice = groceries.map { it.unitPrice * 0.5 }
    println("halfUnitPrice:$halfUnitPrice")
    val newPrices = groceries.filter {
        it.unitPrice > 3.0
    }.map { it.unitPrice * 2 }
    println("newPrices:$newPrices")
    // foreach做遍历
    groceries.forEach {
        println("${it.name}")
    }

    println("filter with foreach")
    groceries.filter {
        it.unitPrice > 3.0
    }.forEach {
        println("${it.name}")
    }

    // groupBy根据元素的属性对list进行分组，返回为Map，key为属性，value为List<>
    val groupBy = groceries.groupBy {
        it.category
    }
    println("groupBy category")
    groupBy.entries.forEach {
        println("category = ${it.key} value = ${it.value}")
    }

    val ints = listOf<Int>(1, 2, 3, 4)
    // 初始值，然后遍历list进行一个操作，完成后返回结果值
    val sumOfInts = ints.fold(0) { runningSum, item ->
        runningSum + item
    }
    println("sumOfInts = ${sumOfInts}")

    val productOfInts = ints.fold(1) { runningProduct, item -> runningProduct * item }
    println("productOfInts = ${productOfInts}")

    val names = groceries.fold("") { str, item ->
        str + " ${item.name}"
    }
    println("names = ${names}")

    val changeFrom50 = groceries.fold(50.0) { change, item ->
        change - item.unitPrice * item.quantity
    }
    println("changeFrom50 = ${changeFrom50}")

    val vegetablesPrices = groceries.filter {
        it.category == "Vegetable"
    }.sumByDouble {
        it.unitPrice * it.quantity
    }
    println("vegetablesPrices = $vegetablesPrices")

    val priceLower5Names = groceries.filter {
        it.unitPrice < 5.0
    }.map {
        it.name
    }
    println("priceLower5Names = $priceLower5Names")

    groceries.groupBy {
        it.category
    }.forEach { t, u ->
        run {
            val sb = StringBuilder()
            sb.append(t)
            val sum = u.sumByDouble {
                it.unitPrice * it.quantity
            }
            sb.append("'s totalSum = ${sum}")
            println(sb)
        }
    }
}
