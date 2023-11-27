package com.app.pexels.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val viewModel: ViewModel,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModel as? T ?: throw ClassCastException(
            "This instance of BasicViewModelProviderFactory can only be used to construct " +
                    "instances of ${viewModel.javaClass}",
        )
}
