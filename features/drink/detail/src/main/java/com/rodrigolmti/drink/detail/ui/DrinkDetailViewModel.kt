package com.rodrigolmti.drink.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigolmti.drink.detail.domain.use_case.GetDrinkByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DrinkDetailViewModel(
    private val getDrinkByIdUseCase: GetDrinkByIdUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<DrinkDetailState>(DrinkDetailState.Loading)
    val viewState: StateFlow<DrinkDetailState> = _viewState

    fun getDrinkById(id: String) {
        viewModelScope.launch {
            _viewState.tryEmit(DrinkDetailState.Loading)

            getDrinkByIdUseCase(id).fold({
                _viewState.tryEmit(DrinkDetailState.Success(it))
            }, {
                _viewState.tryEmit(DrinkDetailState.Error)
            })
        }
    }
}