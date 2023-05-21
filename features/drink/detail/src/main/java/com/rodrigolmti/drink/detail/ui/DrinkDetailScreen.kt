package com.rodrigolmti.drink.detail.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigolmti.modules.ui_kit.CustomLoading
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrinkDetailScreen(
    viewModel: DrinkDetailViewModel = koinViewModel<DrinkDetailViewModel>(),
    drinkId: String,
    onBack: () -> Unit = {},
) {
    FetchDrinkDetail(viewModel, drinkId)

    val viewState by viewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(text = "Drink Detail")
                })
        }
    ) {
        DrinkDetailContent(viewState = viewState) {
            viewModel.getDrinkById(drinkId)
        }
    }
}

@Composable
private fun FetchDrinkDetail(
    viewModel: DrinkDetailViewModel,
    drinkId: String
) {
    LaunchedEffect(Unit) { viewModel.getDrinkById(drinkId) }
}

@Composable
fun DrinkDetailContent(
    viewState: DrinkDetailState,
    onRetry: () -> Unit = {},
) {
    when (viewState) {
        DrinkDetailState.Loading -> CustomLoading()

        DrinkDetailState.Error -> BuildError(onRetry)
        is DrinkDetailState.Success -> Column(modifier = Modifier.fillMaxSize()) {

            val item = viewState.data

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.strDrinkThumb)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            )

            Text(
                text = item.strDrink,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Text(
                text = item.strInstructions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Text(
                text = item.strAlcoholic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Text(
                text = item.ingredients.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Text(
                text = item.measures.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun BuildError(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Something went wrong",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        )

        Button(
            onClick = onRetry,
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            Text(text = "Retry")
        }
    }
}