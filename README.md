# Data base DBMS 
Biomaterials inventory

Through this database our company, BENGMAT biomaterials, wants to provide an easy way of buying all sorts of biomaterials.
By having a user account, each customer will be able to buy any of our products, see every product that can be purchased and 
go thrugh a hisotry of their past transactions. A user will have an specific username and password to properly login. The user 
will be either a director, a worker or a client. In addition, each cutomer will be assigned a category and some benefits 
depending on the total amount of purchases made.

- DATABASE PRESETTINGS

The way someone interacts with the database will depend on who is trying to access it. Beafore unlocking the full
capabilities of this database, some data has to be inserted in order to have something to operate with.

1. DIRECTOR: When creating the database from scratch, we have to first create a director that will access the category and add 
the null category putting the max value to that category. From this moment it is possible to create clients. Once a client 
is created, the null category will be assigned (with 0 benefits). 

2. WORKER: Before a client can purchase anything, the worker will have to create some biomaterials, with their respective
utility and maintenance suggestions.

3. CLIENT: he/she will have to insert the bank account (in order to access the marketplace to buy the before mentioned 
biomaterials).

Once these steps have been followed in the correct order, the database and the corresponding user interface will be ready to use.


- USER INTERFACE CHARACTERISTICS:

We have developed a very clean and intuitive UI in order to provide the user with the best experience possible. Depending on the
relation of our company and the user, different capabilities will be available:

Director: check account settings, list and remove both clients and workers, check the finantial status of the company and
add a category.

Worker: check account settings, list inventory, add and delete a certain product, create fetures and update a product's 
features, check the transaction records of the company and list all clients. 

Client: check their account settings and their BENGMAT club, see a full list of biomaterials in the marketplace and add 
them to their cart and finally make a purchase. Every purchase will give the client fidelity points in order to ascend to a 
higher category and enjoy a range of benefits.


- ADDITIONAL DOCUMENTATION

The different diagrams that have been created during the development of the database have been added to the Github projects 
in a folder under the name "Diagrams"

- TO CREATE NEW DIRECTORS: key = 12345678
- TO CREATE NEW WORKERS: key = 1234	
	







