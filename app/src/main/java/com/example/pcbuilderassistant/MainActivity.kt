package com.example.pcbuilderassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.pcbuilderassistant.ui.theme.PcBuilderTheme
import com.example.pcbuilderassistant.ui.screens.PreferenceScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PcBuilderTheme {
                Surface {
                    PreferenceScreen()
                }
            }
        }
    }
}
