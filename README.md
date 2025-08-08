# Secure Cloud File Sharing System

The **Secure Cloud File Sharing System** is a full-stack application built with **Spring Boot REST API**, **PostgreSQL**, HTML, CSS, and JavaScript.  
It allows users to **register, log in, upload, download, and delete files** securely, with all session and file data stored in the PostgreSQL database.

---

## Features

- **User Registration & Login**
- **Session Handling via Database**
- **Upload, View, Download, Delete files**
- **User-Specific Dashboard** – each user can only see their own files
- **Profile Update** – update username directly
- **Password Reset**
- **Responsive & Modern UI**
- **Database-backed storage** for all files and user data

---

## Technologies Used

- **Backend:** Spring Boot 3.5.4, Spring Data JPA, REST API
- **Frontend:** HTML, CSS, JavaScript (Vanilla)
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Other:** Live Server for frontend testing

---

## Project Structure

```
Secure-File-Sharing/
├── src/main/java/com/shubham/Third/Project
│   ├── controller/       # REST Controllers
│   ├── entity/           # JPA Entities
│   ├── repository/       # JPA Repositories
│   ├── service/          # Service Layer
│   └── SecureCloudFileSharingSystem.java  # Main Application
├── src/main/resources/
│   ├── static/           # Frontend files (HTML, CSS, JS)
│   ├── application.properties
├── pom.xml
└── README.md
```

---

## Installation & Setup

### **1. Clone the repository**

```
git clone https://github.com/yourusername/secure-file-sharing.git
cd secure-file-sharing
```

### **2. Configure PostgreSQL**

```
CREATE DATABASE secure_share;
```

Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/secure_share
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### **3. Run the Backend**

```
mvn spring-boot:run
```

### **4. Run the Frontend**

- Open `src/main/resources/static/` in VS Code
- Right-click `register.html` → **Open with Live Server**

---

## Screenshots

---

## API Endpoints

### **User**

- `POST /api/users/register` → Register new user
- `POST /api/users/login` → Login user
- `PUT /api/users/update-name/{id}` → Update username
- `PUT /api/users/reset-password` → Reset password

### **File**

- `POST /api/files/upload` → Upload file
- `GET /api/files/user/{userId}` → List files by user
- `GET /api/files/download/{fileId}` → Download file
- `DELETE /api/files/delete/{fileId}` → Delete file

---

## 👨‍💻 Developer

**Shubham Santosh Masali**  
📧 Email: shubhammasali76@gmail.com  
🌐 GitHub: [Shubham-Masali76](https://github.com/Shubham-Masali76)

---
