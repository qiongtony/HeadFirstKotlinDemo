package class_10_generic

class DogRetailer : Retailer<Dog>{
    override fun sell() : Dog {
        println("Sell dog")
        return Dog("")
    }

}