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
        val condominiumCost: Double,
        val insurance: Double,
        val electricity: Double,
        val works: Double,
        val realEstateTaxes: Double,
        val rentManagingCost: Double
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
    private var totalCharges: Double = 0.0
    private lateinit var taxesCompute: TaxesCompute

    // output metrics
    private var cashFlow: Array<Double> = Array(funding.loanDuration){0.0}
    private var netReturn: Array<Double> = Array(funding.loanDuration){0.0}
    private var netRentability: Array<Double> = Array(funding.loanDuration){0.0}
    private var netResult: Array<Double> = Array(funding.loanDuration){0.0}

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

        // Declare interest Array
        var loanInterest: Array<Double> = Array(funding.loanDuration){0.0}
        // intermediate values
        var capitalToPay: Double = loanAmount
        var depreciation: Double
        // compute interest for each year
        for (i in 0 until funding.loanDuration) {
            //Compute capital to pay for this year
            if (i>0){
                depreciation = loanRent*12 - loanInterest[i-1]!!
                capitalToPay -= depreciation
            }
            // compute interest
            loanInterest[i] = funding.loanRate / 100 * capitalToPay
        }

        // create output object with computed values
        fundingOutput = FundingOutput(loanInterest, loanRent)
    }

    private fun chargesCalc(){
        // calculate charges for each year
        totalCharges = charges.works + charges.realEstateTaxes + charges.insurance + charges.condominiumCost + charges.electricity + (charges.rentManagingCost * houseOutput.turnover)
    }

    private fun metrics(){
        // compute metrics for each year of loan
        for(index in 0 until funding.loanDuration){
            // compute cash flow
            cashFlow[index] = houseOutput.turnover - totalCharges - fundingOutput.loanRent*12 - taxesCompute.taxesArray[index]
            // compute net result & return
            netResult[index] = houseOutput.turnover - totalCharges - taxesCompute.taxesArray[index] - fundingOutput.loanInterest[index]
            netReturn[index] = netResult[index] / houseOutput.turnover * 100
            // compute net financial rentability
            netRentability[index] = netResult[index] / house.inputCapital * 100
        }
    }

    fun result(){
        println("Cash Flow : ${cashFlow[0]}")
        println("Gross return : ${houseOutput.grossReturn}")
        println("Net Result : ${netResult[0]}")
        println("Net Return : ${netReturn[0]}")
        println("Net Rentability : ${netRentability[0]}")
    }

    fun compute(){
        // compute investment parameters : total cost, funding, charges...
        houseCalc()
        fundingCalc()
        chargesCalc()

        // compute taxes
        taxesCompute = TaxesCompute(taxes, totalCharges, fundingOutput, houseOutput)
        taxesCompute.start()

        // compute output metrics : cashflow, net result, return & rentability
        metrics()
    }
}