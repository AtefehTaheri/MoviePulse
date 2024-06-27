package ir.atefehtaheri.detailscreen.component

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import ir.atefehtaheri.detailscreen.R
import kotlin.math.min



@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HeaderScreen(
    poster_path:String?,
    title: String,
    status:String,
    backDrops:List<String>,
    modifier: Modifier = Modifier,
    countpage:Int = 5
) {

    val pagerState = rememberPagerState(pageCount = {min(backDrops.size,countpage)})
    val baseurl = "https://image.tmdb.org/t/p/w500"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
            HorizontalPager(
//                modifier = Modifier,
                state = pagerState
            ) { page ->
                val image = backDrops[page]

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Uri.parse(baseurl + image))
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    fallback = painterResource(R.drawable.placeholder),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    )
            )

            Row ( modifier = Modifier
                .height(180.dp)
                .padding(horizontal = 30.dp)
                .align(Alignment.BottomEnd)
               )
            {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Uri.parse(baseurl + poster_path))
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    fallback = painterResource(R.drawable.placeholder),
                    contentDescription = "",
                    modifier = Modifier
                        .width(130.dp)
                        .height(180.dp)

                    ,
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column (
                   modifier = Modifier
                       .fillMaxSize()
                       .weight(2f),
                ){
                   DotsIndicator(
                       modifier = Modifier
                           .weight(1f)
                           .padding(top = 20.dp),
                       dotCount = min(backDrops.size,countpage),
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
                   Column(modifier = Modifier
                       .fillMaxWidth()
                       .weight(1f))
                   {
                       Text(
                           text =title,
                           style = MaterialTheme.typography.titleMedium,
                           color = MaterialTheme.colorScheme.onTertiaryContainer,
                       )
                       Spacer(modifier = Modifier.height(10.dp))
                       Text(
                           text =status,
                           style = MaterialTheme.typography.titleSmall,
                           color = MaterialTheme.colorScheme.secondaryContainer,
                       )

                   }
               }
            }
    }
}





