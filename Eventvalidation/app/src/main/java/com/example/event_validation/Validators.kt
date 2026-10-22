package com.example.event_validation

object Validators {
    val name = Regex("""[\p{L}\p{M}]+(\s+[\p{L}\p{M}]+)+""") // >= 2 từ, chỉ chữ
    val email = Regex("""[\w.+-]+@[\w-]+(\.[\w-]+)+""")
    val phone = Regex("""(0|\+84)[35789]\d{8}""") // SĐT di động VN
    val msv = Regex("""\d{6,13}""")
    val cls = Regex("""[\p{L}\p{N}_.-]{2,20}""")
}