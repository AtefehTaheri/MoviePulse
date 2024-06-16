package ir.atefehtaheri.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.homescreen.Uistate.NowPlayingPagerState
import ir.atefehtaheri.homescreen.Uistate.TopRatedMoviePagerState
import ir.atefehtaheri.homescreen.Uistate.TopRatedTvShowPagerState
import ir.atefehtaheri.homescreen.Uistate.TvAiringPagerState
import ir.atefehtaheri.homescreen.Uistate.UpcomingPagerState
import ir.atefehtaheri.nowplaying.repository.NowPlayingRepository
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel
import ir.atefehtaheri.toprated.repository.TopRatedMovieRepository
import ir.atefehtaheri.toprated.repository.TopRatedTvShowRepository
import ir.atefehtaheri.toprated.repository.models.TopRatedMovieListDataModel
import ir.atefehtaheri.toprated.repository.models.TopRatedTvShowListDataModel
import ir.atefehtaheri.tvairing.repository.TvAiringRepository
import ir.atefehtaheri.tvairing.repository.models.TvAiringListDataModel
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
    private val upcomingListRepository: UpcomingListRepository,
    private val tvAiringRepository: TvAiringRepository,
    private val topRatedMovieRepository: TopRatedMovieRepository,
    private val topRatedTvShowRepository: TopRatedTvShowRepository,


    ) : ViewModel() {


    private val _nowplayingMovie =
        MutableStateFlow<NowPlayingPagerState>(NowPlayingPagerState(NowPlayingListDataModel()))
    val nowplayingMovie = _nowplayingMovie.asStateFlow()


    private val _UpcomingPager =
        MutableStateFlow<UpcomingPagerState>(UpcomingPagerState(UpcomingListDataModel()))
    val UpcomingPager = _UpcomingPager.asStateFlow()

    private val _tvShowAiring =
        MutableStateFlow<TvAiringPagerState>(TvAiringPagerState(TvAiringListDataModel()))
    val tvShowAiring = _tvShowAiring.asStateFlow()


    private val _topRatedMovie =
        MutableStateFlow<TopRatedMoviePagerState>(TopRatedMoviePagerState(TopRatedMovieListDataModel()))
    val topRatedMovie = _topRatedMovie.asStateFlow()



    private val _topRatedTvShow =
        MutableStateFlow<TopRatedTvShowPagerState>(TopRatedTvShowPagerState(
            TopRatedTvShowListDataModel()
        ))
    val topRatedTvShow = _topRatedTvShow.asStateFlow()

    init {
    getNowPlayingMovie()
    getUpcomingMovie()
    getTvAiringMovie()
    getTopRatedMovie()
        getTopRatedTvShow()

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

    private fun getTvAiringMovie() {
        viewModelScope.launch {


            val response = tvAiringRepository.getTvAiringPager()
            when (response) {
                is ResultStatus.Failure -> _tvShowAiring.update {
                    it.copy(error = response.exception_message)
                }
                is ResultStatus.Success -> {
                    _tvShowAiring.update {
                        TvAiringPagerState(TvAiringListDataModel(response.data?.airinglist),null)
                    }
                }
            }
        }
    }

    private fun getTopRatedMovie() {
        viewModelScope.launch {


            val response = topRatedMovieRepository.getTopRatedMoviePager()
            when (response) {
                is ResultStatus.Failure -> _topRatedMovie.update {
                    it.copy(error = response.exception_message)
                }
                is ResultStatus.Success -> {
                    _topRatedMovie.update {
                        TopRatedMoviePagerState(TopRatedMovieListDataModel(response.data?.topratedmovielist),null)
                    }
                }
            }
        }
    }


    private fun getTopRatedTvShow() {
        viewModelScope.launch {


            val response = topRatedTvShowRepository.getTopRatedTvShowPager()
            when (response) {
                is ResultStatus.Failure -> _topRatedTvShow.update {
                    it.copy(error = response.exception_message)
                }
                is ResultStatus.Success -> {
                    _topRatedTvShow.update {
                        TopRatedTvShowPagerState(TopRatedTvShowListDataModel(response.data?.topratedtvshowlist),null)
                    }
                }
            }
        }
    }
}
