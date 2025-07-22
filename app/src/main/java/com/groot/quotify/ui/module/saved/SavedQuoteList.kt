package com.groot.quotify.ui.module.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.groot.quotify.R
import com.groot.quotify.navigation.Route


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedQuoteList(navController: NavHostController, isDarkTheme: MutableState<Boolean>) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Coming Soon", color = MaterialTheme.colorScheme.onSurface)
                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.homeScreen)
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(24.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.baseline_arrow_back),
                                contentDescription = "list",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                            )
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            isDarkTheme.value = !isDarkTheme.value
                        },
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(24.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_theme_24),
                                contentDescription = "theme",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                            )
                        })
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



            }
        }

    )
}