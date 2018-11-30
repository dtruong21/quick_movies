package app.cmtruong.com.quickmovies.views.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.cmtruong.com.quickmovies.R

/**
 * @author davidetruong
 * @version 1.0
 * @since 2018, November 26th
 */
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startMainScreen()
    }

    private fun startMainScreen(){
        val intent: Intent? = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
