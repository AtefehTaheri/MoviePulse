package ir.atefehtaheri.upcominglist

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn

//import androidx.paging.PagingData
//import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.upcominglist.Uistate.UpcomingPagerState
import ir.atefehtaheri.upcominglist.repository.UpcomingListRepository
import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel
import ir.atefehtaheri.upcominglist.repository.models.UpcomingMovieDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMovieViewModel @Inject constructor(
    private val upcomingListRepository: UpcomingListRepository
) : ViewModel() {


    private val _UpcomingPager =
        MutableStateFlow<UpcomingPagerState>(UpcomingPagerState(UpcomingListDataModel()))
    val UpcomingPager = _UpcomingPager.asStateFlow()

init {
    getPagerData()
}
    private fun getPagerData() {
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

    fun getUpcomingMovies(): Flow<PagingData<UpcomingMovieDataModel>> {
        return upcomingListRepository.getUpcomingMovies()
            .cachedIn(viewModelScope)

    }
}
