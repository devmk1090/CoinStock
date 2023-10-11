package com.devkproject.coinstock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import com.devkproject.coinstock.databinding.ActivityMainBinding
import com.devkproject.coinstock.extensions.replaceFragment
import com.devkproject.coinstock.ui.AlarmFragment
import com.devkproject.coinstock.ui.CoinFragment
import com.devkproject.coinstock.ui.SettingFragment
import com.devkproject.coinstock.ui.StockFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            bottomNavigationView.setOnItemSelectedListener {
                supportFragmentManager.findFragmentByTag(MainSeparator.bottomMenuSeparator(it.itemId).name)?.let { fragment ->
                    replaceFragment(fragmentContainer.id, fragment, addToBackStack = false)
                } ?: replaceMainFragment(it)
                true
            }
            replaceMainFragment(bottomNavigationView.menu.findItem(bottomNavigationView.selectedItemId))
        }
    }

    private fun replaceMainFragment(menuItem: MenuItem) {
        val mainSeparator = MainSeparator.bottomMenuSeparator(menuItem.itemId)
        replaceFragment(
            binding.fragmentContainer.id,
            when (mainSeparator) {
                MainSeparator.COIN -> CoinFragment()
                MainSeparator.STOCK -> StockFragment()
                MainSeparator.ALARM -> AlarmFragment()
                MainSeparator.SETTING -> SettingFragment()
            }, mainSeparator.name
        )
    }
    enum class MainSeparator(@IdRes val menuId: Int) {
        COIN(R.id.menu_coin), STOCK(R.id.menu_stock), ALARM(R.id.menu_alarm), SETTING(R.id.menu_setting);

        companion object{
            fun bottomMenuSeparator(@IdRes menuId: Int) =
                values().find { it.menuId == menuId } ?: throw IllegalArgumentException("Can't find menuId")
        }
    }
}