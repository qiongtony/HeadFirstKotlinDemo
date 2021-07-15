package class_10_generic

class FishRetailer : Retailer<Fish>{
    override fun sell(): Fish {
        println("Sell fish")
        return Fish("")
    }
}