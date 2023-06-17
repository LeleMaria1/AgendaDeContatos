package com.ifsc.leticia.agendadecontatos.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ifsc.leticia.agendadecontatos.R;
import static com.ifsc.leticia.agendadecontatos.MainActivity.CONTACT_ID;
import static com.ifsc.leticia.agendadecontatos.MainActivity.NEW_CONTACT;

public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        // TODO recyclerView, adapter, itemdivider

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
    }

}