@file:Suppress("DEPRECATION")

package ui.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.yazlab.smartmarket.R
import com.yazlab.smartmarket.Utils
import kotlinx.android.synthetic.main.activity_login.*


class LoginAC : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Lütfen Bekleyiniz...")

        mAuth = FirebaseAuth.getInstance()

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)

        register.setOnClickListener {
            progressDialog.show()
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            mAuth?.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth?.currentUser
                        User.uid = user?.uid.toString()
                        User.email = user?.email.toString()
                        User.user = user
                        progressDialog.dismiss()
                        startActivity(Intent(this, HomeActvity::class.java))
                        finish()
                    } else {
                        progressDialog.dismiss()
                        Utils.showAlertDialog(this, "Kayıt İşlemi başarısız.") {
                            username.text.clear()
                            password.text.clear()
                        }
                    }
                }
        }

        login.setOnClickListener {
            progressDialog.show()
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            mAuth?.signInWithEmailAndPassword(username.text.toString(), password.text.toString())?.addOnCompleteListener {
                if (it.isSuccessful){
                    val user = mAuth?.currentUser
                    User.uid = user?.uid.toString()
                    User.email = user?.email.toString()
                    User.user = user
                    progressDialog.dismiss()
                    startActivity(Intent(this, HomeActvity::class.java))
                    finish()
                } else {
                    progressDialog.dismiss()
                    Utils.showAlertDialog(this, "Giriş İşlemi başarısız.") {
                        username.text.clear()
                        password.text.clear()
                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
/*
        val currentUser = mAuth?.getCurrentUser()
*/
        // TODO: Check if user is signed in (non-null) and update UI accordingly.

    }
}

