package app.cmtruong.com.quickmovies.views.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import app.cmtruong.com.quickmovies.R
import timber.log.Timber

/**
 * @author davidetruong
 * @version 1.0
 * @since 2018, November 26th
 */
class SplashScreenActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val TAG = SplashScreenActivity::class.java.canonicalName as String

        private fun getSplashDuration() = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Timber.plant(Timber.DebugTree())
        Timber.tag(TAG)
        Timber.d("$TAG is created")
        startMainScreen()
    }

    /**
     * Function which start main activity after splash screen
     */
    private fun startMainScreen() {
        Handler().postDelayed({
            val intent: Intent? = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, getSplashDuration())
    }
}
