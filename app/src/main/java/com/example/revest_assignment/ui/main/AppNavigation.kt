package com.example.revest_assignment.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.revest_assignment.ui.main.AppDestinations.PRODUCT_DETAIL_ID_KEY
import com.example.revest_assignment.ui.theme.Revest_AssignmentTheme
import com.example.revest_assignment.viewmodel.ProductsViewModel

private object AppDestinations {
    const val PRODUCTS_ROUTE = "products"
    const val PRODUCT_DETAIL_ROUTE = "product"
    const val PRODUCT_DETAIL_ID_KEY = "productId"
}
@Composable
fun AppNavigation(
    startDestination: String = AppDestinations.PRODUCTS_ROUTE, viewModel: ProductsViewModel

) {
    val navController = rememberNavController()
    val actions = remember(navController) { AppActions(navController) }

    NavHost(
        navController = navController,
        startDestination = "products"
    ) {
        composable(
            AppDestinations.PRODUCTS_ROUTE
        ) {
            Revest_AssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AllProducts(selectedProduct = actions.selectedProduct, viewModel)
                }
            }
        }
        composable(
            "${AppDestinations.PRODUCT_DETAIL_ROUTE}/{$PRODUCT_DETAIL_ID_KEY}",
            arguments = listOf(
                navArgument(PRODUCT_DETAIL_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            ProductDetails(
                productId = arguments.getInt(PRODUCT_DETAIL_ID_KEY),
                navigateUp = actions.navigateUp,viewModel
            )
        }
    }
}
//
private class AppActions(
    navController: NavHostController
) {
    val selectedProduct: (Int) -> Unit = { productId: Int ->
        navController.navigate("${AppDestinations.PRODUCT_DETAIL_ROUTE}/$productId")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}