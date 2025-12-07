# User Management Application

A RESTful API built with Spring Boot for managing user data with full CRUD operations. This project demonstrates clean architecture principles with a layered approach (Controller → Service → Repository → Entity).

## Features

- Create, Read, Update, and Delete users
- Input validation with custom error handling
- DTO-based request/response models
- Interactive API documentation with Swagger UI
- MySQL database integration
- Docker and Kubernetes support

## Tech Stack

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Data JPA** - Database operations
- **Spring Web** - REST API endpoints
- **MySQL** - Primary database
- **H2 Database** - In-memory database for development
- **Maven** - Dependency management
- **Swagger/OpenAPI** - API documentation
- **Docker** - Containerization

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (or use H2 for development)
- Docker (optional, for containerized deployment)

## Getting Started

### Local Setup

1. Clone the repository
   ```bash
   git clone <repository-url>
   cd User-Management-Application-main
   ```

2. Configure database in [application.properties](src/main/resources/application.properties)
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/userdb
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. Create the database
   ```sql
   CREATE DATABASE userdb;
   ```

4. Build the project
   ```bash
   mvn clean install
   ```

5. Run the application
   ```bash
   mvn spring-boot:run
   ```

The application will start at `http://localhost:8080`

### Using Docker

1. Build the JAR file
   ```bash
   mvn clean package
   ```

2. Build Docker image
   ```bash
   docker build -t user-management-app .
   ```

3. Run with Docker Compose (recommended)
   ```bash
   docker-compose up
   ```

### Kubernetes Deployment

The project includes Kubernetes manifests in the [k8s](k8s/) folder for deploying to a Kubernetes cluster.

#### Prerequisites

- Kubernetes cluster (Minikube, Docker Desktop, or cloud provider)
- kubectl CLI configured

#### Deploy to Kubernetes

1. Build and push Docker image to a registry
   ```bash
   docker build -t your-registry/user-management-app:latest .
   docker push your-registry/user-management-app:latest
   ```

2. Apply Kubernetes manifests
   ```bash
   kubectl apply -f k8s/
   ```

3. Check deployment status
   ```bash
   kubectl get pods
   kubectl get services
   ```

4. Access the application
   ```bash
   # If using NodePort
   kubectl get svc user-management-service

   # If using Minikube
   minikube service user-management-service
   ```

#### Kubernetes Resources

The k8s folder typically contains:
- **Deployment**: Application deployment configuration
- **Service**: Service to expose the application
- **ConfigMap**: Configuration settings
- **PersistentVolumeClaim**: MySQL data storage
- **MySQL Deployment**: Database deployment

## API Documentation

Once the application is running, access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

### API Endpoints

| Method | Endpoint          | Description       |
|--------|-------------------|-------------------|
| GET    | /api/users        | Get all users     |
| GET    | /api/users/{id}   | Get user by ID    |
| POST   | /api/users        | Create new user   |
| PUT    | /api/users/{id}   | Update user       |
| DELETE | /api/users/{id}   | Delete user       |

## Project Structure

```
src/main/java/org/example/
├── controller/          # REST controllers
├── service/            # Business logic
├── repository/         # Data access layer
├── model/              # Entity classes
├── dto/                # Data Transfer Objects
├── exception/          # Exception handlers
└── UserManagementApplication.java
```

## Development

The project uses a layered architecture:

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Database operations using Spring Data JPA
- **DTO Layer**: Separates internal models from API contracts
