package com.example.dementiaapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.dementiaapp.R

class ReminderAdapter(
    private val context: Context,
    private val reminders: MutableList<String>,
    private val deleteAction: (Int) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int {
        return reminders.size
    }

    override fun getItem(position: Int): Any {
        return reminders[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.reminder_list_item, parent, false)
        val reminderText: TextView = view.findViewById(R.id.reminder_text)
        val deleteButton: Button = view.findViewById(R.id.delete_button)

        reminderText.text = reminders[position]
        deleteButton.setOnClickListener {
            deleteAction(position)
        }

        return view
    }
}
