package ui.ui

import com.google.firebase.auth.FirebaseUser

object User {
    var uid: String = ""
    var email: String = ""
    var user: FirebaseUser? = null
    var campains = ArrayList<Campain>()
}