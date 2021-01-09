package realEstate

import kotlin.math.pow

/* ****************************************************************** */
/* Input data classes                                                 */
/* ****************************************************************** */
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

/* ****************************************************************** */
/* Output data classes                                                */
/* ****************************************************************** */
data class HouseOutput(
        val turnover: Double,
        val grossReturn: Double
)

class FundingOutput(
        val loanInterest: Array<Double>,
        val loanRent: Double
)

/* ****************************************************************** */
/* Real Estate Investment class                                       */
/* ****************************************************************** */
class RealEstate(
        val house: House,
        val funding: Funding,
        val charges: Charges,
        val taxes: Taxes) {

    // initialize output data classes
    private lateinit var houseOutput: HouseOutput
    private lateinit var fundingOutput: FundingOutput

    // declare private variables
    private val notaryCost: Double = 0.08
    private val totalCost: Double = house.houseCost*(1+notaryCost) + house.works

    private fun houseCalc(){
        // calculate turnover and grossReturn
        houseOutput = HouseOutput(
                house.rent * 12 * house.occupationRate,
                house.rent * 12 * house.occupationRate / totalCost * 100)
    }

    private fun fundingCalc(){
        // compute loan amount for each month
        val loanAmount: Double = totalCost - house.inputCapital
        val loanRent: Double = loanAmount * funding.loanRate/12/100 / (1-(1 + funding.loanRate/12/100).pow(-12*funding.loanDuration))

        println(totalCost)
        println(loanAmount)
        println(loanRent)

    }

    private fun chargesCalc(){
        // to implement
    }

    fun metrics(){
        houseCalc()
        fundingCalc()
        chargesCalc()


        println("Real Estate Invest Metrics")
    }
}
