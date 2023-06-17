package com.ifsc.leticia.agendadecontatos.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactsDAO {
    @Query("SELECT * from contacts_table ORDER BY name ASC")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * from contacts_table WHERE id=:id")
    LiveData<Contact> getContactById(int id);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contacts_table")
    void deleteAll();
}
