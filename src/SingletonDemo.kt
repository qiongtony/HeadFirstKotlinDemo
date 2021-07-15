
/**
 * 方式1、Kotlin提供的单例方式：使用object修饰类名；
 * 编译成Java代码后可以发现，使用的是私有化构造器+静态代码块实现的
 * 问题：
 * 1、能在多线程环境下使用吗？
 * 回答：可以，static会在类加载器加载后，被线程使用前对类加上类初始化锁，是线程安全的
 * 2、用这种方式会导致类加载后实例就被创建吗？
 * 回答：跟虚拟器有关，一般情况下虚拟机也不会启动就加载所有的类，而是会用到的时候才加载
 */

/**
 * 打印结果：
 * before getInstance..."
 * get SomeSingletonInstance...
 * SomeSingleton init...
 */
fun main() {
    println("before getInstance...")
    Thread.sleep(3000)
    println("get SomeSingletonInstance...")
    SomeSingleton.sayHi()
}
object SomeSingleton{
    init {
        println("SomeSingleton init...")
    }

    fun sayHi(){

    }
}

/**
 * 方式2：传参的单例,类似于Java的双重检查单例，借鉴的是lazy实现方式
 */
final class SomeSingletonWithParams(name : String){
    private val mName : String = name
    companion object{
        @Volatile
        private var instance : SomeSingletonWithParams? = null
        fun getInstance(name: String) : SomeSingletonWithParams{
            val i = instance
            if (i != null){
                return i
            }
            return synchronized(this){
                val i2 = instance
                if (i2 != null){
                    i2
                }else{
                    val created = SomeSingletonWithParams(name)
                    instance = created
                    created
                }
            }
        }
    }
}

/**
 * 方式3：使用lazy
 */
class SomeSingletonLazy{
    companion object{
        // lazy内部默认带了同步锁
        val instance : SomeSingletonLazy by lazy {
            SomeSingletonLazy()
        }
    }
}

