package class_10_generic

import class_10_generic.`in`.InContest

fun main(){
    val catFuzz = Cat("Fuzz Lightyear")
    val catKatsu = Cat("Katsu")
    val fishFinny = Fish("Finny McGraw")

    // 创建猫咪比赛
    val catContest = Contest<Cat>()
    catContest.addScore(catFuzz, 50)
    catContest.addScore(catKatsu, 45)
    val topCat = catContest.getWinners().first()
    println("Cat contest winner is ${topCat.name}")

    // 创建宠物比赛
    val petContest = Contest<Pets>()
    petContest.addScore(catFuzz, 50)
    petContest.addScore(fishFinny, 60)
    val topPet = petContest.getWinners().first()
    println("Pet contest winner is ${topPet.name}")

    // 猫兽医
    val catVet = Vet<Cat>()
    // 鱼兽医
    val fishVet = Vet<Fish>()
    // 宠物兽医
    val petVet = Vet<Pets>()
    // 猫兽医可以治疗猫，不能治疗其他宠物
    catVet.treat(Cat("Fuzz Lightyear"))
    /*
     该行报错
    catVet.treat(Fish("Finny Mcgraw")
     */
    petVet.treat(Cat("Katsu"))
    petVet.treat(Fish("Finny Mcgraw"))

    val catNewContest = InContest<Cat>(catVet)
    val petNewContest = InContest<Pets>(petVet)
    // petVet可以治疗cat，如果泛型不加in---逆变，编译会报错
    val catInContest = InContest<Cat>(petVet)
}

class Contest <T : Pets>{
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