package com.ifsc.leticia.agendadecontatos.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.ifsc.leticia.agendadecontatos.R;
import com.ifsc.leticia.agendadecontatos.data.Contact;

import static com.ifsc.leticia.agendadecontatos.MainActivity.CONTACT_ID;
import static com.ifsc.leticia.agendadecontatos.MainActivity.NEW_CONTACT;

public class AddEditFragment extends Fragment {
    private int contactID; // ID do contato selecionado
    private boolean addingNewContact; // flag para sinalizar adição ou edição

    // componente FAB para salvar o contato
    private FloatingActionButton saveContactFAB;

    // ViewModel do fragmento
    private AddEditViewModel addEditViewModel;

    // componentes EditText para informações de contato
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout emailTextInputLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // cria o fragmento com o layout do arquivo add_edit_fragment.xml
        View view = inflater.inflate(R.layout.fragment_add_edit, container, false);
        // obtem as referências dos componentes
        nameTextInputLayout = view.findViewById(R.id.nameTextInputLayout);
        phoneTextInputLayout = view.findViewById(R.id.phoneTextInputLayout);
        emailTextInputLayout = view.findViewById(R.id.emailTextInputLayout);
        // configura o receptor de eventos do FAB
        saveContactFAB = view.findViewById(R.id.saveButton);
        saveContactFAB.setOnClickListener(saveContactButtonClicked);
        // acessa a lista de argumentos enviada ao fragmento em busca do ID do contato
        Bundle arguments = getArguments();
        contactID = arguments.getInt(CONTACT_ID);
        // verifica se o fragmento deve criar um novo contato ou editar um já existente
        if (contactID == NEW_CONTACT) {
            // usa a flag para sinalizar que é um novo contato
            addingNewContact = true;
        } else {
            // usa a flag para sinalizar que é uma edição
            addingNewContact = false;
        }
        return view;
    }

    private final View.OnClickListener saveContactButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // oculta o teclado virtual
            ((InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getView().getWindowToken(), 0);
            saveContact();
        }
    };

    // salva informações de um contato no banco de dados
    private void saveContact() {
        // faz a leitura dos dados inseridos
        String name = nameTextInputLayout.getEditText().getText().toString();
        String phone = phoneTextInputLayout.getEditText().getText().toString();
        String email = emailTextInputLayout.getEditText().getText().toString();
        // caso for adição de uma novo contato
        if (addingNewContact) {
            // cria um contato sem um ID pois ele será adicionado automaticamente no banco de dados
            Contact contact = new Contact(name, phone, email);
            // solicita a ViewModel a inserção do novo contato
            addEditViewModel.insert(contact);
        } else {
            // cria um contato com o mesmo ID e atualiza o seus valores
            Contact contact = new Contact(contactID, name, phone, email);
            // solicita a ViewModel a atualização do contato
            addEditViewModel.update(contact);
        }
        //Solicita a naveção voltar uma tela
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // cria uma ViewModel para o fragmento
        addEditViewModel = new ViewModelProvider(this).get(AddEditViewModel.class);
        // se estiver editando um contato existente atualiza a tela com os valores
        if (addingNewContact == false) {
            // usa a ViewModel para solicitar a busca pelo novo contato
            addEditViewModel.getContactById(contactID).observe(getViewLifecycleOwner(), new Observer<Contact>() {
                @Override
                public void onChanged(@Nullable final Contact contact) {
                    // atualiza as informações da tela com os dados do contato lido
                    nameTextInputLayout.getEditText().setText(contact.getName());
                    phoneTextInputLayout.getEditText().setText(contact.getPhone());
                    emailTextInputLayout.getEditText().setText(contact.getEmail());
                }
            });
        }
    }
}