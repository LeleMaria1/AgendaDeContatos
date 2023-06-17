package com.ifsc.leticia.agendadecontatos.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactsRepository {
    private ContactsDAO mContactsDao;
    private LiveData<List<Contact>> mAllContacts; // create a cached data


    public ContactsRepository(Application application) {
        ContactsDatabase db = ContactsDatabase.getDatabase(application);
        mContactsDao = db.contactsDao();
        mAllContacts = mContactsDao.getAllContacts();
    }

    /***********************************************
     GET ALL CONTACTS
     ***********************************************/
    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    /***********************************************
     GET CONTACT BY ID
     ***********************************************/
    public LiveData<Contact> getContactById(int id) {
        return mContactsDao.getContactById(id);
    }

    /***********************************************
     INSERT CONTACT TASKS
     ***********************************************/
    public void insert (Contact contact) {
        new insertAsyncTask(mContactsDao).execute(contact);
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactsDAO mAsyncTaskDao;

        insertAsyncTask(ContactsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /***********************************************
     UPDATE CONTACT TASKS
     ***********************************************/
    public void update (Contact contact) {
        new updateAsyncTask(mContactsDao).execute(contact);
    }

    private static class updateAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactsDAO mAsyncTaskDao;

        updateAsyncTask(ContactsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    /***********************************************
     DELETE CONTACT TASKS
     ***********************************************/
    public void delete (Contact contact) {
        new deleteAsyncTask(mContactsDao).execute(contact);
    }

    private static class deleteAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactsDAO mAsyncTaskDao;

        deleteAsyncTask(ContactsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
