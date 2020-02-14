package com.task.ui.component.news

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.IdlingResource
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.data.Resource
import com.task.data.models.db.Category
import com.task.data.remote.dto.CategoryRemote
import com.task.data.remote.dto.NewsItem
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.news.newsAdapter.NewsAdapter
import com.task.utils.*
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject



class NewsListActivity : BaseActivity() {
    @Inject
    lateinit var newsListViewModel: NewsListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val layoutId: Int
        get() = R.layout.home_activity

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun initializeViewModel() {
        newsListViewModel = viewModelFactory.create(NewsListViewModel::class.java)
        newsListViewModel.getNews()
        newsListViewModel?.categoryLiveDataIn?.let {
            observe(newsListViewModel.categoryLiveDataIn!!, ::handleNewsList)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ic_toolbar_refresh.setOnClickListener {
            newsListViewModel.getNews()
        }
        btn_search.setOnClickListener {
            if (!(et_search.text?.toString().isNullOrEmpty())) {
                pb_loading.visibility = VISIBLE
                newsListViewModel.onSearchClick(et_search.text?.toString()!!)
            }
        }
        val layoutManager = LinearLayoutManager(this)
        rv_news_list.layoutManager = layoutManager
        rv_news_list.setHasFixedSize(true)
       // newsListViewModel.getNews()

    }

    private fun bindListData(categoryModel:List<Category>) {
        if (!(categoryModel.isNullOrEmpty())) {
            val newsAdapter = NewsAdapter(newsListViewModel, categoryModel!!)
            rv_news_list.adapter = newsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
        EspressoIdlingResource.decrement()
    }

    private fun navigateToDetailsScreen(navigateEvent: Event<CategoryRemote>) {
        navigateEvent.getContentIfNotHandled()?.let {
            startActivity(intentFor<DetailsActivity>(Constants.NEWS_ITEM_KEY to it))
        }
    }

    private fun observeSnackBarMessages(event: LiveData<Event<Int>>) {
        rl_news_list.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<Event<Any>>) {
        rl_news_list.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showSearchError() {
        newsListViewModel.showSnackbarMessage(R.string.search_error)
    }

    private fun showDataView(show: Boolean) {
        tv_no_data.visibility = if (show) GONE else VISIBLE
        rl_news_list.visibility = if (show) VISIBLE else GONE
        pb_loading.toGone()
    }

    private fun showLoadingView() {
        pb_loading.toVisible()
        tv_no_data.toGone()
        rl_news_list.toGone()
        EspressoIdlingResource.increment()
    }


    private fun showSearchResult(newsItem: NewsItem) {
       // newsListViewModel.openNewsDetails(newsItem)
        pb_loading.toGone()
    }

    private fun noSearchResult(unit: Unit) {
        showSearchError()
        pb_loading.toGone()
    }

    private fun handleNewsList(newsModel: List<Category>) {

         if (newsModel?.isNullOrEmpty()){
                   Log.e("UseCase","Api called")
                   newsListViewModel.getCategoryFromDatabase()
             return
               }else{
                   Log.e("UseCase","database called")


             if (newsModel.size>0){
                 bindListData(newsModel)
             }else{
                 showDataView(false)
                 //newsListViewModel.showToastMessage("")
             }


               }

      /*  when (newsModel) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> newsModel.data?.let { bindListData(categoryModel = it) }
            is Resource.DataError -> {
                showDataView(false)
                newsModel.errorCode?.let { newsListViewModel.showToastMessage(it) }
            }
        }*/

    }

    override fun observeViewModel() {
       // observe(newsListViewModel.categoryLiveDataIn, ::handleNewsList)


        observe(newsListViewModel.newsLiveData, ::handleNewsListProgress)


        observe(newsListViewModel.newsSearchFound, ::showSearchResult)
        observe(newsListViewModel.noSearchFound, ::noSearchResult)
        observeEvent(newsListViewModel.openNewsDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(newsListViewModel.showSnackBar)
        observeToast(newsListViewModel.showToast)

    }

    private fun handleNewsListProgress(resource: Resource<List<Category>>?) {
        when (resource) {
                  is Resource.Loading -> showLoadingView()
                 // is Resource.Success -> resource.data?.let { bindListData(categoryModel = it) }
                  is Resource.DataError -> {
                      showDataView(false)
                      resource.errorCode?.let { newsListViewModel.showToastMessage(it) }
                  }
              }
    }
}
