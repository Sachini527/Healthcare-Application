# 📋 Doc-Connect Healthcare Application

![Java](https://img.shields.io/badge/Language-Java-orange) 
![License](https://img.shields.io/badge/License-MIT-blue) 
![Contributors](https://img.shields.io/badge/Contributors-Welcome-brightgreen)  

The **Doc-Connect Healthcare Application** is a Java-based system designed to streamline patient management in a healthcare setting. Leveraging core Object-Oriented Programming (OOP) principles, it focuses on doctor availability management, appointment scheduling, and patient interaction.

---

## 🚀 Features
### ✅ **Doctor Availability Management**
- Doctors can specify their availability by date.
- Default availability is set from 5 PM to 10 PM unless otherwise specified.
- Supports dynamic scheduling for varied dates and times.

### ✅ **Appointment Scheduling**
- Two appointment types:
  - **General Appointment**: Directly booked by patients.
  - **Referral Appointment**: Includes referral details from another doctor.
- Appointment slots are dynamically allocated based on availability in 1-hour intervals.

### ✅ **Patient Management**
- Patients can book appointments by selecting a doctor and date.
- The system prevents multiple bookings for the same slot.
- Displays a list of available slots per doctor.

### ✅ **Doctor Information**
- View all registered doctors.
- Doctors can view their scheduled patients for a specific date.

---

## 🛠️ Technologies
- **Programming Language**: Java  
- **IDE**: Eclipse, IntelliJ IDEA, or any preferred Java IDE.  

---

## 📂 Project Structure
### 🖼️ **OOP Classes**
- **Doctor**:
  - Attributes: Name, Specialization, Availability
  - Methods: `addAvailability(date, startTime, endTime)`, `viewBookings(date)`
- **Patient**:
  - Attributes: Name, Contact Information, Bookings
  - Methods: `bookAppointment(doctor, date)`
- **Appointment**:
  - Abstract class with General and Referral subtypes.
- **Main Class**:
  - Handles overall system logic and user interface.

### 🗂️ **Data Structures**
- **HashMap**: To manage doctor availability and appointments.
- **ArrayList**: To store data for doctors, patients, and appointment slots.

### 🔄 **System Logic**
- User menu options:
1. Add Doctor
2. Add Doctor Availability
3. View Doctors
4. Book Appointment
5. View Doctor's Bookings
6. Exit
     
--- 

### 📈 **Future Improvements**
- Data Persistence: Integrate a database for permanent storage.
- Graphical Interface: Upgrade to a GUI for better user interaction.
- Advanced Scheduling: Implement more dynamic slot allocation and customization.
  
---

## 📝 Getting Started

### 📌 Prerequisites
- Install **Java Development Kit (JDK)**.
- Preferred IDE: **Eclipse**, **IntelliJ IDEA**, etc.

### ▶️ Run the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/healthcare-application.git

