package com.groot.quotify.ui.module.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.groot.quotify.R
import com.groot.quotify.base.QuoteApiModel
import com.groot.quotify.dto.QotdResponse
import com.groot.quotify.ui.module.shared.ButtonFlatView
import com.groot.quotify.ui.theme.Blue0
import com.groot.quotify.ui.theme.QuotifyTheme
import com.groot.quotify.ui.theme.gradient
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
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(gradient),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center
                )


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp)
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        contentColor = Color.Transparent
                    ),
                    border = CardDefaults.outlinedCardBorder(

                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(25.dp),
                            text = quoteOfTheDay.value?.quote?.body ?: "Loading...",
                            color = Blue0
                        )
                    }
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


@Preview
@Composable
fun HomeScreenPreview() {
    QuotifyTheme {
        HomeScreen(rememberNavController())
    }
}


