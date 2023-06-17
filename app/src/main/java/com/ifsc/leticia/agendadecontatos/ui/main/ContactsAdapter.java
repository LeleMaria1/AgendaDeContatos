package com.ifsc.leticia.agendadecontatos.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ifsc.leticia.agendadecontatos.data.Contact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    // interface implementada por ContactsFragment para responder
    // quando o usuário toca em um item na RecylerView
    public interface ContactClickListener {
        void onClick(int contactID);
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactItemView;
        private int contactID;

        private ContactsViewHolder(View itemView) {
            super(itemView);
            contactItemView = itemView.findViewById(android.R.id.text1);
            // anexa receptor a itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(contactID);
                }
            });
        }
        // configura o identificador de linha do banco de dados para o contato
        public void setContactID(int contactID) {
            this.contactID = contactID;
        }
    }

    private List<Contact> mContacts; // Cached copy of contacts
    private final ContactClickListener clickListener;

    // construtor
    public ContactsAdapter(ContactClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infla o layout android.R.layout.simple_list_item_1
        View view = LayoutInflater.from(
                parent.getContext()).inflate(
                android.R.layout.simple_list_item_1,
                parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        if (mContacts != null) {
            Contact current = mContacts.get(position);
            holder.contactItemView.setText(current.getName());
            holder.setContactID(current.getId());
        }
    }

    void setContacts(List<Contact> contacts){
        mContacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mContacts != null)
            return mContacts.size();
        else return 0;
    }
}