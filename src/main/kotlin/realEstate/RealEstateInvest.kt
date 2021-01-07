package realEstate

class RealEstateInvest(
        val house: Map<String,Double>,
        val funding: Map<String,Double>,
        val charges: Map<String,Double>,
        val taxes: Map<String,Int>) {

    private fun grossReturn(house: Map<String,Double>){
        // to implement
    }

    private fun fundingCalc(funding: Map<String,Double>){
        // to implement
    }

    private fun chargesCalc(charges: Map<String,Double>){
        // to implement
    }

    fun metrics(){
        grossReturn(house)
        fundingCalc(funding)
        chargesCalc(charges)

        println("Real Estate Invest Metrics")
    }
}