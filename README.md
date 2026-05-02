# 🌿 Carbon Footprint Calculator

> A Java console-based application that estimates personal CO₂ emissions across five daily activity categories using object-oriented programming principles.

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=java)
![OOP](https://img.shields.io/badge/Paradigm-Object--Oriented-blue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen?style=flat-square)
![Platform](https://img.shields.io/badge/Platform-NetBeans%20%7C%20IntelliJ%20%7C%20CLI-lightgrey?style=flat-square)

---

## Table of Contents

- [Overview](#overview)
- [Project Scope](#project-scope)
- [Features](#features)
- [Architecture](#architecture)
- [Class Diagram Summary](#class-diagram-summary)
- [Emission Formulas](#emission-formulas)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [OOP Concepts Applied](#oop-concepts-applied)
- [References](#references)
- [License](#license)

---

## Overview

The **Carbon Footprint Calculator** is a Java console application designed to help users track and understand their personal carbon dioxide (CO₂) equivalent emissions from everyday activities. Users can log up to 30 activities across five categories — transportation, home energy, food, digital device usage, and waste disposal — and receive an immediate emission estimate, an eco-friendly tip, and a final summary report with a carbon rating.

This project was developed as an academic exercise in object-oriented design, demonstrating core OOP pillars: **abstraction**, **inheritance**, **polymorphism**, and **encapsulation**, alongside practical exception handling and interface-driven architecture.

---

## Project Scope

### Goals

- Provide a simple, accurate tool for estimating personal carbon emissions from five major lifestyle categories.
- Demonstrate a clean object-oriented architecture with a well-defined class hierarchy rooted in an abstract base class.
- Apply two custom interfaces (`CarbonCalculable`, `Reportable`) and two custom exceptions (`NegativeValueException`, `InvalidActivityTypeException`) to enforce correctness and type safety.
- Produce a readable console report with a qualitative CO₂ rating.

### In Scope

| Category | What is tracked |
|---|---|
| Transport | Vehicle type, distance (km), number of passengers |
| Home Energy | Energy source, monthly kWh consumption, number of months |
| Food | Meal type, servings per week, number of weeks |
| Digital | Device type, hours of use per day, number of days |
| Waste | Waste category, weight (kg), whether it is recycled |

### Out of Scope

- GUI or web interface
- Database persistence or file I/O
- Real-time energy meter integration
- Multi-user accounts
- Network or API connectivity

### Target Users

This project is aimed at:
1. **Computer science students** learning OOP in Java.
2. **Educators** seeking a real-world, values-driven OOP case study.
3. **Individuals** curious about estimating their personal carbon footprint from a command-line tool.

---

## Features

- ✅ Five activity types, each with validated emission factors backed by peer-reviewed sources
- ✅ Custom checked exceptions for negative values and invalid type strings
- ✅ Polymorphic dispatch — all activities stored as `CarbonSource[]`
- ✅ Per-activity eco-tips printed immediately after logging
- ✅ Full report with total CO₂ in kg, tonnes, and a four-tier qualitative rating
- ✅ Input validation with retry loops for all numeric fields
- ✅ Recycling modifier for waste (60% reduction when recycled)
- ✅ Passenger sharing modifier for transport (per-person allocation)

---

## Architecture

```
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
```

---

## Class Diagram Summary

```
┌────────────────────────────────┐
│  <<abstract>> CarbonSource     │
│  # activityName : String       │
│  # emissionFactor : double     │
│  + showEmissions() : void      │
│  + showTip() : void            │
│  + calculateEmissions() : dbl  │
│  + getSummary() : String       │
└──────────────┬─────────────────┘
               │ extends (5 subclasses)
    ┌──────────┴─────────────────────────────────────┐
    │          │           │           │              │
Transport  HomeEnergy    Food       Digital         Waste
Activity   Activity    Activity    Activity        Activity
```

Both `CarbonCalculable` and `Reportable` interfaces are implemented by all five subclasses, enforcing the presence of all required methods at compile time.

---

## Emission Formulas

| Activity | Formula | Unit |
|---|---|---|
| Transport | `(distanceKM × factor) / passengers` | kg CO₂ |
| Home Energy | `monthlyKwh × months × factor` | kg CO₂ |
| Food | `servingsPerWeek × weeks × factor` | kg CO₂ |
| Digital | `hoursPerDay × days × factor` | kg CO₂ |
| Waste | `weightKg × factor × (recycled ? 0.4 : 1.0)` | kg CO₂ |

Emission factors are sourced from the UK DESNZ Conversion Factors (2023), IEA Emissions Factors (2023), Poore & Nemecek (2018, *Science*), and the US EPA WARM model (2022). See [References](#references) for full APA citations.

---

## Getting Started

### Prerequisites

- Java 17 or later
- NetBeans IDE 19+ **or** any Java IDE / terminal with `javac`

### Clone the Repository

```bash
git clone https://github.com/your-username/carbon-footprint-calculator.git
cd carbon-footprint-calculator
```

### Compile and Run (Terminal)

```bash
cd src
javac carbonfootprintcalculator/*.java
java carbonfootprintcalculator.Main
```

### Open in NetBeans

1. **File → Open Project** and select the cloned folder.
2. Ensure the project source root is set to `src/`.
3. Press **F6** (Run Project) or right-click `Main.java → Run File`.

---

## Usage

```
  ================================================
       Carbon Footprint Calculator  v1.0
       Object-Oriented Java — Console App
  ================================================

  ---- MAIN MENU ----
  1.  Add Transport Activity
  2.  Add Home Energy Activity
  3.  Add Food Activity
  4.  Add Digital Activity
  5.  Add Waste Activity
  6.  List All Activities
  7.  Show Full Report
  8.  Exit
  -------------------
  Your choice: 1

  --- Add Transport Activity ---
  Vehicle types: car | bus | train | plane | motorbike
  Vehicle type  : car
  Distance (km) : 150
  Passengers    : 2
  [+] Activity added. Total recorded: 1

  [Transport]  car        |  150.00 km | 2 passenger(s) | 15.7500 kg CO2
  Tip: Sharing a car or taking the train cuts transport emissions by 50–80%.
```

### Report Output

```
  ============================================================
         CARBON FOOTPRINT FINAL REPORT
  ============================================================
    1.  Transport  | car        | 150.00 km | 2 pax | 15.7500 kg CO2
    2.  Food       | beef       | 5 srv x 4 wks    | 132.2000 kg CO2
  ============================================================
  Total activities recorded : 2
  Total CO2 emissions       : 147.9500 kg
  Equivalent in tonnes      : 0.147950 t CO2
  ============================================================
  RATING: *   MODERATE   — Room for meaningful improvement.
  ============================================================
```

---

## Project Structure

```
CarbonFootprintCalculator/
│
├── src/
│   └── carbonfootprintcalculator/
│       ├── CarbonCalculable.java          # Interface
│       ├── Reportable.java                # Interface
│       ├── NegativeValueException.java    # Custom exception
│       ├── InvalidActivityTypeException.java  # Custom exception
│       ├── EmissionFactor.java            # Data holder
│       ├── CarbonSource.java              # Abstract base class
│       ├── FootprintCalculator.java       # Activity manager
│       ├── TransportActivity.java         # Subclass
│       ├── HomeEnergyActivity.java        # Subclass
│       ├── FoodActivity.java              # Subclass
│       ├── DigitalActivity.java           # Subclass
│       ├── WasteActivity.java             # Subclass
│       └── Main.java                      # Entry point
│
├── README.md
└── LICENSE
```

---

## OOP Concepts Applied

| Concept | Implementation |
|---|---|
| **Abstraction** | `CarbonSource` is abstract; subclasses must implement all four methods |
| **Inheritance** | Five activity classes extend `CarbonSource` |
| **Polymorphism** | `FootprintCalculator` stores `CarbonSource[]` and calls overridden methods |
| **Encapsulation** | All fields `private` or `protected`; accessed via methods only |
| **Interfaces** | `CarbonCalculable` and `Reportable` enforce contracts at compile time |
| **Exception Handling** | Checked exceptions thrown in constructors, caught in `Main` |

---

## References

- Department for Energy Security and Net Zero & Department for Business, Energy and Industrial Strategy. (2023). *Greenhouse gas reporting: Conversion factors 2023*. UK Government. https://www.gov.uk/government/publications/greenhouse-gas-reporting-conversion-factors-2023

- Intergovernmental Panel on Climate Change. (2021). *Climate change 2021: The physical science basis — Chapter 7*. Cambridge University Press. https://doi.org/10.1017/9781009157896.009

- International Energy Agency. (2023). *CO₂ emissions from electricity generation*. IEA. https://www.iea.org/data-and-statistics/data-product/emissions-factors-2023

- Poore, J., & Nemecek, T. (2018). Reducing food's environmental impacts through producers and consumers. *Science, 360*(6392), 987–992. https://doi.org/10.1126/science.aaq0216

- Ritchie, H., Roser, M., & Rosado, P. (2020). *CO₂ and greenhouse gas emissions*. Our World in Data. https://ourworldindata.org/co2-emissions-from-transport

- U.S. Environmental Protection Agency. (2022). *Waste reduction model (WARM): Management practices — Background chapters*. EPA. https://www.epa.gov/warm

---

## License

This project is licensed under the MIT License. See `LICENSE` for details.

---

*Built with Java · Designed for learning · Backed by science*
