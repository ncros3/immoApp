package realEstate

data class House(
        val houseCost: Int,
        val works: Int,
        val inputCapital: Int,
        val rent: Int,
        val occupationRate: Double
)

data class Funding(
        val loanRate: Double,
        val loanDuration: Int
)

data class Charges(
        val condominiumCost: Int,
        val insurance: Int,
        val electricity: Int,
        val works: Int,
        val realEstateTaxes: Int,
        val rentManagingCost: Double
)

data class Taxes(
        val marginalRate: Int,
        val taxationRegime: String
)

data class HouseOutput(
        var turnover: Double,
        var grossReturn: Double
)

class RealEstateInvest(
        val house: House,
        val funding: Funding,
        val charges: Charges,
        val taxes: Taxes) {

    private val notaryCost: Double = house.houseCost * 0.08

    private fun houseCalc(house: House): HouseOutput{
        // to implement
        val output: HouseOutput = HouseOutput(
                house.rent * 12 * house.occupationRate,
                house.rent * 12 * house.occupationRate / house.houseCost * 100)

        return output
    }

    private fun fundingCalc(funding: Funding){
        // to implement
    }

    private fun chargesCalc(charges: Charges){
        // to implement
    }

    fun metrics(){
        println(houseCalc(house))
        fundingCalc(funding)
        chargesCalc(charges)


        println("Real Estate Invest Metrics")
    }
}