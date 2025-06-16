package com.example.khabbar.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.khabbar.presentation.main_activity.MainViewModel
import com.example.khabbar.presentation.navgraph.NavGraph
import com.example.khabbar.ui.theme.KhabbarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply{
            setKeepOnScreenCondition{
                viewModel.splashCondition.value
            }
        }


        super.onCreate(savedInstanceState)
        setContent {
            KhabbarTheme {
                Box(
                    modifier = Modifier.Companion.background(color = MaterialTheme.colorScheme.background)
                ) {
                    val startDestination = viewModel.startDestination.value
                    NavGraph(startDestination = startDestination)


                }

            }
        }
    }





    }