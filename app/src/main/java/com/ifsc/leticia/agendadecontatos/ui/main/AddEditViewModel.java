package com.ifsc.leticia.agendadecontatos.ui.main;

import androidx.lifecycle.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ifsc.leticia.agendadecontatos.data.Contact;
import com.ifsc.leticia.agendadecontatos.data.ContactsRepository;

public class AddEditViewModel extends AndroidViewModel {
    private LiveData<Contact> contact;
    private ContactsRepository mRepository;

    public AddEditViewModel (Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
    }

    public LiveData<Contact> getContactById(int id) {
        contact = mRepository.getContactById(id);
        return contact;
    }

    public void insert(Contact contact) { mRepository.insert(contact); }

    public void update(Contact contact) { mRepository.update(contact); }
}