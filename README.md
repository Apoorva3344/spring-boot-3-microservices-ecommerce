# Teesside University - Computing Masters Project Artifact  
## E-Commerce Microservices Application (Kubernetes Deployment)

---

This document offers **detailed instructions** to set up and run the **Kubernetes-deployed microservices-based e-commerce project** submitted as part of the MSc dissertation artifact by **D3828560**.

---

## 1. System Requirements

- A 64-bit processor and at least **8 GB of RAM**  
- Operating system: **Linux**, **macOS**, or **Windows 10/11**  
- An internet connection to download necessary files

---

## 2. Required Tools to Install

### a. Docker Desktop (with Kubernetes)

- Download Docker Desktop: [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)
- During installation → **Enable Kubernetes** feature in settings
- After installation, verify:

```bash
docker --version
kubectl version --client
```

### b. Kubectl CLI

- Install Kubectl: [https://kubernetes.io/docs/tasks/tools/](https://kubernetes.io/docs/tasks/tools/)
- Verify installation:

```bash
kubectl get nodes
```

---

## 3. Download the Project ZIP Folder

- The ZIP file containing all deployment artifacts and YAML configurations is provided via the Assessments dashboard.

### Steps:

```bash
# Unzip folder to desktop
unzip SpringBoot_Microservices_Ecommerce_Computing_Masters_Project.zip

# Navigate to project folder
cd path/to/SpringBoot_Microservices_Ecommerce_Computing_Masters_Project
```

---

## 4. Project Overview

| Component             | Description                        |
|----------------------|------------------------------------|
| Angular UI           | Frontend (served via Nginx)        |
| Product Service      | Microservice (Spring Boot)         |
| Order Service        | Microservice (Spring Boot)         |
| Inventory Service    | Microservice (Spring Boot)         |
| Notification Service | Microservice (Spring Boot)         |
| API Gateway          | Spring Cloud Gateway + Resilience4j|
| Keycloak             | OAuth2 Authentication Server       |
| Apache Kafka         | Messaging Platform                 |
| Prometheus           | Metrics Collection                 |
| Grafana              | Metrics Visualization              |
| Loki / Tempo         | Logging & Distributed Tracing      |

---

## 5. Docker Hub Repository

- Docker Hub URL: [https://hub.docker.com/repositories/apoorva3344](https://hub.docker.com/repositories/apoorva3344)
- Docker Hub Credentials (for private access only — not required normally):  
  `Username: apoorva3344`  
  `Password: Docker@2025`

> *Note: All Kubernetes YAML files already reference public container images. Manual login is not necessary.*

---

## 6. Deploying to Kubernetes

- All Kubernetes YAML files are located in `/k8s` folder.

### a. Start Kubernetes

1. Open Docker Desktop → **Settings > Kubernetes > Enable Kubernetes**
2. Wait until Kubernetes initializes completely

### b. Apply Kubernetes Configurations

```bash
kubectl apply -f k8s/
```

### c. Check Deployment Status

```bash
kubectl get pods
kubectl get svc
```

---

## 7. Accessing the Application

Port-forward services locally (in separate terminal tabs):

```bash
# Frontend (Angular UI)
kubectl port-forward svc/frontend 4200:80

# API Gateway
kubectl port-forward svc/api-gateway 9000:9000

# Keycloak Admin
kubectl port-forward svc/keycloak 8080:8080

# Grafana Dashboard
kubectl port-forward svc/grafana 3000:3000

# MongoDB Database
kubectl port-forward svc/mongodb 27017:27017

# MySQL Database
kubectl port-forward svc/mysql 3306:3306
```

### Access URLs

| Service        | URL                               |
|----------------|-----------------------------------|
| Frontend (Angular) | http://localhost:4200          |
| API Gateway (Swagger UI) | http://localhost:9000/swagger-ui.html |
| Keycloak Admin Panel | http://localhost:8080       |
| Grafana Dashboard | http://localhost:3000         |

---

## 8. Login Credentials

| Service   | Username | Password |
|-----------|----------|----------|
| Keycloak Admin | admin | admin |

Use these credentials to access secured services and API documentation.

---

## 9. Observability Tools

- **Grafana** → Preconfigured dashboards
- **Prometheus** → Metrics scraping
- **Loki & Tempo** → Logs and distributed tracing for debugging

---

## 10. Kubernetes Debugging Commands

```bash
# Check pod status
kubectl get pods

# View real-time logs
kubectl logs <pod-name>
```

---

## 11. Summary Table

| Component   | Role |
|-------------|------|
| Angular | Frontend UI |
| Spring Boot | Microservices backend |
| Kafka | Event-based messaging |
| Keycloak | Authentication / Authorization |
| Prometheus | Metrics collection |
| Grafana | Metrics visualization |
| Loki | Centralized logging |
| Tempo | Distributed tracing |
| Kubernetes | Container orchestration |
| Docker Hub | Public image repository |

> *Table 5 — Application Infrastructure Components*

---

## 12. Final Notes

- All services are packaged into Docker containers and deployed using Kubernetes manifests.
- No manual setup of Java, Maven, MySQL, or MongoDB is required.

### Tear down the setup:

```bash
kubectl delete -f k8s/
```

> This README ensures that any user can successfully deploy and evaluate the project in a **local Kubernetes environment** using **Docker Hub–hosted containers**.

---

Prepared by: D3828560  
MSc IT Project Management (with Advanced Practice) Dissertation Artifact  
Teesside University*

