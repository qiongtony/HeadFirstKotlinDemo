
// Kotlin1.3以后可以忽略参数
fun main() {
    val d = DrumKit(true, true)
    d.playTopHat()
    d.playSnare()

    d.hasSnare = false

    d.playTopHat()
    d.playSnare()
}

class DrumKit(var hasTopHat : Boolean, var hasSnare : Boolean){
    fun playTopHat(){
        if (hasTopHat) {
            println("ding ding ba-da-bing!")
        }
    }

    fun playSnare(){
        if(hasSnare){
            println("bang bang bang!")
        }
    }
}