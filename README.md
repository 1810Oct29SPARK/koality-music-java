# Koality Music Java (back-end project)

The back-end project that serves as the business logic layer for the Koality Music project.  
*The front-end project is in the same organization called **koality-music-angular***.

## Developers

* Eddy Soma
* Danny Trevino
* Emma Banks
* Ryan Umanzor
* Jeremy Shropshire

## Project Package Structure

* **Bean** - for maintaining domain object classes used in the application.
* **DAO** - for maintaining the data access layer that handles transactions and communication between services and the database.
* **Service** - for maintaining service classes that process and validate raw data passed from servlets.
* **Controller** - for maintaining classes used by the dispatcher servlet to handling specific HTTP requests sent from the browser; all endpoints are RESTful APIs.
* **Aspect** - for implementing AOP for the project; provide logging feature for different layers.
* **Utility** - for maintaining utility classes that holds useful static methods used throughout the app in order to easily transform or generate data.

## Core Features Available through Endpoints

* Users can register new accounts.
* Users can login to and logout from their accounts.
* Users can edit their profile as well as credentials.
* Publishers can submit new tracks with audio files.
* Publishers can create albums composed of published tracks with album artwork.
* Publishers can get their basic statistics
* Publishers can view reviews on their music publications.
* Customers can view published tracks and albums.
* Customers can purchase tracks and albums.
* Customers can get music recommendations.
* Customers can subscribe to publishers.
* Customers can submit and delete reviews on purchased tracks and albums.
* Customers can create and delete playlists.
* Customers can add and remove tracks from playlists.

## Technologies and Frameworks

* Maven
* Spring context
* Spring MVC
* JUnit
* Mockito
* AspectJ
* Oracle database
* Hibernate
* Log4j
* Tomcat 9 server

## Test Coverage

* 100% coverage on data access objects using JUnit and sandbox database
* 50% coverage on service objects using Mockito
* 100% coverage on controller endpoints using Postman

## Build

Run `mvn clean package` to build the project. The build artifact will be stored in the `target/` directory as a .war file.

## Remarks

This project is temporarily finished as of January 17, 2018.