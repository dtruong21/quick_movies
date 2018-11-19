package app.cmtruong.com.quickmovies.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.views.fragments.PopularFragment
import app.cmtruong.com.quickmovies.views.fragments.TopRatedFragment
import app.cmtruong.com.quickmovies.views.fragments.TrendingFragment
import timber.log.Timber

/**
 * @author Davide Truong
 * @version 1.0
 * @since October 25th, 2018
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        if(savedInstanceState != null){
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.trending_container, TrendingFragment.getInstance())
                    .commit()

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.popular_container, PopularFragment.getInstance())
                    .commit()

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.top_rate_container, TopRatedFragment.getInstance())
                    .commit()
        }
    }

}
