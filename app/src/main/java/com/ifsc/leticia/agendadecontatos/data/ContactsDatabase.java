package com.ifsc.leticia.agendadecontatos.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {
    public abstract ContactsDAO contactsDao();

    private static volatile ContactsDatabase INSTANCE;

    static ContactsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ContactsDatabase.class, "contacts_database")
                            // remover essa linha na versão final
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ContactsDAO mDao;

        PopulateDbAsync(ContactsDatabase db) {
            mDao = db.contactsDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Contact word = new Contact("Hello", "", "");
            mDao.insert(word);
            word = new Contact("World", "", "");
            mDao.insert(word);
            return null;
        }
    }
}
