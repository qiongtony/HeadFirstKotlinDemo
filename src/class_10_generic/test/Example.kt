package class_10_generic.test

open class Food

class VeganFood : Food()

// Sellers
interface Seller<T>

class FoodSeller : Seller<Food>

class VeganFoodSeller : Seller<VeganFood>

// Consumers
interface Consumer<T>

class Person : Consumer<Food>

class Vegan : Consumer<VeganFood>


    fun main(){
        // 逆变，参数，不能在val/var变量内，变量会输出？
        var foodSeller : Seller<out Food>
        foodSeller = FoodSeller()
        foodSeller = VeganFoodSeller()

        // 协变，返回值，val/var
        var veganFoodConsumer : Consumer<in VeganFood>
        veganFoodConsumer = Vegan()
        veganFoodConsumer = Person()
    }


/*
    fun main(){
        var foodSeller : Seller<Food>
        foodSeller = FoodSeller()
        foodSeller = VeganFoodSeller()

        var veganFoodConsumer : Consumer<VeganFood>
        veganFoodConsumer = Vegan()
        veganFoodConsumer = Person()
    }
 */