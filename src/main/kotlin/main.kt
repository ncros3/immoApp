import realEstate.*

fun main(args: Array<String>) {

    val house: House = House(92000, 0, 18800, 630, 0.95)
    val funding: Funding = Funding(1.37, 15)
    val charges: Charges = Charges(1510.0, 120.0, 900.0, 500.0, 1200.0, 0.0)
    val taxes: Taxes = Taxes(30, "nude")

    val project: RealEstate = RealEstate(house, funding, charges, taxes)
    project.compute()
}