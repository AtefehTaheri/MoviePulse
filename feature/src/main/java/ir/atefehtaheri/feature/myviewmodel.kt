package ir.atefehtaheri.feature

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.commen.models.ResultStatus

import ir.atefehtaheri.upcominglist.repository.UpcomingListRepository
import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMovieViewModel @Inject constructor(
    private val upcomingListRepository: UpcomingListRepository
) : ViewModel() {
//
//
    private val _data = mutableStateOf<UpcomingListDataModel>(UpcomingListDataModel(null))
    val data: State<UpcomingListDataModel> = _data


    //    var x :UpcomingListDto?= null
    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {


            val response = upcomingListRepository.getUpcomingList()
            val x = when (response) {
                is ResultStatus.Failure -> response.exception_message
                is ResultStatus.Success -> {

                    _data.value = _data.value.copy(response.data?.upcominglist)
                    "sucess"
                }
            }
            Log.d("Test1", x)

        }}
//        fun getUpcomingMovies(): Flow<PagingData<UpcomingMovieDataModel>> {
//            return upcomingListRepository.getUpcomingMovies()
//                .cachedIn(viewModelScope)
//
//        }


////            try {
//                val response = RetrofitInstance.api.getData()
//            val x =when(response ){
//                is NetworkResponse.ApiError -> response.body.message
//                is NetworkResponse.NetworkError -> response.error.message
//                is NetworkResponse.Success -> {
//                    _data.value= _data.value.copy(page=response.body?.page)
//                    "sucess"
//                }
//                is NetworkResponse.UnknownError -> response.error.message
//            }

//            } catch (e: HttpException) {
//                Log.d("Test2",x?.page.toString())
//            } catch (e: Exception) {
//                Log.d("Test3",x?.page.toString())
//            }
    }
//    }
//}