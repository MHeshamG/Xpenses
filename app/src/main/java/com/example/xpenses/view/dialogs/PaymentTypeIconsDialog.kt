package com.example.xpenses.view.dialogs

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.R
import com.example.xpenses.view.recycler_view.adapters.PaymentTypeIconsRecyclerAdapter

class PaymentTypeIconsDialog(
    activity: Activity
) : Dialog(activity,R.style.NewDialog),
    View.OnClickListener {

    var recyclerView: RecyclerView? = null
    var adapter: PaymentTypeIconsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.payment_type_icons_dialog)
        recyclerView = findViewById(R.id.payment_type_icons_recycler_view)
        recyclerView?.adapter = adapter
    }


    override fun onClick(v: View) {
        dismiss()
    }
}