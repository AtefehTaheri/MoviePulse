package ir.atefehtaheri.moviepulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.atefehtaheri.moviepulse.ui.theme.MoviePulseTheme
import ir.atefehtaheri.navigation.SetupNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MoviePulseTheme {
                navController = rememberNavController()
                SetupNavGraph(navController)
//                UpcomingPager()
        }}
    }
}
