package class_10_generic

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