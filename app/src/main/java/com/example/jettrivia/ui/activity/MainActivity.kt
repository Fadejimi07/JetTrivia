package com.example.jettrivia.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.jettrivia.ui.theme.JetTriviaTheme
import com.example.jettrivia.ui.viewmodel.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTriviaTheme {

            }
        }
    }
}

@Composable
fun TriviaHome(viewModel: QuestionsViewModel) {
    
}