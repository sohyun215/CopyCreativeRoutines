package com.android.copycreativeroutines.util

import com.google.firebase.auth.FirebaseAuth

class FBAuth {
    companion object {
        private lateinit var auth: FirebaseAuth
        fun getUid() : String {
            auth = FirebaseAuth.getInstance()
            return auth.currentUser?.uid.toString()
        }
    }
}