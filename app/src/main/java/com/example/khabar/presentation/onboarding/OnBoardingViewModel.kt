package com.example.khabar.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabar.domain.usecases.app_entry.AppEntryusecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor (private val appEntryUsecases: AppEntryusecases):ViewModel() {

    fun onEvent(event:OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry ->{
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch{
            appEntryUsecases.saveAppEntry()

        }
    }

}