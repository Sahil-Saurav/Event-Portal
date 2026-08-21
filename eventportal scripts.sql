DROP DATABASE IF EXISTS event_portal;
CREATE DATABASE event_portal;
USE event_portal;
-- =============================================================================
-- CAMPUS EVENT MANAGEMENT SYSTEM - DATABASE SCHEMA (MySQL / PostgreSQL compatible)
-- =============================================================================

-- 1. ROLES TABLE
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

-- Pre-seed core roles
INSERT INTO roles (name) VALUES 
('ROLE_STUDENT'),
('ROLE_FACULTY'),
('ROLE_ADMIN');


-- 2. DEPARTMENTS TABLE
CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dept_code VARCHAR(10) NOT NULL UNIQUE,
    dept_name VARCHAR(100) NOT NULL
);

-- Pre-seed common academic departments
INSERT INTO departments (dept_code, dept_name) VALUES 
('CSE', 'Computer Science & Engineering'),
('ECE', 'Electronics & Communication Engineering'),
('ME', 'Mechanical Engineering'),
('EE', 'Electrical Engineering');


-- 3. USERS TABLE
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roll_number VARCHAR(30) UNIQUE,
    department_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT fk_users_department FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL
);

-- Junction table for many-to-many User <-> Role mapping
CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- 4. EVENTS TABLE
CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    category VARCHAR(30) NOT NULL,
    venue VARCHAR(100) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    max_capacity INT NOT NULL,
    registered_count INT DEFAULT 0 NOT NULL,
    approval_status VARCHAR(20) DEFAULT 'PENDING' NOT NULL,
    organizer_id BIGINT NOT NULL,
    department_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    
    CONSTRAINT fk_events_organizer FOREIGN KEY (organizer_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_events_department FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL,
    
    -- Constraint checks for basic integrity
    CONSTRAINT chk_capacity CHECK (max_capacity > 0),
    CONSTRAINT chk_registered_count CHECK (registered_count >= 0 AND registered_count <= max_capacity),
    CONSTRAINT chk_event_times CHECK (end_time > start_time)
);


-- 5. REGISTRATIONS TABLE (Junction Table)
CREATE TABLE registrations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    registration_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status VARCHAR(20) DEFAULT 'CONFIRMED' NOT NULL,
    is_attended BOOLEAN DEFAULT FALSE NOT NULL,
    
    CONSTRAINT fk_registrations_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_registrations_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
    
    -- Composite Unique Constraint: Prevents duplicate bookings
    CONSTRAINT uk_student_event UNIQUE (student_id, event_id)
);

-- =============================================================================
-- INDEXES FOR QUERY OPTIMIZATION
-- =============================================================================

-- Index for searching upcoming events by date range and category
CREATE INDEX idx_events_start_time ON events(start_time);
CREATE INDEX idx_events_category ON events(category);
CREATE INDEX idx_events_approval_status ON events(approval_status);

-- Composite Index for venue double-booking check queries
CREATE INDEX idx_events_venue_time ON events(venue, start_time, end_time);

-- Index for retrieving a user's bookings quickly
CREATE INDEX idx_registrations_student ON registrations(student_id);
CREATE INDEX idx_registrations_event ON registrations(event_id);