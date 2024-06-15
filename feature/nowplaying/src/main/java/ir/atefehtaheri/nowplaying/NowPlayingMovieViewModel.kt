package ir.atefehtaheri.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.nowplaying.Uistate.NowPlayingPagerState
import ir.atefehtaheri.nowplaying.repository.NowPlayingRepository
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingDataModel
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMovieViewModel @Inject constructor(
    private val nowPlayingRepository: NowPlayingRepository
) : ViewModel() {


    private val _nowplayingPager =
        MutableStateFlow<NowPlayingPagerState>(NowPlayingPagerState(NowPlayingListDataModel()))
    val nowplayingPager = _nowplayingPager.asStateFlow()

init {
    getPagerData()
}
    private fun getPagerData() {
        viewModelScope.launch {


            val response = nowPlayingRepository.getNowPlayingPager()
            when (response) {
                is ResultStatus.Failure -> _nowplayingPager.update {
                    it.copy(error = response.exception_message)
                }
                is ResultStatus.Success -> {
                    _nowplayingPager.update {
                        NowPlayingPagerState(NowPlayingListDataModel(response.data?.nowplaying),null)
                    }
                }
            }

        }

    }

    fun getNowPlayingMovies(): Flow<PagingData<NowPlayingDataModel>> {
        return nowPlayingRepository.getNowPlayingMovies()
            .cachedIn(viewModelScope)

    }
}
