package com.example.modoexamen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modoexamen.login.data.provider.RetrofitProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        RetrofitProvider().initialize()
    }
}
