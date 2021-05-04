package com.yes.inmyfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yes.inmyfood.addpost.AddPostActivity
import com.yes.inmyfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.actMainBtnAdd.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.let {
            when (view) {
                binding.actMainBtnAdd -> {
                    val intent = Intent(this, AddPostActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
