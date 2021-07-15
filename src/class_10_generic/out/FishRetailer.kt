package class_10_generic.out

import class_10_generic.Fish

class FishRetailer : Retailer<Fish> {
    override fun sell(): Fish {
        println("Sell fish")
        return Fish("")
    }
}