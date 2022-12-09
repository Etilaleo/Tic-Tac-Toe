package com.example.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Input : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val submitBtn = findViewById<Button>(R.id.submitBtn)
        val player1 = findViewById<EditText>(R.id.player1)
        val player2 = findViewById<EditText>(R.id.player2)

        submitBtn.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                it.putExtra("Name 1", player1.text.toString())
                it.putExtra("Name 2", player2.text.toString())
                startActivity(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            R.id.close -> finish()
        }
        return true
    }
}