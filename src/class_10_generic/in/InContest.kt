package class_10_generic.`in`

import class_10_generic.*
import class_10_generic.out.CatRetailer
import class_10_generic.out.DogRetailer
import class_10_generic.out.Retailer

// in，逆变，代表可以用父类型替代子类型
// 逆变，使用场景：输入，如方法参数，如果想限制范围，那就在不需要的泛型内用不带in的泛型，在需要逆变的地方加上T
// 下面的代码只在传递构造参数vet时可以逆变，而普通的Vet<Cat> = Vet<Pets>()是不行的
class InContest <T : Pets>(var vet : Vet<in T>){
    val scores : MutableMap<T, Int> = mutableMapOf()

    fun addScore(t : T, score : Int = 0){
        if (score >= 0){
            scores.put(t, score)
        }
    }

    fun getWinners() : MutableSet<T>{
        val highScore = scores.values.max()
        val winners : MutableSet<T> = mutableSetOf()
        for ((t, score) in scores){
            // 将最高分者加到set
            if (score == highScore){
                winners.add(t)
            }
        }
        return winners
    }
}

fun main() {
    val catFuzz = Cat("Fuzz Lightyear")
    val catKatsu = Cat("Katsu")
    val fishFinny = Fish("Finny McGraw")

    val catVet = Vet<Cat>()
    val fishVet = Vet<Fish>()
    val petVet = Vet<Pets>()

    catVet.treat(catFuzz)
    petVet.treat(catKatsu)
    petVet.treat(fishFinny)

    val catContest = InContest<Cat>(catVet)
    catContest.addScore(catFuzz, 50)
    catContest.addScore(catKatsu, 45)
    val topCat = catContest.getWinners().first()
    println("Cat contest winner is ${topCat.name}")

    val petContest = InContest<Pets>(petVet)
    petContest.addScore(catFuzz, 50)
    petContest.addScore(fishFinny, 56)

    // 使用逆变，传入泛型为父类型的参数
    val fishContest = InContest<Fish>(petVet)
    val dogRetailer : Retailer<Dog> = DogRetailer()
    val catRetailer : Retailer<Cat> = CatRetailer()
    // 使用协变，泛型为子类型，赋值给父类型的遍历
    val petRetailer : Retailer<Pets> = CatRetailer()
    petRetailer.sell()
}