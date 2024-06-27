package ir.atefehtaheri.detailscreen


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.detailscreen.component.DetailMovie


@Composable
internal fun DetailScreen(
    type: Type,
    id:String,
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel()

) {
    when(type){
        Type.MOVIE ->{
            detailScreenViewModel.getDetailMovie(id)
            DetailMovie()
        }
        Type.TVSHOW -> {
            detailScreenViewModel.getDetailTvShow(id)
//            DetailTvShowScreen()
        }
    }
}
