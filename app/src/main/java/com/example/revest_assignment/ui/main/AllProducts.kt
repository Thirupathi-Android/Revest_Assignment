package com.example.revest_assignment.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.revest_assignment.viewmodel.ProductsViewModel

@Composable
fun AllProducts(selectedProduct: (Int) -> Unit, viewModel: ProductsViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = {
                    Text(
                        text = "Revest Sample Products",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.LightGray
                    )
                }
            )
        }
    ) { paddingValues ->
        ProductsListBody(selectedProduct, paddingValues,viewModel)
    }

}

@Composable
private fun ProductsListBody(
    selectedProduct: (Int) -> Unit,
    paddingValues: PaddingValues,
    viewModel: ProductsViewModel
) {
    val scrollState = rememberLazyListState()
    LaunchedEffect(viewModel) {
        viewModel.getProducts()

    }
    val myDataState = viewModel.products.value

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(16.dp)
            .padding(paddingValues)
            .fillMaxSize(),
        state = scrollState,
    ) {
        items(myDataState){ item ->
            ProductCard(item.id,item.title,item.description,item.thumbnail,selectedProduct)
        }
    }
}
