# Software Requirements Specification
## For LocalHarvest Hub

Version 0.1  
Prepared by Alice Beback & Bobby M  
CSC340  
September 1, 2025 

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Introduction](#1-introduction)
  * 1.1 [Document Purpose](#11-document-purpose)
  * 1.2 [Product Scope](#12-product-scope)
  * 1.3 [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
  * 1.4 [References](#14-references)
  * 1.5 [Document Overview](#15-document-overview)
* 2 [Product Overview](#2-product-overview)
  * 2.1 [Product Functions](#21-product-functions)
  * 2.2 [Product Constraints](#22-product-constraints)
  * 2.3 [User Characteristics](#23-user-characteristics)
  * 2.4 [Assumptions and Dependencies](#24-assumptions-and-dependencies)
* 3 [Requirements](#3-requirements)
  * 3.1 [Functional Requirements](#31-functional-requirements)
    * 3.1.1 [User Interfaces](#311-user-interfaces)
    * 3.1.2 [Hardware Interfaces](#312-hardware-interfaces)
    * 3.1.3 [Software Interfaces](#313-software-interfaces)
  * 3.2 [Non-Functional Requirements](#32-non-functional-requirements)
    * 3.2.1 [Performance](#321-performance)
    * 3.2.2 [Security](#322-security)
    * 3.2.3 [Reliability](#323-reliability)
    * 3.2.4 [Availability](#324-availability)
    * 3.2.5 [Compliance](#325-compliance)
    * 3.2.6 [Cost](#326-cost)
    * 3.2.7 [Deadline](#327-deadline)


## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|  Ali | 9/1     | Initial SRS         |    1.0    |
|      |         |                     |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
The purpose of this Software Requirements Document (SRD) is to describe the client-view and developer-view requirements for the LocalHarvest Hub application.  
Client-oriented requirements describe the system from the client’s perspective.  These requirements include a description of the different types of users served by the system.  
Developer-oriented requirements describe the system from a software developer’s perspective.  These requirements include a detailed description of functional, data, performance, and other important requirements.

### 1.2 Product Scope
The purpose of the LocalHarvest Hub system is to connect consumers with fresh, local produce and to create a convenient and easy-to-use application for farmers to reach their customer base and manage their stock.
The system is a web-based application to simplify its subscrption and produce management functions. We will have a server supporting farmers of different produce. 
Above all, we hope to provide a comfortable user experience along with the best offerings available.

### 1.3 Definitions, Acronyms and Abbreviations
| Reference  | Definition                                                                                                                                                                         |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Java       | A programming language originally developed by James Gosling at Sun Microsystems. We will be using this language to build the backend service for LocalHarvest Hub                 |
| Postgresql | Open-source relational database management system.                                                                                                                                 |
| SpringBoot | An open-source Java-based framework used to create a micro Service. This will be used to create and run our application.                                                           |
| Spring MVC | Model-View-Controller. This is the architectural pattern that will be used to implement our system.                                                                                |
| Spring Web | Will be used to build our web application by using Spring MVC. This is one of the dependencies of our system.                                                                      |
| API        | Application Programming Interface. This will be used to interface the backend and the fronted of our application.                                                                  |
| HTML       | Hypertext Markup Language. This is the code that will be used to structure and design the web application and its content.                                                         |
| CSS        | Cascading Style Sheets. Will be used to add styles and appearance to the web app.                                                                                                  |
| JavaScript | An object-oriented computer programming language commonly used to create interactive effects within web browsers.Will be used in conjuction with HTML and CSS to make the web app. |
| VS Code    | An integrated development environment (IDE) for Java. This is where our system will be created.                                                                                    |
|            |                                                                                                                                                                                    |

### 1.4 References
https://spring.io/guides

### 1.5 Document Overview
Section 1 is a general introduction to the document, intended for any readers. Section 2 is focused on the product and its features. This section is for customers and business stakeholders. Section 3 specifies the requirements and constraints for the product and development process. This section is intended for all stakeholders, especially the development team.

## 2. Product Overview
LocalHarvest Hub is a web-based platform designed to help eco-conscious consumers discover and subscribe to seasonal produce from nearby farms. Customers can browse farm profiles, view available goods, and leave reviews based on freshness and delivery experience. Farmers manage their offerings, update harvest schedules, and track customer engagement. The system supports multiple user roles including customers, providers, and administrators; each with tailored services to ensure a vibrant, transparent, and community-driven marketplace.

### 2.1 Product Functions
LocalHarvest Hub allows farmers create and customize the produce they offer. They can manage and track their produce box subscriptions in an intuitive way. Customers can look for and subcribe to any produce boxes of their choosing, and easily manage them from their dashboard.

### 2.2 Product Constraints
At this point, the program will only run on a computer with Java jdk 21 installed. The full scope of the project is hopefully realized, however the team has a deadline of about 10 weeks, which could lead to feature cuts. The program would have a challenge scaling, as the current plan is to use a free version of a Postgresql database to store the information.

### 2.3 User Characteristics
Our website application does not expect our users to have any prior knowledge of a computer, apart from using a web browser. As long as users know what produce they are interested in, they should be expert level within several uses of the application.

### 2.4 Assumptions and Dependencies
We will be using Java, with our program being dependent on Spring & SpringBoot, and RestAPI to connect to external APIs and developed with VS Code. The application will also use an external nutrition API that will help customers learn the nutritional value of produce.

## 3. Requirements

### 3.1 Functional Requirements 
- FR0: The system will allow users to create accounts as either a customer or a farmer.
   - Each account shall have a unique identifier assigned at the time of creation. 
- FR1: The system shall allow farmers to create a new produce box by providing details including title, contents, weight, and price.
- FR2: The system shall allow customers to browse through the list of available produce boxes.
   - The list of boxes shall have a search and filter option.
- FR3: The system shall allow customers to subscribe for any produce box of their choosing. They may customize the frequency of deliveries.
   - A customer may unsubscribe at any time if they are not longer interested in the produce.
- FR4: Users will be able to modify their own profiles at any time.
- FR5: The system shall allow customers to rate and review produce boxes based on delivery and freshness.
- FR6: The system shall allow a farmer to respond to a review.

#### 3.1.1 User interfaces
Web pages using HTML, CSS, and JavaScript.

#### 3.1.2 Hardware interfaces
Devices that have web browser capabilities.

#### 3.1.3 Software interfaces
- Java jdk 21
- PostgreSQL 17
- SpringBoot 3.4.5

### 3.2 Non Functional Requirements 

#### 3.2.1 Performance
- NFR0: The LocalHarvest Hub system will consume less than 100 MB of memory
- NFR1: The novice user will be able to add and manage produce subscriptions in less than 5 minutes.
- NFR2: The expert user will be able to add and manage produce subscriptions in less than 1 minute.

#### 3.2.2 Security
- NFR3: The system is going to be available only to authorized users, using their username and password.

#### 3.2.3 Reliability

#### 3.2.4 Availability
- NFR4: LocalHarvest Hub will be available 24/7. Scheduled Maintenance should be initialized during scheduled low activity hours such as midnight to minimize conflict with users using the app.

#### 3.2.5 Compliance

#### 3.2.6 Cost
- NFR5: We expect to spend zero dollars on this project. 

#### 3.2.7 Deadline
- NFR6: The final product must be delivered by December 2025.

