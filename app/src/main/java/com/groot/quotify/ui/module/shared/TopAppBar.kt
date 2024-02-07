package com.groot.quotify.ui.module.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


// topBarTitle
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomTopAppBar(topBarTitle: String) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = topBarTitle)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}