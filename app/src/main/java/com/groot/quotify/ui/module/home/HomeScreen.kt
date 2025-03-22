package com.groot.quotify.ui.module.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.groot.quotify.R
import com.groot.quotify.base.QuoteApiModel
import com.groot.quotify.dto.QotdResponse
import com.groot.quotify.ui.theme.QuotifyTheme
import com.groot.quotify.ui.theme.platinum
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val apiViewModel = QuoteApiModel()

    val context = LocalContext.current
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
            CenterAlignedTopAppBar(
                title = {

                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(24.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_view_list_24),
                                contentDescription = "list"
                            )
                        }
                    )
                },
                actions = {

                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(24.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_theme_24),
                                contentDescription = "theme"
                            )
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(0.8f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp),
                        text = quoteOfTheDay.value?.quote?.body ?: "Loading...",
                        textAlign = TextAlign.Center
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .height(45.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {

                    Text(
                        text = "Tap for more",
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                GlobalScope.launch {
                                    quoteOfTheDay.value = apiViewModel.getQuoteOfTheDay()
                                }
                            },
                        color = platinum
                    )

                    Spacer(Modifier.weight(1f))

                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier.size(24.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_bookmark_border_24),
                                contentDescription = "save"
                            )
                        }
                    )


                    IconButton(
                        onClick = {
                            val sendIntent = Intent(Intent.ACTION_SEND)
                            sendIntent.putExtra(Intent.EXTRA_TEXT, quoteOfTheDay.value?.quote?.body ?: "Loading...")
                            sendIntent.setType("text/plain")
                            startActivity(context, sendIntent, null)
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(24.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_ios_share_24),
                                contentDescription = "share"
                            )
                        }
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    QuotifyTheme {
        HomeScreen(rememberNavController())
    }
}


