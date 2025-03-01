package com.hoan.basic.activities

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.hoan.basic.R
import com.hoan.basic.adapter.StudentAdapter
import com.hoan.basic.entities.Student
import com.hoan.basic.databinding.ActivityMainBinding

class StudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        registerForContextMenu(binding.lvStudentList)


        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Trang chủ", Toast.LENGTH_SHORT).show()
                R.id.nav_about -> Toast.makeText(this, "Giới thiệu", Toast.LENGTH_SHORT).show()
                R.id.nav_settings -> Toast.makeText(this, "Cài đặt", Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawers()
            true
        }

        setStudentsData()
        studentAdapter = StudentAdapter(this, studentList)
        binding.lvStudentList.adapter = studentAdapter

        binding.lvStudentList.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val student = studentList[position]
                Toast.makeText(this, "${student.name}, ${student.phone}, ${student.address}", Toast.LENGTH_LONG).show()
            }


        binding.fab.setOnClickListener {
            showAlertDialog()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                Toast.makeText(this, "Chọn Sửa sinh viên", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_delete -> {
                Toast.makeText(this, "Chọn Xóa sinh viên", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.option_menu, menu)
        menu.setHeaderTitle("Chọn hành động")
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val student = studentList[info.position]
        return when (item.itemId) {
            R.id.action_edit -> {
                Toast.makeText(this, "Sửa: ${student.name}", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_delete -> {
                Toast.makeText(this, "Xóa: ${student.name}", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Thông báo")
            .setMessage("Đây là AlertDialog")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setNegativeButton("Hủy") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun setStudentsData() {
        studentList = listOf(
            Student(1, "Nguyễn Văn A", "0123456789", "Hà Nội", R.drawable.a1),
            Student(2, "Nguyễn Văn B", "0123456789", "Hà Nội", R.drawable.a2),
            Student(3, "Nguyễn Văn C", "0123456789", "Hà Nội", R.drawable.a3),
            Student(4, "Nguyễn Văn D", "0123456789", "Hà Nội", R.drawable.a4)
        )
    }
}
