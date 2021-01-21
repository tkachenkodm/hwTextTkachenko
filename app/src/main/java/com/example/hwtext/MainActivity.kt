package com.example.hwtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val inputRegex = Regex("^[A-Z]([A-Za-z]{3,})\$")
    private val adapter = PersonAdapter()
    var nameInputHasError = false
    var surnameInputHasError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (savedInstanceState != null) {
            val list = savedInstanceState.getParcelableArrayList<Person>("KEY")
            list?.forEach {
                adapter.personList.add(it)
            }
            adapter.notifyDataSetChanged()
        } else {
            fillInSampleData()
            adapter.notifyDataSetChanged()
        }

        setTextWatchers()
        setClickListener()

    }

    private fun fillInSampleData() {
        adapter.personList.add(Person("Bushra", "Howell"))
        adapter.personList.add(Person("Niyah", "Rawlings"))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putParcelableArrayList("KEY", adapter.personList)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        til_name.error = null
        til_surname.error = null
    }


    private fun setClickListener() {
        btn_addPerson.setOnClickListener {
            adapter.personList.add(
                Person(
                    et_name.text?.trim().toString(),
                    et_surname.text?.trim().toString()
                )
            )
            adapter.notifyItemInserted(adapter.itemCount)

            et_name.text?.clear()
            et_surname.text?.clear()

            til_name.error = null
            til_surname.error = null
        }
    }

    private fun setTextWatchers() {
        val nameTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                nameInputHasError = inputRegex.matches(et_name.text.toString())

                if (!nameInputHasError) {
                    til_name.error = resources.getString(R.string.error_message)
                } else {
                    til_name.error = null
                }

                btn_addPerson.isEnabled = surnameInputHasError && nameInputHasError

            }
        }

        val surnameTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                surnameInputHasError = inputRegex.matches(et_surname.text.toString())

                if (!surnameInputHasError) {
                    til_surname.error = resources.getString(R.string.error_message)
                } else {
                    til_surname.error = null
                }

                btn_addPerson.isEnabled = surnameInputHasError && nameInputHasError

            }
        }

        et_name.addTextChangedListener(nameTextWatcher)
        et_surname.addTextChangedListener(surnameTextWatcher)

    }
}