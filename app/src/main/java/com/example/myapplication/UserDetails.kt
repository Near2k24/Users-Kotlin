package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class UserDetails : AppCompatActivity() {
    private var id: Int = 0

    private lateinit var nameTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var streetTextView: TextView
    private lateinit var suiteTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var zipcodeTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var websiteTextView: TextView
    private lateinit var companyNameTextView: TextView
    private lateinit var companyCatchPhraseTextView: TextView
    private lateinit var companyBsTextView: TextView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_datails)

        id = intent.getIntExtra("id", 0)

        nameTextView = findViewById(R.id.nameTextView)
        usernameTextView = findViewById(R.id.usernameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        streetTextView = findViewById(R.id.streetTextView)
        suiteTextView = findViewById(R.id.suiteTextView)
        cityTextView = findViewById(R.id.cityTextView)
        zipcodeTextView = findViewById(R.id.zipcodeTextView)
        phoneTextView = findViewById(R.id.phoneTextView)
        websiteTextView = findViewById(R.id.websiteTextView)
        companyNameTextView = findViewById(R.id.companyNameTextView)
        companyCatchPhraseTextView = findViewById(R.id.companyCatchPhraseTextView)
        companyBsTextView = findViewById(R.id.companyBsTextView)
        toolbar = findViewById(R.id.toolbar)

        fetchUserDetails(id)
    }

    private fun fetchUserDetails(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("UserDetails", "Fetching user details for ID: $id")
            val url = URL("https://jsonplaceholder.typicode.com/users/$id")
            val jsonString = url.readText()

            val userDetailsJson = JSONObject(jsonString)

            val name = userDetailsJson.getString("name")
            val username = userDetailsJson.getString("username")
            val email = userDetailsJson.getString("email")
            val address = userDetailsJson.getJSONObject("address")
            val street = address.getString("street")
            val suite = address.getString("suite")
            val city = address.getString("city")
            val zipcode = address.getString("zipcode")
            val phone = userDetailsJson.getString("phone")
            val website = userDetailsJson.getString("website")
            val company = userDetailsJson.getJSONObject("company")
            val companyName = company.getString("name")
            val companyCatchPhrase = company.getString("catchPhrase")
            val companyBs = company.getString("bs")

            launch(Dispatchers.Main) {
                nameTextView.text = name
                usernameTextView.text = username
                emailTextView.text = email
                streetTextView.text = "Street: $street"
                suiteTextView.text = "Suite: $suite"
                cityTextView.text = "City: $city"
                zipcodeTextView.text = "Zip: $zipcode"
                phoneTextView.text = "Phone: $phone"
                websiteTextView.text = "Website: $website"
                companyNameTextView.text = "Company: $companyName\nCatch Phrase: $companyCatchPhrase\nBS: $companyBs"
                companyCatchPhraseTextView.text = "Catch Phrase: $companyCatchPhrase"
                companyBsTextView.text = "BS: $companyBs"
                toolbar.title = name
            }
        }
    }
}
