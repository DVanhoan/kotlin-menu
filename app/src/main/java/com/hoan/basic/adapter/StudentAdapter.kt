package com.hoan.basic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.hoan.basic.R
import com.hoan.basic.entities.Student

class StudentAdapter(private val context: Context, private val list: List<Student>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.student_item, parent, false)

        val ivImageStudent = view.findViewById<ImageView>(R.id.ivImageStudent)
        val tvNameStudent = view.findViewById<TextView>(R.id.tvNameStudent)
        val tvPhoneStudent = view.findViewById<TextView>(R.id.tvPhoneStudent)

        val student = list[position]
        tvNameStudent.text = student.name
        tvPhoneStudent.text = student.phone

        Glide.with(context).load(student.image).into(ivImageStudent)
        return view
    }
}
