package ir.atefehtaheri.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.nowplaying.repository.NowPlayingRepository
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingDataModel
import ir.atefehtaheri.tvairing.repository.TvAiringRepository
import ir.atefehtaheri.tvairing.repository.models.TvAiringDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NowPlayingMovieViewModel @Inject constructor(
    private val nowPlayingRepository: NowPlayingRepository,
    private val tvAiringRepository: TvAiringRepository

) : ViewModel() {


init {
    getNowPlayingMovies()
    getTvShowAiring()
}

    fun getNowPlayingMovies(): Flow<PagingData<NowPlayingDataModel>> {
        return nowPlayingRepository.getNowPlayingMovies()
            .cachedIn(viewModelScope)

    }

    fun getTvShowAiring(): Flow<PagingData<TvAiringDataModel>> {
        return tvAiringRepository.getTvAiring()
            .cachedIn(viewModelScope)

    }
}
