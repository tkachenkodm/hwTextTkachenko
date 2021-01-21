package com.example.hwtext

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_list_item.view.*

class PersonAdapter(val personList: ArrayList<Person> = arrayListOf()):RecyclerView.Adapter<PersonViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_list_item, parent, false)

        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(personList[position])
    }

    override fun getItemCount(): Int = personList.size

}

class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view){
    fun bind(person: Person){
        val name = SpannableStringBuilder("${person.name} ${person.surname}")

        name.setSpan(UnderlineSpan(), person.name.length + 1, name.length, 0)
        name.setSpan(ForegroundColorSpan(Color.RED), person.name.length + 1, name.length, 0)

        itemView.tv_name.text = name
    }
}