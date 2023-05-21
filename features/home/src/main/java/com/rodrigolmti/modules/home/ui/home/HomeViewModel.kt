package com.rodrigolmti.modules.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigolmti.modules.home.domain.use_case.GetDrinksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDrinksUseCase: GetDrinksUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val viewState: StateFlow<HomeViewState> = _viewState

    init {
        viewModelScope.launch {
            getDrinksUseCase().fold({
                _viewState.tryEmit(HomeViewState.Success(it))
            }, {
                _viewState.tryEmit(HomeViewState.Error)
            })
        }
    }
}