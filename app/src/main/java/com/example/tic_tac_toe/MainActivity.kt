package com.example.tic_tac_toe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var score1 = 0
    private var score2 = 0
    private var checkNought = "X"
    private val buttonsList  = mutableListOf<Button>()
    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding : ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, binding.drawerView, R.string.open, R.string.close)
        binding.drawerView.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                R.id.close -> finish()
            }
            true
        }

        inboard()

        val player1 = intent.getStringExtra("Name 1")
        val player2 = intent.getStringExtra("Name 2")
        binding.name1.text = "$player1 ="
        binding.name2.text = "$player2 ="

        binding.reset.setOnClickListener {
            for(button in buttonsList) {
                button.text = ""
            }
        }
    }


    private fun inboard() {
        buttonsList.add(binding.a1)
        buttonsList.add(binding.a2)
        buttonsList.add(binding.a3)
        buttonsList.add(binding.b1)
        buttonsList.add(binding.b2)
        buttonsList.add(binding.b3)
        buttonsList.add(binding.c1)
        buttonsList.add(binding.c2)
        buttonsList.add(binding.c3)
    }

    //To set the board.
    fun gameBoard(view: View) {
        if (view !is Button) {
            return
        }

        //Method to add to the board.
        addToBoard(view)

        //Method for checking Victory.
        if (victory("X")) {
            score1++
            binding.name1score.text = score1.toString()
            AlertDialog.Builder(this)
                .setTitle("TIC TAC TOE")
                .setMessage("${binding.name1.text} $score1")
                .setPositiveButton("Reset") {_,_ ->
                    for (button in buttonsList)
                        button.text = ""
                }
                .show()
        }
        else if (victory("O")) {
            score2++
            binding.name2score.text = score2.toString()
            AlertDialog.Builder(this)
                .setTitle("TIC TAC TOE")
                .setMessage("${binding.name2.text} $score2")
                .setPositiveButton("Reset") {_,_ ->
                    for (button in buttonsList)
                        button.text = ""
                }
                .show()
        }
        //Method to reset the gameBoard when its Draw.
        else if (gameBoardFull()) {
            AlertDialog.Builder (this)
                .setTitle("TIC TAC TOE")
                .setMessage("This was a draw!")
                .setPositiveButton("Reset") {_,_ ->
                    for (button in buttonsList)
                        button.text = ""
                }
                .show()
        }
    }


    private fun victory(play: String): Boolean {
        //Horizontal Victory
        if (binding.a1.text == play && binding.a2.text == play && binding.a3.text == play )
            return true
        if (binding.b1.text == play && binding.b2.text == play && binding.b3.text == play )
            return true
        if (binding.c1.text == play && binding.c2.text == play && binding.c3.text == play )
            return true

        //Vertical Victory
        if (binding.a1.text == play && binding.b1.text == play && binding.c1.text == play )
            return true
        if (binding.a2.text == play && binding.b2.text == play && binding.c2.text == play )
            return true
        if (binding.a3.text == play && binding.b3.text == play && binding.c3.text == play )
            return true

        //Diagonal Victory
        if (binding.a1.text == play && binding.b2.text == play && binding.c3.text == play )
            return true
        if (binding.a3.text == play && binding.b2.text == play && binding.c1.text == play )
            return true

        return false
    }

    private fun gameBoardFull(): Boolean {
        for(button in buttonsList) {
            if (button.text == "") {
                return false
            }
        }
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun addToBoard(button: Button) {
        if (button.text != "") {
            return
        }
        if (checkNought == "X")
        {
            button.text = "X"
            checkNought = "O"
            binding.display.text = "Turn $checkNought"
        }
        else if (checkNought == "O")
        {
            button.text = "O"
            checkNought = "X"
            binding.display.text = "Turn $checkNought"
        }
    }

    //For the toggle Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}