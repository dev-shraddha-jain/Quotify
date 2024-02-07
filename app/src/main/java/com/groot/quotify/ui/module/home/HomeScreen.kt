package com.groot.quotify.ui.module.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.groot.quotify.base.QuoteApiModel
import com.groot.quotify.dto.QotdResponse
import com.groot.quotify.ui.module.shared.ButtonFlatView
import com.groot.quotify.ui.module.shared.CustomTopAppBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {

    val apiViewModel = QuoteApiModel()

    val quoteOfTheDay = remember { mutableStateOf<QotdResponse?>(null) }

    LaunchedEffect(key1 = Unit) {
        try {
            quoteOfTheDay.value = apiViewModel.getQuoteOfTheDay()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar("Hello!!")
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .padding(horizontal = 25.dp)
                        .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(25.dp),
                        text = quoteOfTheDay.value?.quote?.body ?: "Loading..."
                    )
                }

                ButtonFlatView("Re-Generate") {
                    GlobalScope.launch {
                        quoteOfTheDay.value = apiViewModel.getQuoteOfTheDay()
                    }
                }
            }
        }
    )
}


