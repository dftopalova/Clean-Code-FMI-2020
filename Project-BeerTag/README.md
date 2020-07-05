# Beer Tag

**Project description**

BEER TAG enables users to manage all the beers that they have drank and want to drink.
Each beer has detailed information about it from the ABV (alcohol by volume) to the style and description.
Data is community driven and every beer lover can add new beers and edit missing information on already existing ones.
Also, BEER TAG allows them to rate a beer and calculates average rating from different users.

*Tech stack*
* The JDK version is 1.8
* Used tiered project structure (separated application components in 3 layers - repositories, services and controllers)
* Used SpringMVC and SpringBoot framework
* For Persistence is used MySQL/MariaDB
* Used Hibernate in the Persistence layer
* Used Spring Security to handle user registration and user roles
* Registered users have at least one of the two roles: user and
   administrator
