# Car Buying App

A Spring Boot application for managing car buying requests and supplier offers with inspection company integration.

## Features

- **Customer Requests**: Create and manage car buying requests with inspection company selection
- **Supplier Offers**: Submit and manage offers from suppliers with automatic inspection requests
- **Inspection Integration**: Pluggable adapter pattern for different inspection companies
- **Validation**: Comprehensive input validation and error handling
- **Pagination**: Support for paginated results on listing endpoints
- **Database Migration**: Flyway integration for schema management

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **H2 Database** (for development)
- **Liquibase** (database migrations)
- **MapStruct** (DTO mapping)
- **JUnit 5** (testing)
- **Maven** (build tool)

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Setup & Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/HabibaHegazy/Car-Buying-Demo-App
   cd Car-Buying-Demo-App
   ```

2. **Build the application**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - API Base URL: `http://localhost:8080/api`
   - H2 Console: `http://localhost:8080/h2-console`
      - JDBC URL: `jdbc:h2:mem:carbuying`
      - Username: `sa`
      - Password: `password`

## API Endpoints

### Customer Requests

- **POST** `/api/v1/customer-requests` - Create a new request
- **GET** `/api/v1/customer-requests` - List all requests (with optional status filter)
- **GET** `/api/v1/customer-requests/{id}` - Get request by ID
- **PUT** `/api/v1/customer-requests/{id}/status` - Update request status

### Supplier Offers

- **POST** `/api/v1/supplier-offers` - Submit a new offer
- **GET** `/api/v1/supplier-offers/by-request/{requestId}` - Get offers for a request
- **GET** `/api/v1/supplier-offers/by-supplier/{supplierId}` - Get offers by supplier
- **GET** `/api/v1/supplier-offers/{id}` - Get offer by ID

## Sample API Usage

### Create a Customer Request

```bash
curl -X POST http://localhost:8080/api/v1/customer-requests \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "description": "Looking for a reliable sedan, 2020 or newer",
    "checkedByCompany": "AUTO_CHECK_CO"
  }'
```

### Submit a Supplier Offer

```bash
curl -X POST http://localhost:8080/api/v1/supplier-offers \
  -H "Content-Type: application/json" \
  -d '{
    "supplierId": 1,
    "requestId": 1,
    "price": 25000.00,
    "carDetails": "2021 Toyota Camry, excellent condition, 15k miles"
  }'
```

## Database Schema

The application uses two main tables:

- **customer_requests**: Stores car buying requests
- **supplier_offers**: Stores supplier offers with foreign key to requests

Database migrations are managed by Flyway and located in `src/main/resources/db/migration/`.

## Testing

Run the test suite:

```bash
mvn test
```

## Configuration

Application configuration is in `src/main/resources/application.yml`. Key configurations:

- **Database**: H2 in-memory database
- **JPA**: Hibernate with SQL logging enabled
- **Liquibase**: Automatic migration on startup
- **Server Port**: 8080

## Architecture

The application follows a layered architecture:

- **Controllers**: REST API endpoints
- **Services**: Business logic
- **Repositories**: Data access layer
- **DTOs**: Data transfer objects
- **Entities**: JPA entities
- **Mappers**: MapStruct mappers for DTO conversion

### Inspection Service

The inspection service uses an adapter pattern to support multiple inspection companies:

- `InspectionAdapter`: Interface for inspection companies
- `AutoCheckAdapter`: Implementation for AUTO_CHECK_CO
- `VehiVerifyAdapter`: Implementation for VEHI_VERIFY_INC
- `InspectionService`: Service that delegates to appropriate adapter

## Performance Considerations

- Database indexes on frequently queried columns
- Pagination support for large datasets
- Lazy loading for entity relationships
- Unique constraints to prevent duplicate offers

## Future Enhancements

- Async processing for inspection requests
- Real HTTP client integration for inspection APIs
- Caching layer for improved performance
- Authentication and authorization
- Notification system for offer status updates