package class_10_generic

/**
 * 猫咪零售店
 */
class CatRetailer : Retailer<Cat> {
    override fun sell(): Cat {
        println("Sell cat")
        return Cat("")
    }
}