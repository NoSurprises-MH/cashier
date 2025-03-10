# Cashier API

The **Cashier API** is a Spring Boot application that provides functionality for managing an online checkout system. It supports calculating totals, applying discounts, enforcing purchase limits, and generating receipts for a list of products in a shopping cart.

---

## Features

- **Checkout Functionality**: Process a cart with product orders and calculate the final receipt, including discounts and special rules.
- **Dynamic Receipt Generation**: Converts cart items into detailed receipt responses.
- **Discounts & Rules**: Supports:
    - Free items based on specific product purchases.
    - Quantity-based discounts.
    - Enforcement of product purchase limits.
- **JSON-Based API**: Interaction with the application is done through RESTful endpoints with JSON input and output.
- **Swagger Integration**: Automatically generated API documentation (via OpenAPI 3.0).

---

## Requirements

- **Java**: 17 or later.
- **Spring Boot**: 3.x or compatible with Jakarta EE.
- **Gradle**: Build and dependency management system.

---

## How to Run the Application

1. Clone the project from the repository:
```shell script
git clone <repository-url>
```

2. Navigate to the project directory:
```shell script
cd cashier-api
```

3. Build the application:
```shell script
./gradlew build
```

4. Run the application:
```shell script
./gradlew bootRun
```

5. The application will start on `http://localhost:8080`.

---

## API Endpoints

### **Checkout API**

**Endpoint**: `POST /`

**Description**: Submit a shopping cart to calculate totals and generate a receipt.

**Request Body Example**:
```json
{
  "cartItems": [
    { "quantity": 2, "product": "ARTICLE_7" },
    { "quantity": 1, "product": "ARTICLE_6" }
  ]
}
```

**Response Example**:
```json
{
  "receiptItems": [
    {
      "quantity": 2,
      "product": "ARTICLE_7",
      "sum": 140.0,
      "comment": null
    },
    {
      "quantity": 6,
      "product": "ARTICLE_5",
      "sum": 0.0,
      "comment": "6 free Article 5 when purchasing Article 7"
    }
  ],
  "total": 140.0
}
```

- **Status Codes**:
    - `200 OK` - If the cart is processed successfully.
    - `400 Bad Request` - If there are validation errors in the input.

---

## Project Structure

The application follows a clean and organized structure with the following main components:

- **`CheckoutController`**: Manages incoming API requests, converts them to the appropriate model format, forwards requests to the `CheckoutService`, and prepares the response.
- **`CheckoutService`**: Business logic layer responsible for handling the checkout process, applying discounts, and generating receipts.
- **Domain Models**:
    - `CheckoutCart`: Represents the shopping cart for the checkout process.
    - `Receipt`: Represents the final receipt generated after applying all rules and discounts.

---

## How to Run Tests

Unit tests are written to verify the functionality of key processes (e.g., handling discounts, errors, and quantity constraints). To run the tests:

```shell script
./gradlew test
```

The test results will be available in the terminal and as a report in the `build/reports/tests` directory.

---

## Swagger Documentation (API Docs)

The Cashier API integrates Swagger for API documentation.

- **API Docs**: Open your browser and navigate to:
    - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    - OpenAPI JSON Spec: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Swagger UI provides an interactive interface to test the API endpoints directly.

---

## Deployment

1. Package the application:
```shell script
./gradlew bootJar
```

2. The JAR file will be created in the `build/libs` directory.

3. Run the application using:
```shell script
java -jar build/libs/cashier-api-1.0.0.jar
```

You can deploy the JAR file to any server or cloud platform supporting Java Runtime Environment.

---

## Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests for any new features, bug fixes, or improvements.

---

## License

This project is licensed under the **MIT License**.

---

## Contact

For any questions or support, please feel free to contact the project maintainers.

---

### Happy Coding! 🛒