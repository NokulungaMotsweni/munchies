**Munchies – Food Delivery Application (Java CLI)**
A command-line food delivery application built for the PRA3016-N: Design Patterns in Java module.
The goal is to implement a scalable and flexible system.
It has built via the use of object-Oriented priciples design patterns and clean architecture whilst meeting functional and non-functional requirements.

**Features (Functional Requirements)**
**F1** – Browse restaurants
Users can list all available restaurants.
**F2** – View dishes from a restaurant
Each restaurant offers multiple menu items.
**F3** – Create orders (implemented later)
Users can choose dishes and build an order.
**F4** – Support for dish categories
Veg, Non-Veg, Vegan, etc.
**F5** – Add toppings
Extra sauce, cheese, etc. (Decorator pattern).
**F6** – Multiple payment methods
Credit Card, PayPal, Cash on Delivery (Strategy pattern).
**F7** – Order status updates
Users are notified when order status changes
(Observer pattern).
**F8** – Discount strategies
Percentage discount, flat discount, no discount (Strategy pattern).


**Running the Application**
Requirements
  * Java 17+
  * IntelliJ IDEA recommended
Steps
1. Clone the repository:
    git clone <repo-url>
2. Open the project in IntelliJ.
3. Run:
    munchies/Main.java
4. The CLI menu will appear:
  * List restaurants
  * Browse menus
  * Create orders (later stage)
  * Exit
