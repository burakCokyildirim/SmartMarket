package com.yazlab.smartmarket

import com.google.firebase.auth.FirebaseUser

object UserModel {
    var uid: String = ""
    var email: String = ""
    var user: FirebaseUser? = null
    var campains = ArrayList<Campain>()
}