package com.ifsc.leticia.agendadecontatos.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ifsc.leticia.agendadecontatos.R;
import com.ifsc.leticia.agendadecontatos.data.Contact;

import static com.ifsc.leticia.agendadecontatos.MainActivity.CONTACT_ID;
import static com.ifsc.leticia.agendadecontatos.MainActivity.NEW_CONTACT;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;

    // adaptor para ser uma usada com a lista de contatos
    ContactsAdapter contactsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // cria uma referência a recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        // Configura arecycler view para exibir os itens na vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        // anexa um ItemDecorator personalizado para desenhar divisórias entre os itens da lista
        recyclerView.addItemDecoration(new ItemDivider(getContext()));
        // cria um adaptador para preencher os dados da lista
        // o adaptar tambem vincula uma ação para quando for clicado sobre um contato
        contactsAdapter = new ContactsAdapter(new ContactsAdapter.ContactClickListener() {
            @Override
            public void onClick(int contactID) {
                // Quando clicado sobre um contato, o método onContactSelected é invocado
                onContactSelected(contactID);
            }
        });
        recyclerView.setAdapter(contactsAdapter); // vincula o adaptador à lista
        // cria uma referencia ao FAB e vincula uma ação
        FloatingActionButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quando for clicado no FAB o método onAddContact será invocado
                onAddContact();
            }
        });
        return view;
    }

    private void onContactSelected(int contactID) {
    }

    private void onAddContact() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        ArrayList<Contact> list = new ArrayList<Contact>();
        list.add(new Contact(1,"Fulano", "1234", "a@a.com"));
        list.add(new Contact(2,"Beltrano", "4567", "b@a.com"));
        list.add(new Contact(3,"Ciclano", "8888", "c@a.com"));
        contactsAdapter.setContacts(list);
    }

}