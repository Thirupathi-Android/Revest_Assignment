package com.example.revest_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.revest_assignment.ui.main.AppNavigation
import com.example.revest_assignment.viewmodel.ProductsViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation("",viewModel)
        }
    }
}

