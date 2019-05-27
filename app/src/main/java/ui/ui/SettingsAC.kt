package ui.ui

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager
import com.yazlab.smartmarket.R
import com.yazlab.smartmarket.Utils

import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.content_settings.*

class SettingsAC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)


        changePasswordButton.setOnClickListener {
            if(!checkEditTexts()){
                Utils.showAlertDialog(this, "Lütfen boşlukları doldurun.") {}
            } else {
                if (password.text.toString() != passwordAgain.text.toString()){
                    Utils.showAlertDialog(this, "Şifreler eşleşmiyor.") {}
                } else {
                    User.user?.updatePassword(password.text.toString())?.addOnCompleteListener {
                        if (it.isSuccessful){
                            Utils.showAlertDialog(this, "Şifre değiştirme işlemi başarılı.") {
                                finish()
                            }
                        } else {
                            Utils.showAlertDialog(this, "Şifre değiştirme işlemi başarısız.") {}
                        }
                    }
                }
            }
        }
    }

    private fun checkEditTexts(): Boolean {
        return !(password.text.isEmpty() || passwordAgain.text.isEmpty())
    }

}
