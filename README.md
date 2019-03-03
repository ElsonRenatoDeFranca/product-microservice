# shoppingcart-cloud-native
This project provides a basic idea about how to build an application that adheres to microservices architecture from scratch

1- In order to run this application you have to have installed locally:

1.1) A build tool (in this case, Apache Maven)

1.2) Pivotal Cloud Foundry CLI (to push your application into PCF environment)

2- Register to create a trial account at https://console.run.pivotal.io 

2.1) Once you have created the account and logged in to your workspace you have to create a Mysql instance as a service in PCF environment (The service instance name must be exactly the same one provided in the 'manifest.yml' file);

2.2) Your 'manifest.yml' file can be validated with the tool below:

   https://codebeautify.org/yaml-validator#

3- Build the application using the build tool:

  3.1) mvn clean package
  
4- Push your application into Pivotal Cloud Foundry environment

4.1) cf login -a api.run.pivotal.io

4.2) cf push

Test your application


4- Navigate to https://console.run.pivotal.io and log in to your workspace:

4.1) Your app should 

4.2) Use Postman to consume the webservices:

4.3) GET - https://shopping-cart-service-cloud-native.cfapps.io/carts/{CART_ID}

4.3) POST - https://shopping-cart-service-cloud-native.cfapps.io/carts

4.4) POST - https://shopping-cart-service-cloud-native.cfapps.io/carts/{CART_ID}/products


