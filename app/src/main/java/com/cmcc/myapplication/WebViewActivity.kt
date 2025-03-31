package com.cmcc.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentActivity

class WebViewActivity : FragmentActivity() {
    private lateinit var webView: WebView
    
    // 滚动步长
    private val SCROLL_STEP = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_webview)

        webView = findViewById(R.id.webview)
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }

        webView.webViewClient = WebViewClient()
        webView.loadUrl(BuildConfig.WEB_URL)

        // 添加遥控器按键支持
        webView.setOnKeyListener { _, keyCode, event ->
            if (event.action != KeyEvent.ACTION_DOWN) return@setOnKeyListener false
            
            when (keyCode) {
                KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                    webView.performClick()
                    true
                }
                KeyEvent.KEYCODE_DPAD_UP -> {
                    webView.scrollBy(0, -SCROLL_STEP)
                    true
                }
                KeyEvent.KEYCODE_DPAD_DOWN -> {
                    webView.scrollBy(0, SCROLL_STEP)
                    true
                }
                KeyEvent.KEYCODE_DPAD_LEFT -> {
                    if (webView.canGoBack()) {
                        webView.goBack()
                        true
                    } else false
                }
                KeyEvent.KEYCODE_DPAD_RIGHT -> {
                    if (webView.canGoForward()) {
                        webView.goForward()
                        true
                    } else false
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
} 