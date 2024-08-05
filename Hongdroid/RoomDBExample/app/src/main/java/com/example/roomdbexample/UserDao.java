package com.example.roomdbexample;

//Data Access Object

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert // 삽입
    void setInsertUser(User user);

    @Update // 수정
    void setUpdateUSer(User user);

    @Delete // 삭제
    void setDeleteUser(User user);

    @Query("SELECT * FROM User") // 명령문 쿼리 : 데이터 베이스에 요청하는 명령문 (조회)
    List<User> getUserAll();
}
