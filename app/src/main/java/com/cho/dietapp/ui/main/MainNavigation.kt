package com.cho.dietapp.ui.main

import android.graphics.drawable.Icon
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cho.dietapp.R
import com.cho.dietapp.ui.common.DietAppScreen

@Composable
fun MainNavigation (
    currentScreen : DietAppScreen,
    onTabSelected : (DietAppScreen) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,



    ) {

        DietAppScreen.entries.forEach { screen ->

            val isSelected = currentScreen == screen

            val color by animateColorAsState(
                targetValue = if (isSelected) Color.White else Color.Gray,
                animationSpec = spring(), label = ""
            )

            val scale by animateFloatAsState(
                targetValue = if (isSelected) 1.2f else 1.0f,
                animationSpec = spring(), label = ""
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onTabSelected(screen) }
                    .scale(scale)
                    .padding(3.dp)
            ) {

                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = screen.name,
                    tint = color,
                    modifier = Modifier.size(36.dp)

                )

                Text(
                    text = screen.name,
                    color = color,
                    fontSize = 16.sp

                    )

            }
        }

    }
}


