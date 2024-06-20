package ir.atefehtaheri.homescreen.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.designsystem.shimmerEffect
import ir.atefehtaheri.homescreen.R
import ir.atefehtaheri.homescreen.Uistate.movieItem


@Composable
fun PagerItem(
    movieItem : movieItem?,
    loading: Boolean = true,
    onItemClick:(Type, String, NavOptions?) -> Unit ={ a,b,c->}
) {
    ElevatedCard(
        modifier = Modifier
            .width(130.dp).clickable {
                movieItem?.let {
                onItemClick(movieItem.type,movieItem?.id.toString(),null)
                }
                                     },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
    ) {
        if (loading) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .shimmerEffect())
            
        } else {
            val baseurl = "https://image.tmdb.org/t/p/w500"

            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Uri.parse(baseurl + movieItem!!.imgurl))
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    fallback = painterResource(R.drawable.placeholder),
                    contentDescription = "",
                    modifier = Modifier

                        .height(180.dp),
                    contentScale = ContentScale.FillBounds
                )

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f))
                    .padding(3.dp)
                    .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.secondaryContainer
                    )

                    Text(text = String.format("%.1f", movieItem!!.vote_average),
                        color = MaterialTheme.colorScheme.secondaryContainer)
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = movieItem!!.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
        }
    }
}