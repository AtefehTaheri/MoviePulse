package ir.atefehtaheri.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.nowplaying.repository.NowPlayingRepository
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NowPlayingMovieViewModel @Inject constructor(
    private val nowPlayingRepository: NowPlayingRepository
) : ViewModel() {


init {
    getNowPlayingMovies()
}

    fun getNowPlayingMovies(): Flow<PagingData<NowPlayingDataModel>> {
        return nowPlayingRepository.getNowPlayingMovies()
            .cachedIn(viewModelScope)

    }
}
