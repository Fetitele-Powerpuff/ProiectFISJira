# Cinema City App

Cinema City App is a Java-based desktop application designed to provide users with a seamless movie browsing and ticket purchasing experience. The application features an interactive seat selection system, secure authentication modules for both clients and administrators, and a dedicated admin dashboard allowing cinema staff to manage movie schedules, track ticket sales, and update theater capacities efficiently.

## Repository Link
* **Repository:** [GitHub Link](https://github.com/Fetitele-Powerpuff/ProiectFISJira)

---

## Core Features

### Client Experience (User Module)
* **Movie Catalog Browsing:** Browse through upcoming film screening schedules, genres, and specific runtime details through an intuitive graphical user interface.
* **Interactive Seat Allocation:** Visually select preferred seats inside a real-time responsive theater arrangement layout with dynamic locking mechanics to prevent duplicate booking.
* **Account Registrations:** Secure account setup and custom client profile login interfaces.

### Administrative Management (Staff Module)
* **Schedule Lifecycle Tools:** Full management capabilities for administrators to add, update, or remove movies and screening times dynamically.
* **Sales Optimization Monitoring:** High-level visibility tools to oversee complete tickets sold metrics and monitor current theater capacity allocations.
* **Capacity Management:** Real-time configurations to alter or clear theater seating grids depending on operational parameters.

---

## Tech Stack & Architecture

* **Programming Language:** Java
* **Graphical User Interface (GUI):** JavaFX & Scene Builder (leveraged to implement structured, scannable layout architectures)
* **Database Management:** SQLite (embedded local relational database configuration utilized to persistently store movie metadata, room properties, and client reservation records)[cite: 1]
* **Quality Assurance & Testing:** JUnit / Java Testing Frameworks (extensive automated test coverage across controller operations)[cite: 4]
* **Project Workflow Tracking:** Jira / Git[cite: 1]

---

## Testing Suite & Quality Assurance (QA)

The project places high priority on system architecture stability and regression prevention, incorporating an explicit suite of **Unit Tests** and **Integration Tests** targeting the application's underlying controller logic[cite: 4]. 

The automated test coverage validates standard user journeys along with critical operational edge cases:
* **Authentication Pipelines:** Assures stable login and registration flows for separate Client and Administrator access layers (`AdminLoginControllerTest.java`, `SignupControllerTest.java`)[cite: 4].
* **Admin Option Logic:** Validates data updates, record entry additions, and target resource removal behaviors within administrative workspaces (`AdminOptionsControllerAddTest.java`, `AdminOptionsControllerDeleteTest.java`)[cite: 4].
* **Collision Avoidance (Edge Cases):** Dedicated testing cases engineered to assert that concurrent seat choices or overlapping record ID generations are handled cleanly without database locking or double-allocation errors (`AdminOptionsControllerCollisionsTest.java`, `ClientControllerAddSeatTest.java`)[cite: 4].

### Test Suite Structure (`/src/test/java/`)
#### ├── AdminLoginControllerTest.java 
#### ├── AdminOptionsControllerAddTest.java
#### ├── AdminOptionsControllerCollisionsTest.java
#### ├── AdminOptionsControllerDeleteTest.java
#### ├── AdminOptionsControllerGetIdTest.java
#### ├── AdminOptionsControllerTest.java
#### ├── ClientControllerAddSeatTest.java
#### ├── HelloAdminLoginControllerTest.java
#### ├── HelloControllerLoginClientTest.java
#### ├── HelloControllerTest.java
#### ├── HelloSignupControllerTest.java
#### └── SignupControllerTest.java

---

## Installation & Environment Setup

### Prerequisites
* Java Development Kit (JDK) 11 or higher
* JavaFX SDK (if not packaged inside your build runner)
* Target Java IDE (IntelliJ IDEA, Eclipse, or NetBeans)

### 1. Clone the Repository
```bash
git clone [https://github.com/Fetitele-Powerpuff/ProiectFISJira.git](https://github.com/Fetitele-Powerpuff/ProiectFISJira.git)
cd ProiectFISJira
# Example if utilizing a Maven layout configuration
mvn test
