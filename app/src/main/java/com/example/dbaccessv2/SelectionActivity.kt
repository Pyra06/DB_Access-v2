package com.example.dbaccessv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_selection.*

class SelectionActivity : AppCompatActivity(), OnPlaceItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        refreshSave()
    }

    private fun refreshSave() {
        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.addItemDecoration(DividerItemDecoration(this, 1))
        recyclerView1.adapter = AdapterClassA(DataBaseHandler(this).getPlaceList(), this)
    }

    override fun onItemClick(places: UserPlace, position: Int) {
        Toast.makeText(this, "places.place", Toast.LENGTH_SHORT).show()
    }
}
