# 🏥 Hospital Management System

A production-ready Hospital Management System built with Spring Boot and PostgreSQL. Designed for real-world healthcare
operations with a focus on data integrity, audit trails, and scalability.

[![Repository Size](https://img.shields.io/github/repo-size/Saptarshi088/hospital_management_system_spring)](https://github.com/Saptarshi088/hospital_management_system_spring)
[![Languages](https://img.shields.io/github/languages/top/Saptarshi088/hospital_management_system_spring)](https://github.com/Saptarshi088/hospital_management_system_spring)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Issues](https://img.shields.io/github/issues/Saptarshi088/hospital_management_system_spring)](https://github.com/Saptarshi088/hospital_management_system_spring/issues)

---

## ✨ Features

- **Patient Management** – Complete patient registration, medical history, and record tracking
- **Appointment System** – Scheduling with calendar integration and automated reminders
- **Staff Management** – Role-based access control (RBAC) for doctors, nurses, and admin staff
- **Billing & Invoicing** – Automated invoice generation with payment tracking
- **Audit Logging** – Database-level audit trails using PostgreSQL triggers
- **RESTful API** – Clean API design for frontend and third-party integrations
- **Docker Ready** – Containerized deployment with Docker Compose

---

## 🚀 Quick Start

### Prerequisites

- **Java 17+** (JDK 17 or higher)
- **Maven 3.6+** or Gradle 6+
- **PostgreSQL 12+** (PostgreSQL 13+ recommended)
- **Docker** (optional, for containerized setup)

### 1. Clone the Repository

```bash
git clone https://github.com/Saptarshi088/hospital_management_system_spring.git
cd hospital_management_system_spring
```

### 2. Set Up the Database

Create a PostgreSQL database and user:

```sql
-- Connect as postgres superuser
CREATE USER hms_user WITH PASSWORD 'your_secure_password';
CREATE DATABASE hms_db OWNER hms_user;
GRANT ALL PRIVILEGES ON DATABASE hms_db TO hms_user;
```

Initialize the schema:

```bash
psql -h localhost -U hms_user -d hms_db -f db/schema.sql
psql -h localhost -U hms_user -d hms_db -f db/functions.sql
```

### 3. Configure the Application

Copy and edit the configuration file:

```bash
cp src/main/resources/application.example.yml src/main/resources/application.yml
```

Update `application.yml` with your database credentials:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hms_db
    username: hms_user
    password: your_secure_password
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: false

server:
  port: 8080
```

### 4. Run the Application

**Using Maven:**

```bash
./mvnw clean package
./mvnw spring-boot:run
```

**Using Gradle:**

```bash
./gradlew clean build
./gradlew bootRun
```

**Or run the JAR directly:**

```bash
java -jar target/hms-*.jar
```

### 5. Access the Application

- **API Base URL**: `http://localhost:8080/api`
- **Swagger UI** (if enabled): `http://localhost:8080/swagger-ui.html`

---

## 🐳 Docker Setup (Recommended)

Run the entire stack with Docker Compose:

```bash
docker-compose up --build
```

This will start:

- PostgreSQL database on port `5432`
- Spring Boot application on port `8080`

**docker-compose.yml:**

```yaml
version: '3.8'
services:
  db:
    image: postgres:13
    environment:
      POSTGRES_DB: hms_db
      POSTGRES_USER: hms_user
      POSTGRES_PASSWORD: your_secure_password
    volumes:
      - db-data:/var/lib/postgresql/data
    ports:
      - "5432:5432"

  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/hms_db
      SPRING_DATASOURCE_USERNAME: hms_user
      SPRING_DATASOURCE_PASSWORD: your_secure_password
    depends_on:
      - db

volumes:
  db-data:
```

---

## 🏗️ Architecture

### Tech Stack

| Component         | Technology                             |
|-------------------|----------------------------------------|
| Backend Framework | Spring Boot (Java 17+)                 |
| Database          | PostgreSQL 12+                         |
| Database Logic    | PL/pgSQL (stored procedures, triggers) |
| Build Tool        | Maven / Gradle                         |
| Containerization  | Docker & Docker Compose                |
| Testing           | JUnit, Spring Test                     |
| API Documentation | Swagger / OpenAPI                      |

### Project Structure

```
hospital_management_system_spring/
├── src/
│   ├── main/
│   │   ├── java/com/yourorg/hms/
│   │   │   ├── config/         # Security & app configuration
│   │   │   ├── controller/     # REST API endpoints
│   │   │   ├── service/        # Business logic
│   │   │   ├── repository/     # Data access layer
│   │   │   ├── dto/            # Data transfer objects
│   │   │   └── util/           # Utility classes
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── db/
│   │       │   ├── migration/  # Flyway migrations
│   │       │   └── functions/  # PL/pgSQL scripts
│   └── test/                   # Unit & integration tests
├── db/                         # Database initialization scripts
├── docker-compose.yml
├── Dockerfile
└── pom.xml / build.gradle
```

---

## 🗄️ Database & Migrations

This project uses **Flyway** for version-controlled database migrations.

### Configuration

Add to `application.yml`:

```yaml
spring:
  flyway:
    enabled: true
    locations: classpath:db/migration
```

### Running Migrations

```bash
./mvnw flyway:migrate
```

### Example Migration

**Idempotent Function:**

```sql
CREATE OR REPLACE FUNCTION public.log_patient_change()
RETURNS trigger AS $$
BEGIN
  INSERT INTO audit.patient_changes(patient_id, changed_at, changed_by, changes)
  VALUES (NEW.id, now(), current_user, row_to_json(NEW));
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;
```

**Trigger:**

```sql
CREATE TRIGGER trg_patient_audit
AFTER INSERT OR UPDATE ON public.patient
FOR EACH ROW EXECUTE FUNCTION public.log_patient_change();
```

---

## 🧪 Testing

### Run Unit Tests

```bash
./mvnw test
# or
./gradlew test
```

### Integration Tests

Use **Testcontainers** for integration testing with a real PostgreSQL instance:

```java
@Testcontainers
@SpringBootTest
public class PatientServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
        .withDatabaseName("test_db")
        .withUsername("test_user")
        .withPassword("test_pass");

    // Your tests here
}
```

---

## 🔒 Security Best Practices

- ✅ **Never commit credentials** – Use environment variables or secret managers
- ✅ **Use HTTPS in production** with proper SSL/TLS certificates
- ✅ **Sanitize inputs** – Always use prepared statements and parameterized queries
- ✅ **Principle of least privilege** – Grant minimal database permissions
- ✅ **Enable CORS properly** – Configure only necessary origins
- ✅ **Implement rate limiting** – Protect against abuse and DDoS
- ✅ **Regular security audits** – Keep dependencies updated

---

## 🚢 Deployment

### Production Checklist

1. **Externalize configuration** using environment variables or Kubernetes Secrets
2. **Set up automated backups** for both database data and schema
3. **Configure monitoring** (Prometheus, Grafana, CloudWatch, etc.)
4. **Enable SSL/TLS** for all connections
5. **Set up CI/CD pipelines** (GitHub Actions, GitLab CI, Jenkins)
6. **Use managed PostgreSQL** (AWS RDS, Google Cloud SQL, Azure Database)

### Example Kubernetes Deployment

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: hms-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: hms
  template:
    metadata:
      labels:
        app: hms
    spec:
      containers:
      - name: hms
        image: saptarshi088/hms:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_DATASOURCE_URL
          valueFrom:
            secretKeyRef:
              name: hms-secrets
              key: db-url
```

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork** the repository
2. **Create a feature branch**: `git checkout -b feat/awesome-feature`
3. **Write tests** for your changes
4. **Commit** using conventional commits: `feat: add patient search`
5. **Push** to your fork: `git push origin feat/awesome-feature`
6. **Open a Pull Request** with a clear description



---


## 🙏 Acknowledgements

Built with ❤️ using:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
- Inspired by best practices in healthcare software development

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Flyway Migrations Guide](https://flywaydb.org/documentation/)
- [Docker Best Practices](https://docs.docker.com/develop/dev-best-practices/)

---

**Made with Spring and PostgreSQL
** | [Report Bug](https://github.com/Saptarshi088/hospital_management_system_spring/issues) | [Request Feature](https://github.com/Saptarshi088/hospital_management_system_spring/issues)