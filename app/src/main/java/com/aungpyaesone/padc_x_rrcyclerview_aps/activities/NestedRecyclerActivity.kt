package com.aungpyaesone.padc_x_rrcyclerview_aps.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.aungpyaesone.padc_x_rrcyclerview_aps.adapter.NestedMainAdapter
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.SubItemDelegate
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_nested_recycler.*

class NestedRecyclerActivity : AppCompatActivity(),SubItemDelegate {
    override fun touchItem() {
        Snackbar.make(mainLayout,"I am nested item",Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_recycler)

        val adapter = NestedMainAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        mainRecycler.layoutManager = layoutManager
        mainRecycler.adapter = adapter
    }
}
