class Dog(val name : String, var weight : Int, breed_param: String) {

    // 初始化块在构造函数调用后执行，初始化块的执行顺序与代码的上下顺序一致
    init {
        println("Dog $name has been created!")
    }

    val activities = arrayOf("Walks")
    val breed = breed_param.toUpperCase()

    init {
        println("The breed is $breed .")
    }
}