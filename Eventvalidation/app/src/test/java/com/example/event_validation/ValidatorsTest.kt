package com.example.event_validation

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidatorsTest {
    @Test fun name() {
        assertTrue(Validators.name.matches("Nguyễn Văn A"))
        assertFalse(Validators.name.matches("Nguyen"))
        assertFalse(Validators.name.matches("Nguyen Van 1"))
    }

    @Test fun email() {
        assertTrue(Validators.email.matches("a.b@gmail.com"))
        assertFalse(Validators.email.matches("email_chua_hop_le"))
        assertFalse(Validators.email.matches("a@b"))
    }

    @Test fun phone() {
        assertTrue(Validators.phone.matches("0901234567"))
        assertTrue(Validators.phone.matches("+84901234567"))
        assertFalse(Validators.phone.matches("0201234567"))
        assertFalse(Validators.phone.matches("090123"))
    }

    @Test fun msvAndClass() {
        assertTrue(Validators.msv.matches("2200123456"))
        assertFalse(Validators.msv.matches("22a"))
        assertTrue(Validators.cls.matches("22T_DT1"))
        assertFalse(Validators.cls.matches("A"))
    }
}
