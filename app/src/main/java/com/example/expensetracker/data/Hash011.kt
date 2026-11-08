package com.example.expensetracker.data

import java.security.MessageDigest

object Hash011 {
    fun sha256011(teks011: String): String =
        MessageDigest.getInstance("SHA-256")
            .digest(teks011.toByteArray(Charsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
}
