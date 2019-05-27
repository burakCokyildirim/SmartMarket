package com.yazlab.smartmarket

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_campain_list.*

class CampainListActivity : AppCompatActivity() {
    var adapter: CampainListAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campain_list)

        adapter = CampainListAdapter()

        list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        list.adapter = adapter
    }
}
