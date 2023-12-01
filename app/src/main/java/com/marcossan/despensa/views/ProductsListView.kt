package com.marcossan.despensa.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.marcossan.despensa.viewmodels.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsListView(navController: NavController, viewModel: ProductsViewModel) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Inicio View", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) {
        ContentInicioView(it, navController, viewModel)
    }
}

@Composable
fun ContentInicioView(
    it: PaddingValues,
    navController: NavController,
    viewModel: ProductsViewModel
) {
    val state = viewModel.state

    Column(modifier = Modifier.padding(it)) {
        LazyColumn {
            items(state.products) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = it.code)
                        Text(text = it.name)
                        IconButton(onClick = {
                            navController.navigate("edit/${it.id}/${it.code}/${it.name}")
                        }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = {
                            viewModel.deleteProduct(it)
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }
    }
}