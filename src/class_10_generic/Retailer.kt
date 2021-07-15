package class_10_generic

/**
 * 零售店
 */
// out代表可以接受子类型赋值给父类型，如 val retailer : Retailer<Person> = Retailer<Man>()
interface Retailer<out T : Pets> {

    fun sell() : T
}

fun main() {
    // 类型为父类型的泛型，可以接受子类型的泛型值，只能作为输出---协变
    val petRetailer_ : Retailer<Pets> = CatRetailer()

    // List的泛型带out，相当于多态
    val catList : List<Cat> = listOf(Cat("Cat kitty"), Cat("Cat molly"))
    val petList = catList
    for (pet in petList){
        println("pet's name = ${pet.name}")
    }

    val dogRetailer : Retailer<Dog> = DogRetailer()
    val catRetailer : Retailer<Cat> = CatRetailer()
    val petRetailer : Retailer<Pets> = catRetailer
    petRetailer.sell()

}