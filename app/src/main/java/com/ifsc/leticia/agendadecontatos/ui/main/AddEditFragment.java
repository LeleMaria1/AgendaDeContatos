package com.ifsc.leticia.agendadecontatos.ui.main;

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
import com.ifsc.leticia.agendadecontatos.R;

import static com.ifsc.leticia.agendadecontatos.MainActivity.CONTACT_ID;
import static com.ifsc.leticia.agendadecontatos.MainActivity.NEW_CONTACT;

public class AddEditFragment extends Fragment {
    private int contactID; // ID do contato selecionado
    private boolean addingNewContact; // flag para sinalizar adição ou edição

    // componente FAB para salvar o contato
    private FloatingActionButton saveContactFAB;

    // ViewModel do fragmento
    private AddEditViewModel addEditViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // cria o fragmento com o layout do arquivo add_edit_fragment.xml
        View view = inflater.inflate(R.layout.fragment_add_edit, container, false);

        // TODO componentes TextInputLayout

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
        // TODO ler dados inseridos
        // TODO salvar dados
        // solicita o retorno a tela anterior
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addEditViewModel = new ViewModelProvider(this).get(AddEditViewModel.class);
        // TODO: Use the ViewModel
    }
}