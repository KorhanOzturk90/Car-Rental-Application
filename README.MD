# Car Rental Application

This application provides a RESTful API enabling users to get an 
estimation on the cost of their car rental. It expects users to provide
the basic information about their trip including type of the car they
are hiring (*can be SUV, CAR, VAN, BUS*), the fuel type of the car,
the cities they are planning on visiting, whether the car has AC and 
whether the number of people travelling.

The application uses `Dropwizard Framework` for providing the RESTful API and `Gradle` tool for dependency management.
Gradle commands can be used to compile, test and run the application. 
 
 Following commands can be used:
 `gradle build` Builds the project 
 `gradle test` Runs the tests 
 `gradle run` starts the embedded Jetty server (Port 8090 by default) and exposes the `/rent-a-car` endpoint.
 
 **Note:** gradle wrapper (`gradlew`) can be used to spin up the application
  if you don't have gradle installed on your PC.
  
  ### Sample Request:
  http://0.0.0.0:8090/rent-a-car?carType=BUS&fuelType=DIESEL&hasAC=false&cities=Pune&cities=Mumbai&cities=Delhi&noPassengers=4