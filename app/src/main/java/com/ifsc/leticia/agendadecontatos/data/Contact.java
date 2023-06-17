package com.ifsc.leticia.agendadecontatos.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

    @Entity(tableName = "contacts_table")
    public class Contact {

        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "id")
        private int id;

        @NonNull
        @ColumnInfo(name = "name")
        private String name;

        @ColumnInfo(name = "phone")
        private String phone;

        @ColumnInfo(name = "email")
        private String email;

        public Contact(int id, String name, String phone, String email) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        @Ignore
        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        public int getId() {return this.id;}

        public String getName(){return this.name;}

        public String getPhone() {return this.phone;}

        public String getEmail() {return this.email;}
    }

