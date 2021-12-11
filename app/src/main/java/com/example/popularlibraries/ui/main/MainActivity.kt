package com.example.popularlibraries.ui.main


import android.view.Menu
import android.view.MenuItem
import com.example.popularlibraries.App
import com.example.popularlibraries.R
import com.example.popularlibraries.screens.AppScreens
import com.example.popularlibraries.ui.base.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun ProvidePresenter(): MainPresenter {
        return MainPresenter(App.instance.router)
    }

    //private val presenter by moxyPresenter { MainPresenter(App.instance.router) }

    override fun onResumeFragments() {
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_convert_jpg_to_png -> {
                App.instance.router.navigateTo(AppScreens.converterJpgToPng())
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}