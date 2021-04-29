package se.jun.allcommunity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.jun.allcommunity.R
import se.jun.allcommunity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}