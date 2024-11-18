# Spring Boot Blog Application Documentation

## Table of Contents
1. [Overview](#overview)
2. [Technical Stack](#technical-stack)
3. [Project Structure](#project-structure)
4. [Features](#features)
5. [Security](#security)
6. [Database Schema](#database-model)
7. [API Endpoints](#api-endpoints)
8. [Installation & Setup](#installation--setup)
9. [User Roles & Permissions](#user-roles--permissions)

## Overview
This is a Spring Boot-based blog application that allows users to create, read, update, and delete blog posts. The application features user authentication, authorization, and a clean, responsive user interface.

## Technical Stack
- **Backend Framework**: Spring Boot 3.1.6
- **Java Version**: 17
- **Database**: MySQL
- **Template Engine**: Thymeleaf
- **Security**: Spring Security
- **Build Tool**: Maven
- **Frontend**: HTML, CSS (Inline styles)
- **Container**: Docker
- **Database Admin Tool**: PHPMyAdmin
- **Additional Libraries**:
  - Lombok
  - Spring Data JPA
  - Thymeleaf Extras Spring Security 6

## Project Structure

### Configuration
- `ApplicationConfig`: General application configuration including password encoder
- `WebSecurityConfig`: Security configuration with route permissions and login setup
- `SeedData`: Fills database with example data

### Controllers
- `HomeController`: Handles the main page display
- `LoginController`: Manages login functionality
- `PostController`: Handles CRUD operations for blog posts
- `RegisterController`: Manages user registration

### Models
- `Account`: User account information and relationships
- `Authority`: User roles and permissions
- `Post`: Blog post data structure

### Repositories
- `AccountRepository`: JPA repository for user accounts
- `AuthorityRepository`: JPA repository for user roles
- `PostRepository`: JPA repository for blog posts

### Services
- `AccountService`: User account management and security
- `MyUserDetailsService`: Custom user details service for Spring Security
- `PostService`: Blog post management

## Features

### User Management
- User registration
- User authentication
- Role-based authorization
- Password encryption using BCrypt

### Blog Posts
- Create new posts
- View all posts
- Edit existing posts
- Delete posts
- Time tracking (creation and updates)

### Security Features
- Form-based authentication
- URL-based security
- Method-level security
- Role-based access control

## Security

### Security Rules
1. Public Access:
   - Home page
   - Login/Register pages
   - Static resources (CSS, JS, images)
   - Post viewing

2. Authenticated Access:
   - Post creation
   - Post editing/deleting (own posts only)

3. Admin Access:
   - Post editing/deletion

### Authentication
- Custom login page
- Username (email) and password authentication
- Session management

## Database Model

### Account Table
```sql
Fields:
- id (Long)
- email (String)
- password (String)
- firstName (String)
- lastName (String)
- posts (One-to-Many)
- authorities (Many-to-Many)
```

### Post Table
```sql
Fields:
- id (Long)
- title (String)
- body (Text)
- createdAt (LocalDateTime)
- updatedAt (LocalDateTime)
- account_id (Foreign Key)
```

### Authority Table
```sql
Fields:
- name (String)
```

## API Endpoints

### Authentication
- GET `/login` - Login page
- POST `/login` - Process login
- POST `/logout` - Process logout
- GET `/register` - Registration page
- POST `/register` - Process registration

### Posts
- GET `/` - Home page (list all posts)
- GET `/posts/{id}` - View specific post
- GET `/posts/new` - New post form
- POST `/posts/new` - Create new post
- GET `/posts/{id}/edit` - Edit post form
- POST `/posts/{id}` - Update post
- GET `/posts/{id}/delete` - Delete post

## User Interface

### Templates
1. `home.html`: Main page with post listing
2. `login.html`: Login form
3. `register.html`: Registration form
4. `post.html`: Individual post view
5. `post_new.html`: New post creation form
6. `post_edit.html`: Post editing form
7. Error Pages:
   - `403.html`: Access denied
   - `404.html`: Post not found
   - `error.html`: General error

## Installation & Setup

### Prerequisites
- Java 17
- Docker and Docker Compose
- Maven

### Environment Variables
Create a `.env` file with:
```
MYSQL_DATABASE=your_database_name
MYSQL_PASSWORD=your_password
MYSQL_ROOT_PASSWORD=your_root_password
MYSQL_USER=your_username
```

### Docker Setup
1. Run docker-compose:
```bash
docker-compose up -d
```

### Application Setup
1. Clone the repository
2. Configure environment variables
3. Run Maven build:
```bash
mvn clean install
```
4. Start the application:
```bash
mvn spring-boot:run
```

## User Roles & Permissions

### Available Roles
- ROLE_USER: Basic user role
- ROLE_ADMIN: Administrative role

### Permissions
- **Anonymous Users**:
  - View posts
  - Register
  - Login

- **Authenticated Users (ROLE_USER)**:
  - Create new posts
  - Edit own posts
  - Delete own posts
  - View all posts

- **Administrators (ROLE_ADMIN)**:
  - All ROLE_USER permissions
  - Delete any post
  - Edit any post
