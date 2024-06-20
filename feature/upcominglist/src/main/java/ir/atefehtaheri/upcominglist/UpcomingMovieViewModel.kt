package ir.atefehtaheri.upcominglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.upcominglist.repository.UpcomingListRepository
import ir.atefehtaheri.upcominglist.repository.models.UpcomingMovieDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UpcomingMovieViewModel @Inject constructor(
    private val upcomingListRepository: UpcomingListRepository
) : ViewModel() {


    init {
        getUpcomingMovies()
    }

    fun getUpcomingMovies(): Flow<PagingData<UpcomingMovieDataModel>> {
        return upcomingListRepository.getUpcomingMovies()
            .cachedIn(viewModelScope)

    }
}
