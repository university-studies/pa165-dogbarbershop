pa165
=====

Task
----

We present you the Dog barbershop. People dress up their dogs and they want them to be beautiful. Your task is to create an information system supporting the dog barbershop. For one, the application should keep record of customers (their name, surname, address, and phone). The customers can bring as many pets as they want. The system should keep track of dog’s name, breed, age and other needed attributes. The barbershop employees are also kept in the system. The employees have the same attributes as the customers with the addition of their salary. The dogs can enjoy different services and the services must be treated as such. The system should keep track of the performed service, its length and price.

DogBarberShop-web
-----------------
`cd DogBarberShop/`
`mvn install/`
`mvn tomcat7:run`

DogBarberShop-cli
-----------------

Implements REST client api using in a maven module using CLI.

### Examples
~~~~
`cd DogBarberShop/`
~~~~

print help
~~~~
mvn -q -pl DogBarberShop-cli exec:java
~~~~
query entity customer
~~~~
`mvn -q -pl DogBarberShop-cli exec:java -Dexec.args=' -e customer getall'`
~~~~
~~~~
`mvn -q -pl DogBarberShop-cli exec:java -Dexec.args='-s http://localhost:8080/pa165/webresources -e customer getbyid 4321'`
~~~~
create new customer record in db
~~~~
`mvn -q -pl DogBarberShop-cli exec:java -Dexec.args='-e customer add name00 surname00 addr00 800800789'`
~~~~
query entity dog
~~~~
`mvn -q -pl DogBarberShop-cli exec:java -Dexec.args='-e dog getall'`
~~~~
create new dog record in db
~~~~
`mvn -q -pl DogBarberShop-cli exec:java -Dexec.args='-e dog add name00 breed00 1.1.2020'`
~~~~
