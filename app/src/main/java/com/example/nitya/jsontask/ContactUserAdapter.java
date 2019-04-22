package com.example.nitya.jsontask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactUserAdapter extends RecyclerView.Adapter<ContactUserAdapter.ContactViewHolder> {

    private Context context;
    private ArrayList<ContactUser> users=new ArrayList<>();

    ContactUserAdapter(Context context,ArrayList<ContactUser> users){
        this.users=users;
        this.context=context;
    }
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.item_view,viewGroup,false);

        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {

        String name=users.get(i).getName();
        String contact=users.get(i).getContact();

        contactViewHolder.tvName.setText(name);
        contactViewHolder.tvContact.setText(contact);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView tvContact,tvName;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            tvContact=itemView.findViewById(R.id.tvContacts);
            tvName=itemView.findViewById(R.id.tvName);
        }
    }
}
