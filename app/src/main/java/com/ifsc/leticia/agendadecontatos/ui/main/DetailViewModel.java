package com.ifsc.leticia.agendadecontatos.ui.main;

import androidx.lifecycle.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ifsc.leticia.agendadecontatos.data.Contact;
import com.ifsc.leticia.agendadecontatos.data.ContactsRepository;

public class DetailViewModel extends AndroidViewModel {
    private LiveData<Contact> mContact;
    private ContactsRepository mRepository;

    public DetailViewModel (Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
    }

    public LiveData<Contact> getContactById(int id) {
        mContact = mRepository.getContactById(id);
        return mContact;
    }

    public void delete() { mRepository.delete(mContact.getValue()); }
}