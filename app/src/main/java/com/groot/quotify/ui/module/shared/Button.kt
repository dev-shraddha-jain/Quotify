package com.groot.quotify.ui.module.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonFlatView(buttonLabel: String, function: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(25.dp),
        onClick = function,
        content = {
            Text(text = buttonLabel)
        }
    )
}