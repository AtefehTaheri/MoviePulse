package ir.atefehtaheri.detailscreen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.atefehtaheri.detailitem.repository.models.Credits

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreditsScreen(credits: Credits) {

    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        stickyHeader {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth() .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                ),
            ) {

                Text(
                    modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),
                    text = "Casts",
                    style = MaterialTheme.typography.titleMedium,
                    color =MaterialTheme.colorScheme.primaryContainer
                )
            }
        }
        items(credits.cast) {

            CreditsItem(it.profile_path,it.name,"")
        }

        stickyHeader {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth() .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                ),
            ) {

                Text(
                    modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),
                    text = "Crews",
                    style = MaterialTheme.typography.titleMedium,
                    color =MaterialTheme.colorScheme.primaryContainer
                )
            }
        }
        items(credits.crew) {
            CreditsItem(it.profile_path,it.name,it.job)
        }
    }


}