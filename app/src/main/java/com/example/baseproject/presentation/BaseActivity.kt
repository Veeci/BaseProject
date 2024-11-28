package com.example.baseproject.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.example.baseproject.R
import com.example.baseproject.databinding.ActivityBaseBinding
import com.example.baseproject.domain.utils.NetworkUtil
import com.example.baseproject.domain.utils.ThemeManager
import com.example.baseproject.domain.utils.ThemeMode
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.presentation.navigation.BaseNavigator
import java.util.Locale

abstract class BaseActivity<V : ViewBinding, N : BaseNavigator>(private val layoutId: Int) :
    AppCompatActivity() {
    var navController: NavController? = null
    abstract val navigator: N

    private lateinit var rootView: ActivityBaseBinding
    lateinit var binding: V

    open var statusBarHeight = 30
    open var bottomNavigationHeight = 30
    open var networkConnected = false
    open var onPermissionResult: ((requestCode: Int, permissions: Array<out String>, grantResults: IntArray, deviceId: Int) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        rootView = DataBindingUtil.inflate(layoutInflater, R.layout.activity_base, null, false)
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)

        setContentView(rootView.root)
    }

    private fun checkNetwork() {
        NetworkUtil(this).observe(this) {
            if (!it) {
                toastShort(resources.getString(R.string.offline_status))
            }
            networkConnected = it
        }
    }

    fun updateLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        applyOverrideConfiguration(config)
        recreate()
    }

    fun applyTheme(themeMode: ThemeMode) {
        when (themeMode) {
            ThemeMode.LIGHT -> setTheme(R.style.Theme_App_Light)
            ThemeMode.DARK -> setTheme(R.style.Theme_App_Dark)
            ThemeMode.CUSTOM -> setTheme(R.style.Theme_App_Custom)
        }
        ThemeManager(this).currentTheme = themeMode
        window.decorView.invalidate()
    }

    abstract fun initView(
        savedInstanceState: Bundle?,
        binding: V,
    )
}
