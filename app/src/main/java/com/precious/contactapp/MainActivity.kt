package com.precious.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.precious.contactapp.adapters.ContactAdapter
import com.precious.contactapp.databinding.ActivityMainBinding
import com.precious.contactapp.models.Contact
import com.precious.contactapp.models.ContactDatabase
import com.precious.contactapp.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: ContactDatabase
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  this code is to instantiate the database
        database = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contacts_database"
        ).allowMainThreadQueries().build()

        //  this code is use to instantiate the ViewModel
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getContacts(database)

        //  to observe live data from viewModel
        viewModel.contactLiveData.observe(this, {contacts ->
            //creating adapter
            contactAdapter = ContactAdapter(contacts){
                val intent = Intent(this@MainActivity, ContactDetailsActivity::class.java)
                intent.run {
                    putExtra("id", it.id.toString())
                    putExtra("name", it.name)
                    putExtra("phone", it.phone)
                }
                startActivity(intent)
            }

            //  to refresh the Recycler view
            binding.contactsRv.apply{
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = contactAdapter
            }
        })

        binding.btnSave.setOnClickListener {
            val name = binding.contactName.text.toString()
            var phone = binding.contactPhone.text.toString()

            if(name.isNullOrEmpty()){
                Toast.makeText(this, "Please Enter Contact Name", Toast.LENGTH_LONG).show()
            }else if(phone.isNullOrEmpty()){
                Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_LONG).show()
            }else{
                saveContact(name, phone)
                binding.contactName.setText("")
                binding.contactPhone.setText("")
                Toast.makeText(this, "New Contact Added Successfully", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun saveContact(name: String, phone: String){
        val contact = Contact(id = 0, name, phone)
        viewModel.addContact(database, contact)

    }
}