package ir.atefehtaheri.topratedmovie

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.designsystem.ShowError
import ir.atefehtaheri.toprated.R
import ir.atefehtaheri.topratedmovie.model.asTopRatedItem
import ir.atefehtaheri.topratedmovie.repository.models.TopRatedMovieDataModel
import ir.atefehtaheri.topratedtvshow.repository.models.TopRatedTvShowDataModel


@Composable
internal fun TopRatedListRoute(
    onItemClick: (Type, String, NavOptions?) -> Unit,
    modifier: Modifier = Modifier,
    topRatedListViewModel: TopRatedListViewModel = hiltViewModel()
) {
    val movies = topRatedListViewModel.topRatedMovies.collectAsLazyPagingItems()
    val tvshow = topRatedListViewModel.topRatedTvshow.collectAsLazyPagingItems()
    TopRatedListScreen(movies, tvshow, onItemClick)
}

@Composable
private fun TopRatedListScreen(
    movies: LazyPagingItems<TopRatedMovieDataModel>,
    tvshow: LazyPagingItems<TopRatedTvShowDataModel>,
    onItemClick: (Type, String, NavOptions?) -> Unit

) {

    when {
        movies.loadState.refresh is LoadState.Error -> ShowError(
            (movies.loadState.refresh as LoadState.Error).error.message ?: ""
        )

        tvshow.loadState.refresh is LoadState.Error -> ShowError(
            (tvshow.loadState.refresh as LoadState.Error).error.message ?: ""
        )

        movies.loadState.refresh is LoadState.Loading -> LoadingState()
        tvshow.loadState.refresh is LoadState.Loading -> LoadingState()

        else -> ShowListScreen(movies, tvshow, onItemClick)
    }
}

@Composable
private fun LoadingState() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {

        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondaryContainer,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowListScreen(
    movies: LazyPagingItems<TopRatedMovieDataModel>,
    tvshow: LazyPagingItems<TopRatedTvShowDataModel>,
    onItemClick: (Type, String, NavOptions?) -> Unit

) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { InformationTabs.entries.size }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {


        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .height(1000.dp)

        ) { index ->
            when (index) {
                0 -> PageContent_Movie(movies, onItemClick)
                1 -> PageContent_Tvshow(tvshow, onItemClick)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {

            TabRow(selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 15.dp)
                    .clip(RoundedCornerShape(90)),
                indicator = { tabPositions: List<TabPosition> ->
                    Box {}
                }
            ) {
                InformationTabs.entries.forEachIndexed { index, currentTab ->
                    Tab(
                        modifier = if (selectedTabIndex == index) Modifier
                            .clip(RoundedCornerShape(50))
                            .background(
                                MaterialTheme.colorScheme.secondaryContainer
                            )
                        else Modifier
                            .clip(RoundedCornerShape(50))
                            .background(
                                MaterialTheme.colorScheme.tertiaryContainer
                            ),


                        selected = selectedTabIndex == index,
                        selectedContentColor = MaterialTheme.colorScheme.primaryContainer,
                        unselectedContentColor = MaterialTheme.colorScheme.outline,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(
                                text = currentTab.text,
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                    )
                }
            }
        }
    }
}


enum class InformationTabs(val text: String) {
    Movie("Movie"),
    TvShow("TvShow")
}

@Composable
fun PageContent_Movie(
    movies: LazyPagingItems<TopRatedMovieDataModel>,
    onItemClick: (Type, String, NavOptions?) -> Unit
) {
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        LazyColumn(
            modifier = Modifier, state = listState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Spacer(modifier = Modifier.height(70.dp))
            }



            items(
                count = movies.itemCount,
            ) { index ->
                val item = movies[index]

                if (item != null) {
                    TopRatedItem(
                        item.asTopRatedItem(),
                        onItemClick
                    )
                }
            }
            item {
                if (movies.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }


}}


@Composable
fun PageContent_Tvshow(
    tvshow: LazyPagingItems<TopRatedTvShowDataModel>,
    onItemClick: (Type, String, NavOptions?) -> Unit
) {
    val listState = rememberLazyListState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                LazyColumn(
                    modifier = Modifier, state = listState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Spacer(modifier = Modifier.height(70.dp))
                    }
                    items(
                        count = tvshow.itemCount,
                    ) { index ->
                        val item = tvshow[index]

                        if (item != null) {
                            TopRatedItem(
                                item.asTopRatedItem(),
                                onItemClick
                            )
                        }
                    }
                    item {
                        if (tvshow.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }

