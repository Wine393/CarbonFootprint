🌿 Carbon Footprint Calculator

A Java console-based application that estimates personal CO₂ emissions across five daily activity categories using object-oriented programming principles.

Overview
The Carbon Footprint Calculator is a Java console application designed to help users track and understand their personal carbon dioxide (CO₂) equivalent emissions from everyday activities. Users can log up to 30 activities across five categories — transportation, home energy, food, digital device usage, and waste disposal — and receive an immediate emission estimate, an eco-friendly tip, and a final summary report with a carbon rating. This project was developed as an academic exercise in object-oriented design, demonstrating core OOP pillars: abstraction, inheritance, polymorphism, and encapsulation, alongside practical exception handling and interface-driven architecture.

Project Scope
Goals

1. Provide a simple, accurate tool for estimating personal carbon emissions from five major lifestyle categories.
2. Demonstrate a clean object-oriented architecture with a well-defined class hierarchy rooted in an abstract base class.
3. Apply two custom interfaces (CarbonCalculable, Reportable) and two custom exceptions (NegativeValueException, InvalidActivityTypeException) to enforce correctness and type safety.
4. Produce a readable console report with a qualitative CO₂ rating.

Features

✅ Five activity types, each with validated emission factors backed by peer-reviewed sources
✅ Custom checked exceptions for negative values and invalid type strings
✅ Polymorphic dispatch — all activities stored as CarbonSource[]
✅ Per-activity eco-tips printed immediately after logging
✅ Full report with total CO₂ in kg, tonnes, and a four-tier qualitative rating
✅ Input validation with retry loops for all numeric fields
✅ Recycling modifier for waste (60% reduction when recycled)
✅ Passenger sharing modifier for transport (per-person allocation)

Architecture
CarbonSource (abstract)
│
├── TransportActivity   implements CarbonCalculable, Reportable
├── HomeEnergyActivity  implements CarbonCalculable, Reportable
├── FoodActivity        implements CarbonCalculable, Reportable
├── DigitalActivity     implements CarbonCalculable, Reportable
└── WasteActivity       implements CarbonCalculable, Reportable

Interfaces
├── CarbonCalculable  — calculateEmissions(), showTip(), showEmissions()
└── Reportable        — getSummary()

Exceptions
├── NegativeValueException
└── InvalidActivityTypeException

Support Classes
├── EmissionFactor       — data holder for factor type, value, unit
└── FootprintCalculator  — manages CarbonSource[] array (max 30)

Entry Point
└── Main                 — interactive console menu and input handling

Emission Formulas

1. Transport = (distanceKM × factor) / passengers  kg CO₂
2. Home Energy = monthlyKwh × months × factor kg CO₂
3. Food = servingsPerWeek × weeks × factor kg CO₂
4. Digital = hoursPerDay × days × factor kg CO₂
5. Waste = weightKg × factor × (recycled ? 0.4 : 1.0) kg CO₂

Emission factors are sourced from the UK DESNZ Conversion Factors (2023), IEA Emissions Factors (2023), Poore & Nemecek (2018, Science), and the US EPA WARM model (2022). See References for full APA citations.

OOP Concepts Applied

1. Abstraction - CarbonSource is abstract; subclasses must implement all four methods
2. Inheritance - Five activity classes extend CarbonSource
3. Polymorphism - FootprintCalculator stores CarbonSource[] and calls overridden methods
4. Encapsulation - All fields are private or protected; accessed via methods only
5. Interfaces - CarbonCalculable and Reportable enforce contracts at compile time
6. Exception Handling - Checked exceptions thrown in constructors, caught in Main

References

Department for Energy Security and Net Zero & Department for Business, Energy and Industrial Strategy. (2023). Greenhouse gas reporting: Conversion factors 2023. UK Government. https://www.gov.uk/government/publications/greenhouse-gas-reporting-conversion-factors-2023
Intergovernmental Panel on Climate Change. (2021). Climate change 2021: The physical science basis — Chapter 7. Cambridge University Press. https://doi.org/10.1017/9781009157896.009
International Energy Agency. (2023). CO₂ emissions from electricity generation. IEA. https://www.iea.org/data-and-statistics/data-product/emissions-factors-2023
Poore, J., & Nemecek, T. (2018). Reducing food's environmental impacts through producers and consumers. Science, 360(6392), 987–992. https://doi.org/10.1126/science.aaq0216
Ritchie, H., Roser, M., & Rosado, P. (2020). CO₂ and greenhouse gas emissions. Our World in Data. https://ourworldindata.org/co2-emissions-from-transport
U.S. Environmental Protection Agency. (2022). Waste reduction model (WARM): Management practices — Background chapters. EPA. https://www.epa.gov/warm
