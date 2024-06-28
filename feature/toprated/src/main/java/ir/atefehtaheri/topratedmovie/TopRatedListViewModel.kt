package ir.atefehtaheri.topratedmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.topratedmovie.repository.TopRatedMovieRepository
import ir.atefehtaheri.topratedtvshow.repository.TopRatedTvShowRepository
import ir.atefehtaheri.topratedmovie.repository.models.TopRatedMovieDataModel
import ir.atefehtaheri.topratedtvshow.repository.models.TopRatedTvShowDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopRatedListViewModel @Inject constructor(
    private val topRatedMovieRepository: TopRatedMovieRepository,
    private val topRatedTvShowRepository: TopRatedTvShowRepository

) : ViewModel() {



    val topRatedMovies: Flow<PagingData<TopRatedMovieDataModel>> =
         topRatedMovieRepository.getTopRatedMovie()
            .cachedIn(viewModelScope)



    val topRatedTvshow: Flow<PagingData<TopRatedTvShowDataModel>> =
         topRatedTvShowRepository.getTopRatedTvShow()
            .cachedIn(viewModelScope)


}
