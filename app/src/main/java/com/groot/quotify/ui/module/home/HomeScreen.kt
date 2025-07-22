package com.groot.quotify.ui.module.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.groot.quotify.navigation.Route
import com.groot.quotify.ui.theme.QuotifyTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, isDarkTheme: MutableState<Boolean>) {

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
                            navController.navigate(Route.savedQuoteListScreen)
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(24.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_view_list_24),
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
                        }
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
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
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
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
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(Modifier.weight(1f))

                    IconButton(
                        onClick = {
                            val quoteText = quoteOfTheDay.value?.quote?.body
                            if (!quoteText.isNullOrEmpty()) {
                                val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("quote", quoteText)
                                clipboardManager.setPrimaryClip(clip)
                                // Optional: Show a Toast message
                                Toast.makeText(context, "Quote copied!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "No quote to copy", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.size(24.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_bookmark_border_24),
                                contentDescription = "save",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)

                            )
                        },
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
                                contentDescription = "share",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
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
    var isDarkTheme = remember { mutableStateOf(false) } // Or load from preferences
    QuotifyTheme(
        content = {
            HomeScreen(rememberNavController(), isDarkTheme)
        },
        isDarkTheme = isDarkTheme
    )
}


