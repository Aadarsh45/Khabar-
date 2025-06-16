package com.example.khabbar.presentation.main_activity

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabbar.domain.usecases.appEntry.AppEntryUseCases
import com.example.khabbar.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    var splashCondition = mutableStateOf(true)
        private set

    var startDestination = mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen) {
                startDestination.value = Route.NewsNavigation.route
            } else {
                startDestination.value = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}