package com.cho.dietapp.ui.main.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.cho.dietapp.MainViewModel
import com.cho.dietapp.ui.common.ProfileScreens
import com.cho.dietapp.ui.profile.ProfileEditScreen
import com.cho.dietapp.ui.profile.ProfileInfoScreen


@Composable
fun ProfileScreen(mainViewModel: MainViewModel) {

    var currentScreen by rememberSaveable { mutableStateOf(ProfileScreens.ProfileInfo) }

    val name by mainViewModel.name.collectAsState()
    val age by mainViewModel.age.collectAsState()
    val height by mainViewModel.height.collectAsState()
    
    //저장실패메세지

    val errorMessage by mainViewModel.errorMessage.collectAsState()

    if (errorMessage != null) {
        Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
        mainViewModel.clearErrorMessage()
    }
    
    
    


    when (currentScreen) {
        ProfileScreens.ProfileInfo -> {
            ProfileInfoScreen(
                name = name,
                age = age,
                height = height,
                onEditClicked = { currentScreen = ProfileScreens.ProfileEdit}
            )
        }
        ProfileScreens.ProfileEdit -> {
            ProfileEditScreen(
                initialName = name,
                initialAge = age,
                initialHeight = height,

                onSaveClicked = { newName, newAge, newHeight ->

                    //저장하기 버튼
                    mainViewModel.saveProfile(newName,newAge,newHeight)
                    //


                    currentScreen = ProfileScreens.ProfileInfo
                },

                onCancelClicked = {
                    currentScreen = ProfileScreens.ProfileInfo
                }
            )
        }
    }
}