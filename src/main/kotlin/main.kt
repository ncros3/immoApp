import realEstate.*

fun main(args: Array<String>) {

    val house: House = House(71000, 0, 22000, 630, 0.95)
    val funding: Funding = Funding(2.5, 30)
    val charges: Charges = Charges(1510.0, 120.0, 900.0, 500.0, 1200.0, 0.0)
    val taxes: Taxes = Taxes(30, "sci")

    val project: RealEstate = RealEstate(house, funding, charges, taxes)
    // compute project parameters
    project.compute()
    // print result
    project.result()
}