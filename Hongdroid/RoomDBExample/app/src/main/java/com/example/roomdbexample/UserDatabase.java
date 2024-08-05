package com.example.roomdbexample;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase { // Roomdatabase 를 상속받는 추상 클래스
    public abstract UserDao userDao();
}
