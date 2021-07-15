package class_10_generic.out

import class_10_generic.Dog

class DogRetailer : Retailer<Dog> {
    override fun sell() : Dog {
        println("Sell dog")
        return Dog("")
    }

}