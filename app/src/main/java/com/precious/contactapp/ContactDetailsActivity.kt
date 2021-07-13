package com.precious.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.precious.contactapp.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val strName = intent.getStringExtra("name")
        val strPhone = intent.getStringExtra("phone")
        val strId = intent.getStringExtra("id")

        val strIdName : String = strId+".  "+strName
        binding.nameText.setText(strIdName)
        binding.phoneText.setText(strPhone)

    }
}