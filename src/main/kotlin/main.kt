import realEstate.RealEstateInvest

fun main(args: Array<String>) {

    val house: Map<String,Double> = mapOf(
            "houseCost" to 92000.0,
            "inputCapital" to 18000.0,
            "rent" to 630.0,
            "occupationRate" to 0.95
    )

    val funding: Map<String,Double> = mapOf(
            "loanRate" to 1.5,
            "loanDuration" to 30.0
    )

    val charges: Map<String,Double> = mapOf(
            "condominiumCost" to 1400.0,
            "insurance" to 120.0,
            "electricity" to 900.0,
            "works" to 800.0,
            "realEstateTaxes" to 1200.0,
            "rentManagingCost" to 0.07
    )

    val taxes: Map<String,Int> = mapOf(
            "marginalRate" to 30,
            "taxationRegime" to 1
    )

    val appart: RealEstateInvest = RealEstateInvest(house, funding, charges, taxes)
    appart.metrics()
}