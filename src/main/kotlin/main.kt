import realEstate.RealEstateInvest
import realEstate.House
import realEstate.Funding
import realEstate.Charges
import realEstate.Taxes

fun main(args: Array<String>) {

    val house: House = House(92000, 0, 18000, 630, 0.95)
    val funding: Funding = Funding(0.015, 30)
    val charges: Charges = Charges(1400, 120, 900, 800, 1200, 0.07)
    val taxes: Taxes = Taxes(30, "nude")

    val appart: RealEstateInvest = RealEstateInvest(house, funding, charges, taxes)
    appart.metrics()
}