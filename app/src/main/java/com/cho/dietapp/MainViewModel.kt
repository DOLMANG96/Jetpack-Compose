package com.cho.dietapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cho.dietapp.model.Exercise
import com.cho.dietapp.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    // 이름, 나이, 키

    private val _name = MutableStateFlow("이름")
    val name : StateFlow<String> = _name

    private val _age = MutableStateFlow("0")
    val age : StateFlow<String> = _age

    private val _height = MutableStateFlow("0")
    val height : StateFlow<String> = _height

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage : StateFlow<String?> = _errorMessage

    private val _exerciseRecords = MutableStateFlow<List<Exercise>>(emptyList())
    val exerciseRecords : StateFlow<List<Exercise>> = _exerciseRecords


    init {
        loadProfile()
    }



    fun loadProfile() {
        FirebaseRepository.loadProfileData { name, age, height ->

            val defaultName = ""
            val defaultAge = ""
            val defaultHeight = ""


            _name.value = name.ifEmpty { defaultName}
            _age.value = age.ifEmpty { defaultAge }
            _height.value = height.ifEmpty { defaultHeight }
        }
    }

    fun saveProfile(name : String, age : String, height : String) {
        FirebaseRepository.saveProfileData(name,age,height) {success ->
            if(success) {
                _name.value = name
                _age.value = age
                _height.value = height
            } else {
                //실패
                _errorMessage.value = "저장 실패"
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }


    // 운동 저장
    fun saveExerciseRecord(exercise: Exercise) {
        viewModelScope.launch {
            FirebaseRepository.createExercise(exercise)
        }
    }

    //운동 불러오기

    fun loadExercise() {
        viewModelScope.launch {
            val exercises = FirebaseRepository.readExercise()
            _exerciseRecords.value = exercises
        }
    }

}

