package com.yes.inmyfood

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_add_btn.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        view?.let {
            when (view) {
                main_add_btn -> {
                    val intent = Intent(this, AddPostActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
