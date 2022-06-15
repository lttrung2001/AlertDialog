package com.ltbth.dialog

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Alert Dialog")
            setMessage("Are you want to finish this activity?")
            setPositiveButton("Có") { _, _ ->
                Toast.makeText(applicationContext, "YES", Toast.LENGTH_SHORT).show()
                this@MainActivity.finish()
            }
            setNegativeButton("Không") { _, _ ->
                Toast.makeText(applicationContext, "NO", Toast.LENGTH_SHORT).show()
            }
            setNeutralButton("Hông biết nữa") { _, _ ->
                Toast.makeText(applicationContext, "MORE", Toast.LENGTH_SHORT).show()
            }
            setPositiveButtonIcon(resources.getDrawable(R.drawable.ic_exit, theme))
            setNegativeButtonIcon(resources.getDrawable(R.drawable.ic_close, theme))
            setNeutralButtonIcon(resources.getDrawable(R.drawable.ic_infinity, theme))
        }
        val button1: Button = findViewById(R.id.btn1) // base alert & icons
        val button2: Button = findViewById(R.id.btn2) // alert with items
        val button3: Button = findViewById(R.id.btn3) // alert multichoices
        val button4: Button = findViewById(R.id.btn4) // alert with centered buttons
        val button5: Button = findViewById(R.id.btn5) // alert with edit text
        button1.setOnClickListener {
            builder.show()
        }
        button2.setOnClickListener {
            val items = arrayOf("Microsoft", "Apple", "Amazon", "Google")
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("Alert Dialog")
                setPositiveButton("Có") { _, _ ->
                    Toast.makeText(applicationContext, "YES", Toast.LENGTH_SHORT).show()
                }

                // With items
                setItems(items) { _, which ->
                    Toast.makeText(
                        applicationContext,
                        items[which] + "is clicked",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                show()
            }
        }
        button3.setOnClickListener {
            val items = arrayOf("Microsoft", "Apple", "Amazon", "Google")
            val selectedList = ArrayList<Int>()
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("This is list choice dialog box")
                setMultiChoiceItems(items, null) { _, which, isChecked ->
                    if (isChecked) {
                        selectedList.add(which)
                    } else if (selectedList.contains(which)) {
                        selectedList.remove(Integer.valueOf(which))
                    }
                    setPositiveButton("DONE") { _, _ ->
                        val selectedStrings = ArrayList<String>()
                        for (j in selectedList.indices) {
                            selectedStrings.add(items[selectedList[j]])
                        }
                        Toast.makeText(
                            applicationContext,
                            "Items selected are: " + selectedStrings.toTypedArray()
                                .contentToString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                show()
            }
            button4.setOnClickListener {
                val alertDialog = AlertDialog.Builder(this).create()
                alertDialog.setTitle("Title")
                alertDialog.setMessage("Message")

                alertDialog.setButton(
                    AlertDialog.BUTTON_POSITIVE, "Yes"
                ) { dialog, _ -> dialog.dismiss() }

                alertDialog.setButton(
                    AlertDialog.BUTTON_NEGATIVE, "No"
                ) { dialog, _ -> dialog.dismiss() }
                alertDialog.show()

                val btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                val btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)

                val layoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
                layoutParams.weight = 10f
                btnPositive.layoutParams = layoutParams
                btnNegative.layoutParams = layoutParams
            }
            button5.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                with(builder) {
                    setTitle("With EditText")
                    val layout = layoutInflater.inflate(R.layout.alert_dialog_with_edittext, null)
                    val editText: EditText = layout.findViewById(R.id.et_text)
                    setView(layout)
                    setPositiveButton("OK") { _, _ ->
                        Toast.makeText(
                            applicationContext,
                            "EditText is " + editText.text.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    show()
                }
            }
        }
    }
}
