package com.hisham.githubviewer.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val DarkColorScheme = darkColorScheme(
    primary = LightPurple,
    secondary = LightPurpleGrey,
    tertiary = LightPink,
)

private val LightColorScheme = lightColorScheme(
    primary = DarkPurple,
    onPrimary = Color.DarkGray,
    onSecondary = Color.DarkGray,
    onBackground = Color.DarkGray,
    onPrimaryContainer = Color.DarkGray,
    secondary = DarkPurpleGrey,
    tertiary = DarkPink,
    background = Color.White,
    surface = Color.White,
)


@Composable
fun HishamGithubViewerTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    ConfigureSystemBars(systemUiController, darkTheme)

    val colorScheme = provideColorScheme(darkTheme, dynamicColor)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

@Composable
private fun ConfigureSystemBars(systemUiController: SystemUiController, darkTheme: Boolean) {
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.White,
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = StatusBarBlue,
            darkIcons = false,
        )
        systemUiController.setNavigationBarColor(Color.Black)
    }
}

@Composable
private fun provideColorScheme(darkTheme: Boolean, dynamicColor: Boolean): ColorScheme {
    return when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
}
