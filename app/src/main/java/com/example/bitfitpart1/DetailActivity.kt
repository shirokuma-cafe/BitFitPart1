package com.example.bitfitpart1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var recordButton: Button
    private lateinit var enterDateET: EditText
    private lateinit var enterTimeET: EditText
    private lateinit var enterAmountET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(android.R.style.Theme_Light);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_layout)

        recordButton = findViewById(R.id.recordButton)
        enterDateET = findViewById(R.id.enterDate)
        enterTimeET = findViewById(R.id.enterTime)
        enterAmountET = findViewById(R.id.enterAmount)

        recordButton.setOnClickListener{
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }

            val newItem = Item(date = enterDateET.text.toString(), time = enterTimeET.text.toString(), amount = enterAmountET.text.toString().toInt())
            lifecycleScope.launch(IO){
                (application as ItemApplication).db.itemDao().insert(newItem)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}