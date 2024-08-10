# Tool Rental Exercise (ss0824)

## Overview
This Java exercise contains example code for managing a tool rental enterprise.
There is no UI for the project, but JUnit tests are included to demonstrate it working.

## Tests
The <b>RequiredTests.java</b> file contains the JUnit tests that demonstrate the code working in a variety of situations.

Further JUnit tests are provided that test each component of the solution.

## Architecture

These are the components of the architecture:

1. Simple POJOs representing the data types:
    * <b>Tool</b> - associates a tool code with a tool type and brand
    * <b>PriceRules</b> - associates a tool type with a daily charge rate and other attributes describing pricing rules
    * <b>CheckoutRequest</b> - data associated with a rental request

2. Rental Agreement
    * <b>RentalAgreement</b> - object containing all pertinent information related to a rental of a tool

3. Registry objects containing data you'd normally put in a database but which is hardcoded here for simplicity
    * <b>ToolRegistry</b> - list of all tools available to rent
    * <b>PriceRegistry</b> - list of pricing rules for each tool type

4. Day Counter
    * <b>DayCounter</b> - class containing logic for counting days by type

5. JUnit tests
    * All classes have an associated JUnit test class
    * Additionally, there is a RequiredTests test class that try out all the various required top-level testing scenarios
