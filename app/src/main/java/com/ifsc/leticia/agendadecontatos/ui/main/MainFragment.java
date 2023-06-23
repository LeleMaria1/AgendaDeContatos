package com.ifsc.leticia.agendadecontatos.ui.main;

import static com.ifsc.leticia.agendadecontatos.MainActivity.CONTACT_ID;
import static com.ifsc.leticia.agendadecontatos.MainActivity.NEW_CONTACT;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ifsc.leticia.agendadecontatos.R;
import com.ifsc.leticia.agendadecontatos.data.Contact;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;

    // adaptor para ser uma usada com a lista de contatos
    ContactsAdapter contactsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        FloatingActionButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(view1 -> {
            onAddContact();
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(
                recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        contactsAdapter = new ContactsAdapter(contactID -> {
            onContactSelected(contactID);
        });
        recyclerView.setAdapter(contactsAdapter);
        return view;
    }
    private void onAddContact() {
        //cria um pacote de argumentos
        Bundle arguments = new Bundle();
        // adiciona o ID do contato como argumento a ser passado ao fragmento
        // como se trate de um novo contato o valor a ser encaminhado será  NEW_CONTACT
        arguments.putInt(CONTACT_ID, NEW_CONTACT);
        //solitica a transição de tela para o fragmento de adicionar um novo contato
        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_addEditFragment, arguments);
    }
    // chamado quando o contato é selecionado
    private void onContactSelected(int contactID){
        //cria um pacote de argumentos
        Bundle arguments = new Bundle();
        // adiciona o contactID informado como argumento a ser passado ao fragmento
        arguments.putInt(CONTACT_ID, contactID);
        //solitica a transição de tela para o fragmento de detalhes do contato
        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_detailFragment, arguments);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
        mainViewModel.getAllContacts().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactsAdapter.setContacts(contacts);
            }
        });
    }

}