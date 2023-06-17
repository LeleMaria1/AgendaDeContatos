package com.ifsc.leticia.agendadecontatos.ui.main;

import androidx.lifecycle.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.ifsc.leticia.agendadecontatos.data.Contact;
import com.ifsc.leticia.agendadecontatos.data.ContactsRepository;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Contact>> mAllContacts;
    private ContactsRepository mRepository;

    public MainViewModel (Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
        mAllContacts = mRepository.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() { return mAllContacts; }
}