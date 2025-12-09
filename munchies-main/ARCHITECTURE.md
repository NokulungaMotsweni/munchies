# Munchies – Architecture Overview

## 1. Package Structure 
  munchies
  ├── Main.java
  ├── cli/
  │     └── MunchiesCLI.java
  ├── config/
  │     └── DemoDataLoader.java
  ├── model/
  │     ├── MenuItem.java
  │     ├── Restaurant.java
  │     ├── OrderItem.java
  │     ├── Order.java
  │     └── OrderStatus.java
  └── service/
    ├── RestaurantRepository.java
    ├── payment/
    │     └── PaymentStrategy.java
    ├── discount/
    │     └── DiscountStrategy.java
    └── observer/
    └── OrderStatusObserver.java

-------------------------------------------------------------
## 2. Responsibilities

### **Nokulunga**
  - Create base project structure
  - Implement all **model classes**
  - Create **RestaurantRepository** (Repository Pattern)
  - Create **Strategy Interfaces**:
    - `PaymentStrategy`
    - `DiscountStrategy`
  - Create **Observer Interfaces**:
    - `OrderStatusObserver`
  - Create **DemoDataLoader**
  - Create **CLI** with TODOs for Dren.
  - Wire everything in `Main`

### **Dren**
  - Implement the full CLI logic
  - F1 - List restaurants
  - F2/F4 - Browse restaurant menu
  - Order creation flow
  - Integrate observer notifications

### **Ahmed**
  - Implement payment methods (implements `PaymentStrategy`)
  - Implement discount types (implements `DiscountStrategy`)
  - Implement order total and checkout logic

-------------------------------------------------------------
## 3. Design Patterns Used

### **Repository Pattern**
- `RestaurantRepository`
- Stores and retrieves the restaurant objects
- Keeps data access separate from CLI

### **Strategy Pattern**
- `PaymentStrategy`
- `DiscountStrategy`
- Allows for the adding of new payment/discount types without modifying the core logic

### **Observer Pattern**
- `OrderStatusObserver`
- For notifying the CLI(user) when an order status changes
-------------------------------------------------------------
## 4. Application Flow (High Level)

1. `Main` creates `RestaurantRepository`
2. `DemoDataLoader` adds restaurants + menu items
3. `Main` creates `MunchiesCLI` and calls `run()`
4. `MunchiesCLI` acts as a placeholder for Dren to implement:
  - List restaurants (F1)
  - Browse menu (F2/F4)
  - Order creation (later)
  - Payment and discounts (later through strategies)

-------------------------------------------------------------

## 5. What Each Folder Does

- **model** → Data only, no logic
- **service** → Business logic and pattern interfaces
- **cli** → User interface (Dren’s implementation)
- **config** → Startup data (DemoDataLoader)
- **Main** → Composition root (wires everything together)

-------------------------------------------------------------
