package com.devkproject.coinstock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import com.devkproject.coinstock.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {

        }
    }

    private fun replaceMainFragment(menuItem: MenuItem) {
        val mainSeparator = MainSeparator.bottomMenuSeparator(menuItem.itemId)

    }
    enum class MainSeparator(@IdRes val menuId: Int) {
        COIN(R.id.menu_coin), STOCK(R.id.menu_stock), ALARM(R.id.menu_alarm), SETTING(R.id.menu_setting);

        companion object{
            fun bottomMenuSeparator(@IdRes menuId: Int) =
                values().find { it.menuId == menuId } ?: throw IllegalArgumentException("Can't find menuId")
        }
    }
}