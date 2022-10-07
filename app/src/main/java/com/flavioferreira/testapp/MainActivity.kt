package com.flavioferreira.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import com.flavioferreira.testapp.databinding.ActivityMainBinding

// Define list of URLs
const val HOME_URL = "https://www.thebay.com/"
const val SHOP_URL  = "https://www.thebay.com/c/men"
const val ACCOUNT_URL = "https://www.thebay.com/account/login"
const val BAG_URL = "https://www.thebay.com/cart"
// Define the burger and close buttons
const val openBurger = "button.navbar-toggler"
const val closeBurger = "button.close-button"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var webView: WebView
    var isNavbarToggled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set bingind and content view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Setup webview and load home url
        webView = findViewById(R.id.main_wv)
        webView.settings.javaScriptEnabled = true
        loadPage(HOME_URL)
        // Setup bottom nav buttons
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_btn -> loadPage(HOME_URL)
                R.id.shop_btn -> loadPage(SHOP_URL)
                R.id.account_btn -> loadPage(ACCOUNT_URL)
                R.id.bag_btn -> loadPage(BAG_URL)
                else -> {
                    print("Something went wrong")
                }
            }
            true
        }
    }

    private fun loadPage(url: String) {
        // Load page in webView from URL
        webView.loadUrl(url)
        // Also reset the navbar
        isNavbarToggled = false
    }

    fun menuClick(v: View) {
        // On click of burger button, toggle the button in the webview
        // Select the right one to simulate a click
        val clickableBurger = if (isNavbarToggled) closeBurger else openBurger
        // Do the actual click
        webView.loadUrl("""
            javascript:(function f() {
                var btn = document.querySelector("$clickableBurger").click()
            })()
        """)
        // Now toggle the variable so we can select the other one next time
        isNavbarToggled = !isNavbarToggled
    }
}