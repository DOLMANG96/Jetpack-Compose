package com.cho.dietapp.ui.dietrecord.dietRecordInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cho.dietapp.repository.FirebaseRepository
import kotlinx.coroutines.launch

class DietRecodeInfoViewModel : ViewModel() {

    fun removeExerciseRecord(docId : String) {
        viewModelScope.launch {
            FirebaseRepository.removeExercise(docId)
        }
    }
}