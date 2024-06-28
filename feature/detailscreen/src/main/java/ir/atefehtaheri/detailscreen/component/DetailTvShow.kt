package ir.atefehtaheri.detailscreen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.atefehtaheri.designsystem.ShowError
import ir.atefehtaheri.detailitem.repository.models.TvShowDetailDataModel
import ir.atefehtaheri.detailscreen.DetailScreenViewModel

@Composable
internal fun DetailTvShow(
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val errorstate by detailScreenViewModel.errorState.collectAsState()
    if (errorstate != "") {
        ShowError(errorstate)
    } else {
        DetailTvShowScreen()
    }


}

@Composable
private fun DetailTvShowScreen(
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel()

) {
    val detailTvShow by detailScreenViewModel.detailTvShow.collectAsState()

    when {
        detailTvShow.loading -> LoadingState()
        else -> ShowListState(detailTvShow.tvShowDetailDataModel!!)
    }
}

@Composable
private fun LoadingState(
    modifier: Modifier = Modifier
) {
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
private fun ShowListState(
    tvShowDetailDataModel: TvShowDetailDataModel,
    modifier: Modifier = Modifier
) {


    BoxWithConstraints {
        val screenHeight = maxHeight
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .verticalScroll(state = scrollState)
        ) {

            HeaderScreen(
                tvShowDetailDataModel!!.poster_path,
                tvShowDetailDataModel!!.name,
                tvShowDetailDataModel!!.status,
                tvShowDetailDataModel!!.images
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Icon(
                        Icons.Filled.DateRange,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = tvShowDetailDataModel!!.first_air_date.split("-")[0],
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                Divider(
                    color = Color.White, modifier = Modifier
                        .height(20.dp)
                        .width(2.dp)
                )


                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = String.format("%.1f", tvShowDetailDataModel!!.vote_average),
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                Divider(
                    color = Color.White, modifier = Modifier
                        .height(20.dp)
                        .width(2.dp)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        Icons.Rounded.Timer,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = "${tvShowDetailDataModel!!.episode_run_time.firstOrNull() ?: "-"} minutes",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        textAlign = TextAlign.Center
                    )

                }
            }
            Column(modifier = Modifier.height(screenHeight)) {

                var selectedTabIndex by remember { mutableIntStateOf(0) }
                val pagerState = rememberPagerState { InformationTvTabs.entries.size }

                LaunchedEffect(key1 = selectedTabIndex) {
                    pagerState.animateScrollToPage(selectedTabIndex)
                }

                LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
                    if (!pagerState.isScrollInProgress)
                        selectedTabIndex = pagerState.currentPage
                }


                TabRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp, horizontal = 8.dp)
                        .clip(RoundedCornerShape(90)),
                    contentColor = MaterialTheme.colorScheme.tertiaryContainer,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    selectedTabIndex = selectedTabIndex,
                    indicator = { tabPositions: List<TabPosition> ->
                        Box {}
                    }
                ) {
                    InformationTvTabs.entries.forEachIndexed { index, currentTab ->
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
                            }
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxHeight()
                        .nestedScroll(remember {
                            object : NestedScrollConnection {
                                override fun onPreScroll(
                                    available: Offset,
                                    source: NestedScrollSource
                                ): Offset {
                                    return if (available.y > 0) Offset.Zero else Offset(
                                        x = 0f,
                                        y = -scrollState.dispatchRawDelta(-available.y)
                                    )
                                }
                            }
                        })
                ) { page: Int ->
                    when (page) {
                        0 -> InformationTvScreen(tvShowDetailDataModel)
                        1 -> CreditsScreen(tvShowDetailDataModel.credits)
                        2 -> SeasonsScreen(tvShowDetailDataModel.seasons)
                    }
                }
            }
        }
    }
}


enum class InformationTvTabs(val text: String) {
    About("About"),
    Credits("Credits"),
    Seasons("Seasons")
}