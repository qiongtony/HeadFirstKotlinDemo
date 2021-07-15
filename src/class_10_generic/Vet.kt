package class_10_generic

class Vet<T : Pets> {
    fun treat(t : T){
        println("Treat Pet ${t.name}")
    }
}