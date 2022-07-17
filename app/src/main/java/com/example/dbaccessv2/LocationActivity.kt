package com.example.dbaccessv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val db = DataBaseHandler(this)

        spn_Country.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_activated_1, db.getCountryUser().sortedWith(compareBy{it.country}))

        spn_Country.onItemSelectedListener = object :

        AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val countryValue = spn_Country.selectedItem.toString()
                val dataCountry = db.getCountryId(countryValue).toString()
                val cidValue1 = dataCountry.lastIndexOf("[").plus(1).let {dataCountry.substring(it, dataCountry.length)}
                val cidValue2 = cidValue1.lastIndexOf("]").plus(0).let {cidValue1.substring(0, it)}
                val dataState = db.getStateUser(cidValue2.toInt())
                val adapterState = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_activated_1, dataState.sortedWith(compareBy{it.state}))
                spn_State.adapter = adapterState
            }
        }

        spn_State.onItemSelectedListener = object :

        AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val stateValue = spn_State.selectedItem.toString()
                val dataState = db.getStateId(stateValue).toString()
                val sidValue1 = dataState.lastIndexOf("[").plus(1).let {dataState.substring(it, dataState.length)}
                val sidValue2 = sidValue1.lastIndexOf("]").plus(0).let {sidValue1.substring(0, it)}
                val dataPlace = db.getPlaceUser(sidValue2.toInt())
                val adapterPlace = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_activated_1, dataPlace.sortedWith(compareBy{it.place}))
                spn_Place.adapter = adapterPlace
            }
        }

        btn_Select.setOnClickListener() {
            view_Selections.text = "${spn_Place.selectedItem}, ${spn_State.selectedItem}, ${spn_Country.selectedItem}"
        }

        btn_Back.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}



