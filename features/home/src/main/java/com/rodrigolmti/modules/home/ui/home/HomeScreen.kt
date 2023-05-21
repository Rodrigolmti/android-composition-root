package com.rodrigolmti.modules.home.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigolmti.modules.ui_kit.CustomLoading
import org.koin.androidx.compose.koinViewModel

interface IHomeDelegate {
    fun onDrinkSelected(id: String)
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    delegate: IHomeDelegate,
) {
    val viewState by viewModel.viewState.collectAsState()

    when (viewState) {
        HomeViewState.Error -> BuildError(viewModel)
        HomeViewState.Loading -> CustomLoading()
        is HomeViewState.Success -> BuildContent(viewState, delegate)
    }
}

@Composable
private fun BuildContent(
    viewState: HomeViewState,
    delegate: IHomeDelegate
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val state = viewState as HomeViewState.Success

            items(state.drinks.size) { index ->

                val item = state.drinks[index]

                BuildDrinkItem(item.strDrinkThumb, item.strDrink) {
                    delegate.onDrinkSelected(item.idDrink)
                }
            }
        }
    }
}

@Composable
private fun BuildError(viewModel: HomeViewModel) {
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
            onClick = { viewModel.getDrinks() },
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun BuildDrinkItem(
    thumb: String,
    name: String,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(thumb)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(85.dp)
                    .padding(end = 16.dp)
            )

            Text(
                text = name,
                color = Color.White,
                textAlign = TextAlign.Left,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewDrinkItem() {
    BuildDrinkItem(
        thumb = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
        name = "Mojito",
        onItemClick = {}
    )
}