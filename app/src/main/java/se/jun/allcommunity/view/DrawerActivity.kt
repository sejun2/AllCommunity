package se.jun.allcommunity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.jun.allcommunity.R
import se.jun.allcommunity.databinding.ActivityDrawerBinding

class DrawerActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}