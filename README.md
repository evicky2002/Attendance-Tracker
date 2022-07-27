
# Attendance Tracker

This is an Android Application tailored for the students of **Anna University** keep track of their Attendance throughout the semester.


### Architecture and patterns used

* **MVVM Architecture** - All of the data manipulation is purely done with data models.
* Also incorporated the **"one activity mulitiple fragments"** as much as possible throught out the project.

### Authentication

* I have used the **Firebase Google-One Tap Sign-in** to make the onboarding seamless.

### Database

* This system is powered by the **Firestore database** which is a **NOSQL** database.
* **CRUD** operations are performed and UI is updated regardingly.

### Networking 

* All the database queries are done in a single network service class - implemented **callbacks with interfaces**.

### Design

* The design is entirely made using the native android library, no third party library or whatsoever.
* It even supports **cutouts** for the devices which has it's **SDK version 28(Android 9 or Pie)** or above.

