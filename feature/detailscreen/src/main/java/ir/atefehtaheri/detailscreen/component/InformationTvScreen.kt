package ir.atefehtaheri.detailscreen.component


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.atefehtaheri.common.BASE_URL
import ir.atefehtaheri.designsystem.shimmerEffect
import ir.atefehtaheri.detailitem.repository.models.Episode
import ir.atefehtaheri.detailitem.repository.models.TvShowDetailDataModel
import ir.atefehtaheri.detailscreen.R

@Composable
fun InformationTvScreen(
    tvShowDetailDataModel: TvShowDetailDataModel
//    overview: String?,
//    genres: List<String>?,
//    first_air_date:String,
//    last_air_date:String,
//    number_of_seasons:Int,
//    number_of_episodes:Int
) {
    var expandedState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (tvShowDetailDataModel.overview == "") {
            Text(
                text = "There is no description for this item",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        } else {
            Text(
                text = tvShowDetailDataModel.overview,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                maxLines = if (expandedState) 9 else 3,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.clickable { expandedState = !expandedState },
                text = if (!expandedState) "Read more" else "Read less",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondaryContainer
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Genres",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondaryContainer
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            items(tvShowDetailDataModel.genres ?: emptyList()) {
                SuggestionChip(
                    onClick = { },
                    label = { Text("${it}") },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer, // Custom background color
                        labelColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ),
                )
            }
        }

        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "first air date: ${tvShowDetailDataModel.first_air_date}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "last air date: ${tvShowDetailDataModel.last_air_date}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "number of seasons:  ${tvShowDetailDataModel.number_of_seasons}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "number of episodes:  ${tvShowDetailDataModel.number_of_episodes}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )

        }

        Text(
            text = "last episode to air",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondaryContainer
        )
        Spacer(modifier = Modifier.height(5.dp))

        episodeitem(tvShowDetailDataModel.last_episode_to_air)

        Spacer(modifier = Modifier.height(10.dp))
        tvShowDetailDataModel.next_episode_to_air?.let {
            Text(
                text = "next episode to air",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondaryContainer
            )
            Spacer(modifier = Modifier.height(5.dp))

            episodeitem(it)
        }


    }

}


@Composable
fun episodeitem(episode: Episode) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Uri.parse(BASE_URL + episode.still_path) ?: "")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    fallback = painterResource(R.drawable.placeholder),
                    contentDescription = "",

                    contentScale = ContentScale.FillBounds
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f))
                        .padding(3.dp)
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.secondaryContainer
                    )

                    Text(
                        text = String.format("%.1f", episode.vote_average),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = episode.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "episode number: ${episode.episode_number}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "season number: ${episode.season_number}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }

}

