package com.cho.dietapp.ui.intro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cho.dietapp.MainActivity
import com.cho.dietapp.ui.intro.screens.LoginScreen
import com.cho.dietapp.ui.theme.DietAppTheme

class IntroActivity : ComponentActivity() {

    private val introViewModel : IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            
            DietAppTheme {

                val context = LocalContext.current

                val user by introViewModel.authState.collectAsStateWithLifecycle()
                val error by introViewModel.errorState.collectAsStateWithLifecycle()

                Log.d("LoginInfo", "user : $user")


                if(user == null)
                {
                    //로그인 회원가입 X

                    LoginScreen(
                        onLogin = { email, password ->
                            introViewModel.signIn(email, password)
                        },
                        onSingUp = { email, password ->
                            introViewModel.signUp(email, password)
                        },
                        errorMessage = error
                    )
                }

                else {

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    finish()
                }
                
            }
        }
    }
    
}