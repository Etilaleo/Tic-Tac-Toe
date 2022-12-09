package com.example.tic_tac_toe

import android.content.Context
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


        //Saving the inputs of the players so when not filled can be filled automatically
        val sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString("player1", player1.text.toString())
            putString("player2", player2.text.toString())
            apply()
        }

        submitBtn.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                val newPlayer1 = sharedPref.getString("player1", "Leo")
                val newPlayer2 = sharedPref.getString("player2", "Ralph")
                it.putExtra("Name 1", newPlayer1)
                it.putExtra("Name 2", newPlayer2)
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