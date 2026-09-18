package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Student
import com.example.myapplication.utils.toAcademicRanking
import com.example.myapplication.utils.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "2415053122302",
        name = "Nguyen Van Quoc Chuong",
        className = "DD2026",
        email = "anv@ute.udn.vn",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindStudentData(currentStudent)

        binding.btnUpdateGpa.setOnClickListener {
            val newGpa = binding.edtNewGpa.text.toString().trim().toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent)
            toast("Cập nhật điểm thành công!")
        }
    }

    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}