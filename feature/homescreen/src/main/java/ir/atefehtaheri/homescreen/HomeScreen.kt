package ir.atefehtaheri.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import ir.atefehtaheri.homescreen.components.NowPlayingList
import ir.atefehtaheri.homescreen.components.UpcomingPager

@Composable
fun HomeScreen (onClick :  (NavOptions?) -> Unit) {
val scrollstate = rememberScrollState()

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollstate)
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "Upcoming Movies",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )

            Text(
                modifier = Modifier.clickable{ onClick(null)},
                text = "See All",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
        }
        UpcomingPager()
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "Now Playing",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )

            Text(
                modifier = Modifier.clickable{ onClick(null)},
                text = "See All",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
        }

        NowPlayingList()

}}