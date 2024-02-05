package com.groot.quotify.ui.module.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.groot.quotify.base.QuoteApiModel
import com.groot.quotify.dto.QotdResponse

@Composable
fun HomeScreen(navController: NavHostController) {

    val apiViewModel = QuoteApiModel()

    val quoteOfTheDay = remember { mutableStateOf<QotdResponse?>(null) }

    LaunchedEffect(key1 = Unit) {
        val quoteDto = apiViewModel.getQuoteOfTheDay()
        quoteOfTheDay.value = quoteDto
        println(quoteDto?.quote?.body)
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(25.dp),
                text = quoteOfTheDay.value?.quote?.body ?: "Loading..."
            )

        }
    }

}