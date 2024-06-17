package ir.atefehtaheri.homescreen.components

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import ir.atefehtaheri.homescreen.HomeScreenViewModel
import ir.atefehtaheri.homescreen.R
import ir.atefehtaheri.homescreen.Uistate.UpcomingPagerState
import ir.atefehtaheri.homescreen.shimmerEffect
import ir.atefehtaheri.upcominglist.repository.models.UpcomingMovieDataModel


@Composable
internal fun UpcomingPager(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by homeScreenViewModel.UpcomingPager.collectAsState()
    UpcomingPagerScreen(state)
}

@Composable
private fun UpcomingPagerScreen(state: UpcomingPagerState) {

    when {
        state.loading -> LoadingState()
        else -> ShowListState(state.upcomingListDataModel.upcominglist)
    }

}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .shimmerEffect()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowListState(
    upcominglist: List<UpcomingMovieDataModel>?,
    modifier: Modifier = Modifier
    ) {

    upcominglist?.let {

        val pagerState =
            rememberPagerState(pageCount = { upcominglist!!.size })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            ElevatedCard(
                modifier = modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
            ) {


                HorizontalPager(
                    modifier = Modifier,
                    state = pagerState
                ) { page ->
                    val image = upcominglist!![page].backdrop_path
                    val baseurl = "https://image.tmdb.org/t/p/w500"
                    Box {


                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(Uri.parse(baseurl + image) ?: "")

                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.placeholder),
                            error = painterResource(R.drawable.placeholder),
                            fallback = painterResource(R.drawable.placeholder),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f))
                                .padding(10.dp),
                            textAlign = TextAlign.Center,
                            text = upcominglist!![page].title,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                    }
                }

            }
            DotsIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                dotCount = upcominglist!!.size,
                type = ShiftIndicatorType(
                    dotsGraphic = DotGraphic(
                        12.dp,
                        borderWidth = 0.dp,
                        borderColor = Color.Black,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    shiftSizeFactor = 3f
                ),
                dotSpacing = 5.dp,
                pagerState = pagerState
            )

        }

    }


}

