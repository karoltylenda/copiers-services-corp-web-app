# copiers-services-corp-web-app

<p align="center">
  <img src="https://user-images.githubusercontent.com/64745872/106356217-4d1ea000-62fe-11eb-80e8-225287b63824.png" width="400" height="400"  />
  </p>
  
## Table of contents
* [Mission](#mission)
* [Intruduction](#intruduction)
* [Technology](#technology)
* [Requirement](#requirement)
  
 
### Mission

This is a REST API Application that gives you opportunity to manage with history of serviced devices(service visits, replacement of consumables etc.).
Each client are capable of creating orders for a given number of devices and articles.
From customer site it will be possible to order consumables or service directly to a device(by serial number or customer/deivce address).
And many many more features will come over time.


### Introduction

#### Manufacturer 

This is a producer for each model of multifunction devices. 

#### Model

Each manufacturer has it's own list of models.
Models are distinguished by such elements as printingSpeed, printingFormat or list of consumables that fit only for given models.

#### Device

This is an individual count for the model. 
Each device has it's own serialNumber(and it's unique for all), address, contract, customer and list of counters.

#### Counter

Each of every device is managed by reading and writing meters read by a technician on that device. 
The counters are used later for monthly billing. 
Measurements include data such as a mono counter, colour counter and total counter.

#### Contract

Each contract is assigned to one single device and has a list of settlements for its device, which are billed and stored in the system. 
Each contract has a unique contract number, contract start date, contract end date, device leasing price and individual print prices for mono and color depending on the signed contract.

#### Copier Settlement

Copier settlements are billed monthly - at the beginning of each month, and contains the meters read for a given device for the entire previous month. 
Each settlement is assigned to the contract that is attached to the selected device. 
Accounting for devices consists in reading all mono and color counters read by the technician for a given month and recalculation of all fees that must be paid by the customer in connection with the use of the device.
Each settlement set starting counter as closing from previous settlement.

#### Service Order

The application allows customers to place various types of orders, such as: repair, consumable delivery or technival review. 
Each order has its own status which changes with the progress and stage of the order. 
Employees have the option of sending articles and materials to the customer as well as sending a technician responsible for the repair of the device. 
Each order has a unique number consisting of: "year / month / generated number counted from 1".

### Technology

Used: Java 11, Spring, HTML5, CSS3, Bootstrap, JavaScript, SQL(mySQL), Hibernate, JUnit, Mockito, Maven, Docker.

### Requirement

The code is tested on Ubuntu and macOS. Java>=11 and Maven are required to run the code.

### To Do
- [ ] Create PDF settlement file for each contract
- [ ] TBA


------------
At this moment we can add new manufacturers and models and edit them.
This section will be updated with the application growth.

------------
