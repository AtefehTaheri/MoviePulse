package ir.atefehtaheri.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.homescreen.Uistate.NowPlayingPagerState
import ir.atefehtaheri.homescreen.Uistate.TopRatedMoviePagerState
import ir.atefehtaheri.homescreen.Uistate.TopRatedTvShowPagerState
import ir.atefehtaheri.homescreen.Uistate.TvAiringPagerState
import ir.atefehtaheri.homescreen.Uistate.UpcomingPagerState
import ir.atefehtaheri.nowplaying.repository.NowPlayingRepository
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel
import ir.atefehtaheri.topratedmovie.repository.TopRatedMovieRepository
import ir.atefehtaheri.topratedtvshow.repository.TopRatedTvShowRepository
import ir.atefehtaheri.topratedmovie.repository.models.TopRatedMovieListDataModel
import ir.atefehtaheri.topratedtvshow.repository.models.TopRatedTvShowListDataModel
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
        MutableStateFlow(NowPlayingPagerState(NowPlayingListDataModel()))
    val nowplayingMovie = _nowplayingMovie.asStateFlow()


    private val _UpcomingPager =
        MutableStateFlow(UpcomingPagerState(UpcomingListDataModel()))
    val UpcomingPager = _UpcomingPager.asStateFlow()

    private val _tvShowAiring =
        MutableStateFlow(TvAiringPagerState(TvAiringListDataModel()))
    val tvShowAiring = _tvShowAiring.asStateFlow()


    private val _topRatedMovie =
        MutableStateFlow(TopRatedMoviePagerState(TopRatedMovieListDataModel()))
    val topRatedMovie = _topRatedMovie.asStateFlow()


    private val _topRatedTvShow =
        MutableStateFlow(
            TopRatedTvShowPagerState(
                TopRatedTvShowListDataModel()
            )
        )
    val topRatedTvShow = _topRatedTvShow.asStateFlow()


    private val _errorState =
        MutableStateFlow("")
    val errorState = _errorState.asStateFlow()


    init {
        getNowPlayingMovie()
        getUpcomingMovie()
        getTvAiringMovie()
        getTopRatedMovie()
        getTopRatedTvShow()

    }


    private fun getNowPlayingMovie() {
        viewModelScope.launch {

            _nowplayingMovie.update {
                it.copy(loading = true)
            }
            val response = nowPlayingRepository.getNowPlayingPager()
            when (response) {
                is ResultStatus.Failure ->
                    updateError(response.exception_message)


                is ResultStatus.Success -> {
                    _nowplayingMovie.update {
                        NowPlayingPagerState(
                            NowPlayingListDataModel(response.data?.nowplaying),
                            false
                        )
                    }
                }
            }
        }
    }


    private fun getUpcomingMovie() {
        viewModelScope.launch {
            _UpcomingPager.update {
                it.copy(loading = true)
            }
            val response = upcomingListRepository.getUpcomingPager()
            when (response) {
                is ResultStatus.Failure ->
                updateError(response.exception_message)

                is ResultStatus.Success -> {
                    _UpcomingPager.update {
                        UpcomingPagerState(
                            UpcomingListDataModel(response.data?.upcominglist),
                            false
                        )
                    }
                }
            }
        }
    }

    private fun getTvAiringMovie() {
        viewModelScope.launch {
            _tvShowAiring.update {
                it.copy(loading = true)
            }

            val response = tvAiringRepository.getTvAiringPager()
            when (response) {
                is ResultStatus.Failure -> updateError(response.exception_message)

                is ResultStatus.Success -> {
                    _tvShowAiring.update {
                        TvAiringPagerState(TvAiringListDataModel(response.data?.airinglist), false)
                    }
                }

            }
        }
    }

    private fun getTopRatedMovie() {
        viewModelScope.launch {
            _topRatedMovie.update {
                it.copy(loading = true)
            }

            val response = topRatedMovieRepository.getTopRatedMoviePager()
            when (response) {
                is ResultStatus.Failure ->
                    updateError(response.exception_message)


                is ResultStatus.Success -> {
                    _topRatedMovie.update {
                        TopRatedMoviePagerState(
                            TopRatedMovieListDataModel(response.data?.topratedmovielist),
                            false
                        )
                    }
                }
            }
        }
    }


    private fun getTopRatedTvShow() {
        viewModelScope.launch {
            _topRatedTvShow.update {
                it.copy(loading = true)
            }


            val response = topRatedTvShowRepository.getTopRatedTvShowPager()
            when (response) {
                is ResultStatus.Failure ->
                    updateError(response.exception_message)

                is ResultStatus.Success -> {
                    _topRatedTvShow.update {
                        TopRatedTvShowPagerState(
                            TopRatedTvShowListDataModel(response.data?.topratedtvshowlist),
                            false
                        )
                    }
                }
            }
        }
    }


    private fun updateError(error: String) {
        if (_errorState.value == "") {
            _errorState.update {
                error
            }
        }
    }

    private fun deleteError() {
        _errorState.update {
            ""
        }
    }

}

