package com.ifsc.leticia.agendadecontatos.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifsc.leticia.agendadecontatos.R;
import com.ifsc.leticia.agendadecontatos.data.Contact;

import static com.ifsc.leticia.agendadecontatos.MainActivity.CONTACT_ID;

public class DetailFragment extends Fragment {

    private int contactID; // ID do contato selecionado
    // TODO componentes TextView
    private DetailViewModel detailViewModel; // ViewModel do fragmento

    // componentes TextView para informações de contato
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // cria o fragmento com o layout do arquivo details_fragment.xml
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        // configura o fragmento para exibir itens de menu
        setHasOptionsMenu(true);
        // obtem as referências dos componentes
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        emailTextView = (TextView) view.findViewById(R.id.emailTextView);
        // acessa a lista de argumentos enviada ao fragmento em busca do ID do contato
        Bundle arguments = getArguments();
        if (arguments != null)
            contactID = arguments.getInt(CONTACT_ID);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // seleciona o menu a ser mostrado no fragmento
        inflater.inflate(R.menu.fragment_details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                editContact();
                return true;
            case R.id.action_delete:
                deleteContact();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // passa o ID do contato para editar no DetailFragmentListener
    private void editContact(){
        //cria um pacote de argumentos
        Bundle arguments = new Bundle();
        // adiciona o ID do contato como argumento a ser passado ao fragmento
        arguments.putInt(CONTACT_ID, contactID);
        //solitica a transição de tela para o fragmento de editar um novo contato
        Navigation.findNavController(getView()).navigate(R.id.action_detailFragment_to_addEditFragment, arguments);
    }

    // exclui um contato
    private void deleteContact() {
        // usa a ViewModel para apagar o contato aberto
        detailViewModel.delete();
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getContactById(contactID).observe(getViewLifecycleOwner(), new Observer<Contact>() {
            @Override
            public void onChanged(@Nullable final Contact contact) {
                // atualiza as informações da tela com os dados do contato lido
                nameTextView.setText(contact.getName());
                phoneTextView.setText(contact.getPhone());
                emailTextView.setText(contact.getEmail());
            }
        });
    }
}