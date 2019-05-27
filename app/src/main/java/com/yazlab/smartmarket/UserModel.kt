package com.yazlab.smartmarket

import com.google.firebase.auth.FirebaseUser
import com.yazlab.smartmarket.ui.Campain

object UserModel {
    var uid: String = ""
    var email: String = ""
    var user: FirebaseUser? = null
    var campains = ArrayList<Campain>()
}