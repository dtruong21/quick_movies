package app.cmtruong.com.quickmovies.views.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PopularFragment : Fragment() {

    companion object {
        @JvmStatic
        val TAG = TrendingFragment::class.java.canonicalName as String

        @JvmStatic
        fun getInstance(): PopularFragment = PopularFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}