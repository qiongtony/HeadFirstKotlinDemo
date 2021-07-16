package class_11_lambda

// 使用类型别名代替lambda类型
typealias DoubleConversion = (Double) -> Double

fun main() {
// lambda的通用格式：{开头}结尾 x:Int 参数 -> 隔离参数和主体--方法内的内容 主体的最后一行被作为返回值
    // 可以认为lambda是一种工具，用这种工具能够产出想要的结果
    val addFive = {x : Int -> x + 5}
    val result = addFive(5)
    println("5 add 5 = $result")

    val addInts = {x : Int, y : Int -> x + y}
    // 调用lambda：invoke函数
    addInts.invoke(6, 7)
    // 简写形式
    addInts(6, 7)

    // lambda作为参数--------------------start
    // lambda的类型--函数类型
    // 格式：(parameters)-> return_type
    // 解释parameters是我们传参的类型，如{x:Int-> ...}里传参类型为Int

    // add的类型：传参为两个Int，返回值为Int
    val add : (Int, Int) -> Int = {x : Int, y : Int -> x + y}
    // 类型的自动推断，声明了类型，在赋值lambda时可以不指名参数的类型--自动对应
    val addFiveAuto : (Int) -> Int = {x -> x + 5}
    // 当只有一个参数时可以不声明参数，用it代替--编译器自动帮我们补了
    val addFiveOne : (Int) -> Int = {it + 5}

    // lambda不带返回值
    val noReturnLambda : (Int) -> Unit = {println("it value = $it")}

    println("Pass 6 to addFive：${addFive(6)}")
    println("Pass 6,7 to addInts：${result}")

    val intLambda : (Int, Int) -> Int = {x, y -> x * y}
    println("Pass 10,11 to intLambda：${intLambda(10, 11)}")

    val myLambda : () -> Unit = {println("Hi!")}
    myLambda()

    // 高阶函数：将lambda作为函数或返回值的函数
    fun convert(x : Double, converter : (Double) -> Double) : Double{
        val result = converter(x)
        println("$x is converted to $result")
        return result
    }

    fun convertFive(converter : (Int) -> Double) : Double{
        val result = converter(5)
        println("5 is converted to $result")
        return result
    }

    // 将摄氏度转化为华氏度
    convert(20.0, { c : Double -> c * 1.8 + 32 })

    // 当最后一个参数为lambda时的简写形式
   /* convert(20.0){
        c : Double -> c * 1.8 + 32
    }*/

    // 当只有一个lambda函数类型参数时可以省略()
    convertFive {
        c : Int -> c * 1.8 + 32
    }
    // lambda作为参数--------------------end

    // lambda作为返回值--------------------start
    fun getConversionLambda(str : String) : (Double) -> Double{
        when(str){
            "CentigradeToFahrenheit" -> {
                return {
                    it * 1.8 + 32
                }
            }

            "KgsToPounds" -> {
                return {
                    it * 2.204623
                }
            }

            "PoundsToUSTons" -> {
                return {
                    it / 2000.0
                }
            }
            else -> {
                return {
                    it
                }
            }
        }
    }
    // 获取kg转换为磅的lambda函数，然后将2.5kg转换为磅
    val pounds = getConversionLambda("KgsToPounds")(2.5)

    // 使用getConversionLambda获取一个摄氏度转换为华氏度的lambda，然后传入convert函数
    convert(20.0, getConversionLambda("CentigradeToFahrenheit"))

    // 传入两个lambda，返回
    fun combine(lambda : (Double) -> Double, lambda2 : (Double) -> Double) : (Double) -> Double{
        return {
            x : Double -> lambda2(lambda(x))
        }
    }

    val kgsToUSTons = combine(getConversionLambda("KgsToPounds"), getConversionLambda("PoundsToUSTons"))
    val usTons = kgsToUSTons(1000.0)
    println("1000.0kg to USTons：${usTons}")

    // 使用类型别名提高可读性--将lambda类型当成一种类的类型
    fun combineNew(lambda1 : DoubleConversion,
                    lambda2 : DoubleConversion
                   ) : DoubleConversion{
        return {
            x : Double -> lambda2(lambda1(x))
        }
    }
    // lambda作为返回值--------------------end
}

class Lambda {
}