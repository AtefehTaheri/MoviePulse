package ir.atefehtaheri.homescreen.components

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import ir.atefehtaheri.homescreen.HomeScreenViewModel
import ir.atefehtaheri.homescreen.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UpcomingPager(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {


    val state by homeScreenViewModel.UpcomingPager.collectAsState()
    state.upcomingListDataModel.upcominglist?.let {


        val pagerState =
            rememberPagerState(pageCount = { state.upcomingListDataModel.upcominglist!!.size })
        Column(
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
                    val image = state.upcomingListDataModel.upcominglist!![page].poster_path
                    val baseurl = "https://image.tmdb.org/t/p/w500"

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

                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            DotsIndicator(
                dotCount = state.upcomingListDataModel.upcominglist!!.size,
                type = ShiftIndicatorType(
                    dotsGraphic = DotGraphic(
                        12.dp,
                        borderWidth = 0.dp,
                        borderColor = Color.Black,
                        color = Color.Yellow,
                    ),
                    shiftSizeFactor = 3f
                ),
                dotSpacing = 5.dp,
                pagerState = pagerState
            )


        }


    }
}

