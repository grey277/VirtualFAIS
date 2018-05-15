# VirtualFAIS
Repository contains application build for team programing. Android application that allows to calculate shortest distance from your position to selected room in FAIS department of Jagiellonian University 

## How to use database (Room library)

More info: https://developer.android.com/training/data-storage/room/

Example:
```java
AppDatabase appDatabase = android.arch.persistence.room.Room.databaseBuilder(
        getApplicationContext(), AppDatabase.class, "fileName.sqlite").build();

EmployeeDao employeeDao = appDatabase.employeeDao();
RoomDao roomDao = appDatabase.roomDao();
```
