package ir.atefehtaheri.homescreen.components

import android.net.Uri
import android.util.Log
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.atefehtaheri.homescreen.R
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingDataModel


@Composable
fun PagerItem(
    title:String ,
    imgurl:String?,
    onClick : ()->Unit={} ) {
    ElevatedCard(
        modifier = Modifier
            .width(130.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
    ){

        val baseurl = "https://image.tmdb.org/t/p/w500"
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Uri.parse(baseurl + imgurl) ?: "")
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
        Text(
            modifier = Modifier
                .fillMaxWidth().padding(10.dp),
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify,
        )
    }
}