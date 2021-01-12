package realEstate

data class Taxes(
        val marginalRate: Int,
        val taxationRegime: String
)

class TaxesCompute(taxes: Taxes,
                   totalCharges: Double,
                   fundingOutput: FundingOutput,
                   houseOutput: HouseOutput){

    // private variable
    private val loanDuration = fundingOutput.loanInterest.size
    private val interestArray: Array<Double> = fundingOutput.loanInterest
    private val loanAmount = houseOutput.turnover
    private val totalCharges = totalCharges
    private val taxes: Taxes = taxes
    private val csg: Double = 17.2

    // output variable
    var taxesArray: Array<Double> = Array(loanDuration){0.0}

    private fun sciCalc(){
        for(i in 0 until loanDuration){
            taxesArray[i] = (loanAmount - totalCharges - interestArray[i]) * (15 + csg)/100
        }
    }

    private fun nudeCalc(){
        for(i in 0 until loanDuration){
            taxesArray[i] = (loanAmount - totalCharges - interestArray[i]) * (taxes.marginalRate + csg)/100
        }
    }

    fun start(){
        when(taxes.taxationRegime){
            "nude" -> nudeCalc()
            "sci" -> sciCalc()
            else -> println("Taxation regime unknown")
        }
    }
}