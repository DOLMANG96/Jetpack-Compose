package com.cho.dietapp.ui.main.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.cho.dietapp.MainViewModel
import com.cho.dietapp.ui.common.DietRecordScreens
import com.cho.dietapp.ui.dietrecord.DietRecordInputScreen
import com.cho.dietapp.ui.dietrecord.DietRecordListScreen

@Composable
fun DietScreen(mainViewModel: MainViewModel) {

    var currentScreen by rememberSaveable { mutableStateOf(DietRecordScreens.DietRecordInfo) }

    when(currentScreen){
        DietRecordScreens.DietRecordInfo -> {
            DietRecordListScreen(
                mainViewModel = mainViewModel,

                onAddClicked = {
                    currentScreen = DietRecordScreens.DietRecordAdd
                }
            )
        }
        DietRecordScreens.DietRecordAdd -> {
            DietRecordInputScreen(

                mainViewModel = mainViewModel,

                onSaveClicked = {
                    currentScreen = DietRecordScreens.DietRecordInfo
                },
                onCancelClicked = {
                    currentScreen = DietRecordScreens.DietRecordInfo
                }
            )
        }
    }

}