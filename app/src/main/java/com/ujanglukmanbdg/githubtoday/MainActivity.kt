package com.ujanglukmanbdg.githubtoday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveUserList: Button = findViewById(R.id.btn_move_user_list)
        btnMoveUserList.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_move_user_list -> {
                val moveIntent = Intent(this@MainActivity, ListUserActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}