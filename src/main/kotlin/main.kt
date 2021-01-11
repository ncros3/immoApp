import realEstate.*

fun main(args: Array<String>) {

    val house: House = House(92000, 0, 18800, 630, 0.95)
    val funding: Funding = Funding(1.37, 15)
    val charges: Charges = Charges(1510, 120, 900, 800, 1200, 0.07)
    val taxes: Taxes = Taxes(30, "nude")

    val appart: RealEstate = RealEstate(house, funding, charges, taxes)
    appart.metrics()
}