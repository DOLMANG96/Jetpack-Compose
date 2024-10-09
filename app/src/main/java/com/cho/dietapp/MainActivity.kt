package com.cho.dietapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cho.dietapp.ui.common.DietAppScreen
import com.cho.dietapp.ui.main.MainNavigation
import com.cho.dietapp.ui.main.screens.DietScreen
import com.cho.dietapp.ui.main.screens.HomeScreen
import com.cho.dietapp.ui.main.screens.ProfileScreen
import com.cho.dietapp.ui.theme.DietAppTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel = MainViewModel()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.loadExercise()

        enableEdgeToEdge()
        setContent {
            DietAppTheme {

                var currentScreen by remember {
                    mutableStateOf(DietAppScreen.Home)
                }

                Column (modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                ) {

                    Box (modifier = Modifier
                        .padding(bottom = 60.dp)
                        .fillMaxSize()
                    ){

                        when(currentScreen) {
                            DietAppScreen.Diet -> DietScreen(mainViewModel = mainViewModel)
                            DietAppScreen.Home -> HomeScreen(mainViewModel = mainViewModel)
                            DietAppScreen.Profile -> ProfileScreen(mainViewModel = mainViewModel)

                        }

                    }

                }

                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    MainNavigation(currentScreen = currentScreen,
                        onTabSelected ={
                        currentScreen = it
                    } )
                        

                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        mainViewModel.loadExercise()
    }

}
