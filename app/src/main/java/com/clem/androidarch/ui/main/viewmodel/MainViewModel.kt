package com.clem.androidarch.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clem.androidarch.data.model.ArticleData
import com.clem.androidarch.data.repository.DataRepository
import com.clem.arch_core.ui.BaseViewModel
import com.clem.arch_core.ui.UIState
import com.clem.arch_core.utils.yes

class MainViewModel(
    private val dataRepository: DataRepository
) : BaseViewModel() {

    private val _isShowRepositoryView = MutableLiveData<Boolean>()
    val isShowRepositoryView: LiveData<Boolean> = _isShowRepositoryView

    private val _articleData = MutableLiveData<ArticleData>()
    val articleData: LiveData<ArticleData> = _articleData

    fun getArticle(page: String) =
        launch(
            uiState = UIState(isShowLoadingView = true, isShowErrorView = true, isShowErrorToast = true),
            block = {
                dataRepository.getDataRepository(page)
            },
            success = {
                it.data.datas.isNotEmpty().yes {
                    _articleData.value = it
                    _isShowRepositoryView.value = true
                }
            }
        )

    interface Handlers {
        fun onTextClick(view: View)
    }
}