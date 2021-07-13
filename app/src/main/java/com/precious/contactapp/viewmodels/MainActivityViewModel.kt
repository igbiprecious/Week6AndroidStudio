package com.precious.contactapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.precious.contactapp.models.Contact
import com.precious.contactapp.models.ContactDatabase

class MainActivityViewModel:ViewModel() {
    val contactLiveData =  MutableLiveData<List<Contact>>()

    fun getContacts(database: ContactDatabase){
        contactLiveData.postValue(database.contactDao().getAllContacts())
    }

    fun addContact(database: ContactDatabase, contact: Contact){
        database.contactDao().addContact(contact)
        getContacts(database)
    }
}