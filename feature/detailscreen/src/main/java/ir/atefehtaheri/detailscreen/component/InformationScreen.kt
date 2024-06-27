package ir.atefehtaheri.detailscreen.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun InformationScreen(overview: String?, genres: List<String>?) {
    var expandedState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = overview ?: "There is no description for this item",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            maxLines = if (expandedState) 9 else 3,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.clickable { expandedState = !expandedState },
            text = "Read more",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondaryContainer
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Genres",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondaryContainer
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            items(genres ?: emptyList()) {
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
    }

}

