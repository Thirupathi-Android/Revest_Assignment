package com.example.revest_assignment.ui.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.revest_assignment.R
import com.example.revest_assignment.model.Product
import com.example.revest_assignment.viewmodel.ProductsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetails(productId: Int, navigateUp: () -> Unit, viewModel: ProductsViewModel) {
    LaunchedEffect(viewModel) {
        viewModel.getProductDetails(productId)
    }
    val myDataState = viewModel.product
    AnimatedVisibility(
        visible = true,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            initialHeight = { 0 }
        )
    ) {
        SetProductDetails(myDataState, navigateUp)
    }
}
@Composable
private fun SetProductDetails(

product: Product,
navigateUp: () -> Unit
) {
    androidx.compose.material.Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                item { ProductHeader(product.thumbnail, navigateUp) }
                item { ProductDetailBody(product) }
                item { ProductSampleImages(product) }
            }
        }
    }
}

@Composable
fun ProductSampleImages(product: Product) {
    val scrollState = rememberLazyListState()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        state = scrollState,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(product.images) { productImages ->
            ProductSampleRowItems(productImages)
        }
    }
}

@Composable
private fun ProductSampleRowItems(
    imagesItems: String
) {
    Card(
        Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize()
                .padding(10.dp),

            verticalArrangement = Arrangement.Center
        ) {

            AnimatedVisibility(
                visible = true
            ) {
                Image(
                    painter = rememberImagePainter(data = imagesItems,builder = {
                        crossfade(true) // Drawable to display on error
                        scale(Scale.FILL) // Scale type for the image
                    }),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ProductHeader(
    productImage: String,
    navigateUp: () -> Unit
) {
    Box {
        Image(
            painter = rememberImagePainter(data = productImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.Black
        ) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.app_name)
                )
            }
        }
    }
}

@Composable
private fun ProductDetailBody(
    cat: Product
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp)
            .animateContentSize()
    ) {
        Column {
            Row {

                Box(
                    Modifier.weight(8.5f)
                ) {
                    Column {
                        Text(
                            text = cat.title,
                            modifier = Modifier.padding(top = 4.dp, start = 16.dp),
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = cat.description,
                            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp),
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = "Rating : ${cat.rating}",
                            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp),
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = "Category : ${cat.category}",
                            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp),
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = "Price : ${cat.price}",
                            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp),
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onSurface
                        )

                    }
                }

            }
        }
    }
}

