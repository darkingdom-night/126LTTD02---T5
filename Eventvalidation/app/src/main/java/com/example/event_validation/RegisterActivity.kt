package com.example.event_validation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private class Field(val layout: TextInputLayout, @StringRes val error: Int, val regex: Regex, val key: String) {
        val text get() = layout.editText!!.text.toString().trim()
        fun validate(): Boolean {
            val ok = regex.matches(text)
            layout.error = if (ok) null else layout.context.getString(error)
            return ok
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val fields = listOf(
            Field(findViewById(R.id.tilName), R.string.err_name, Validators.name, DetailActivity.EXTRA_NAME),
            Field(findViewById(R.id.tilEmail), R.string.err_email, Validators.email, DetailActivity.EXTRA_EMAIL),
            Field(findViewById(R.id.tilPhone), R.string.err_phone, Validators.phone, DetailActivity.EXTRA_PHONE),
            Field(findViewById(R.id.tilMsv), R.string.err_msv, Validators.msv, DetailActivity.EXTRA_MSV),
            Field(findViewById(R.id.tilClass), R.string.err_class, Validators.cls, DetailActivity.EXTRA_CLASS),
        )
        // Validate ngay khi gõ
        fields.forEach { f -> f.layout.editText!!.doOnTextChanged { _, _, _, _ -> f.validate() } }

        findViewById<Button>(R.id.btnRegister).setOnClickListener { v ->
            // map (không dùng all trực tiếp) để mọi ô lỗi đều được hiện
            if (fields.map { it.validate() }.all { it }) {
                val i = Intent(this, DetailActivity::class.java)
                fields.forEach { i.putExtra(it.key, it.text) }
                startActivity(i)
            } else {
                Snackbar.make(v, R.string.err_form, Snackbar.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnGoDetail).setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
    }
}