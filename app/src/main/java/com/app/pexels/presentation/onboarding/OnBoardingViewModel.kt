package com.app.pexels.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.app.pexels.domain.repository.PexelsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    private val pexelsRepository: PexelsRepository,
) : ViewModel() {

    private val _isOnboardingFinished = MutableStateFlow(pexelsRepository.getOnBoardingState())
    val isOnboardingFinished = _isOnboardingFinished.asStateFlow()

    fun onBoardingFinished() {
        _isOnboardingFinished.tryEmit(true)
        pexelsRepository.setOnboardingState(_isOnboardingFinished.value)
    }
}