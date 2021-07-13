package com.precious.contactapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.precious.contactapp.databinding.ContactItemBinding
import com.precious.contactapp.models.Contact

class ContactAdapter(private val contacts: List<Contact>, val clicker: (Contact)-> Unit)
    :RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
        inner class ContactViewHolder(private val binding: ContactItemBinding)
            : RecyclerView.ViewHolder(binding.root){
                fun bindContact(contact: Contact){
                    binding.apply{
                        var display : String = contact.id.toString() + ". "+ contact.name
                        phoneDisplay.text = contact.phone
                        nameDisplay.text = display
                        root.setOnClickListener { clicker(contact) }
                    }
                }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindContact(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}