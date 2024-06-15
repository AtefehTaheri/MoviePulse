package ir.atefehtaheri.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.homescreen.Uistate.NowPlayingPagerState
import ir.atefehtaheri.homescreen.Uistate.UpcomingPagerState
import ir.atefehtaheri.nowplaying.repository.NowPlayingRepository
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel
import ir.atefehtaheri.upcominglist.repository.UpcomingListRepository
import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val nowPlayingRepository: NowPlayingRepository,
    private val upcomingListRepository: UpcomingListRepository
) : ViewModel() {


    private val _nowplayingMovie =
        MutableStateFlow<NowPlayingPagerState>(NowPlayingPagerState(NowPlayingListDataModel()))
    val nowplayingMovie = _nowplayingMovie.asStateFlow()


    private val _UpcomingPager =
        MutableStateFlow<UpcomingPagerState>(UpcomingPagerState(UpcomingListDataModel()))
    val UpcomingPager = _UpcomingPager.asStateFlow()

init {
    getNowPlayingMovie()
    getUpcomingMovie()
}


    private fun getNowPlayingMovie() {
        viewModelScope.launch {


            val response = nowPlayingRepository.getNowPlayingPager()
            when (response) {
                is ResultStatus.Failure -> _nowplayingMovie.update {
                    it.copy(error = response.exception_message)
                }
                is ResultStatus.Success -> {
                    _nowplayingMovie.update {
                        NowPlayingPagerState(NowPlayingListDataModel(response.data?.nowplaying),null)
                    }
                }
            }
        }
    }


    private fun getUpcomingMovie() {
        viewModelScope.launch {

            val response = upcomingListRepository.getUpcomingPager()
            when (response) {
                is ResultStatus.Failure -> _UpcomingPager.update {
                    it.copy(error = response.exception_message)
                }
                is ResultStatus.Success -> {
                    _UpcomingPager.update {
                        UpcomingPagerState(UpcomingListDataModel(response.data?.upcominglist),null)
                    }
                }
            }
        }
    }


}
