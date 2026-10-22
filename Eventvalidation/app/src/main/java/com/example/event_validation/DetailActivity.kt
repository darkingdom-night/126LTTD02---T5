package com.example.event_validation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Mở từ nút "Chi tiết" khi chưa đăng ký -> không có extra -> hiện "—"
        fun show(id: Int, fmt: Int, key: String) {
            findViewById<TextView>(id).text = getString(fmt, intent.getStringExtra(key) ?: "—")
        }
        show(R.id.tvName, R.string.fmt_name, EXTRA_NAME)
        show(R.id.tvEmail, R.string.fmt_email, EXTRA_EMAIL)
        show(R.id.tvPhone, R.string.fmt_phone, EXTRA_PHONE)
        show(R.id.tvMsv, R.string.fmt_msv, EXTRA_MSV)
        show(R.id.tvClass, R.string.fmt_class, EXTRA_CLASS)

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            // Quay về RegisterActivity đang có sẵn (giữ dữ liệu đã nhập) thay vì chồng thêm bản mới
            startActivity(
                Intent(this, RegisterActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            )
        }
    }

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_EMAIL = "email"
        const val EXTRA_PHONE = "phone"
        const val EXTRA_MSV = "msv"
        const val EXTRA_CLASS = "class"
    }
}
