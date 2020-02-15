package com.task.ui.component.news

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.data.models.db.Category
import com.task.data.remote.dto.NewsItem
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.news.categoriesAdapter.NewsAdapter
import com.task.utils.*
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject



class NewsListActivity : BaseActivity() {
    @Inject
    lateinit var newsListViewModel: NewsListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val layoutId: Int
        get() = R.layout.home_activity

   /* val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource
*/
    override fun initializeViewModel() {
        newsListViewModel = viewModelFactory.create(NewsListViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ic_toolbar_refresh.setOnClickListener {
            newsListViewModel.getCategories()
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
        showLoadingView()
        newsListViewModel.getCategories()

    }

    private fun bindListData(categoeyList:List<Category>) {
        if (!(categoeyList.isNullOrEmpty())) {
            val newsAdapter = NewsAdapter(newsListViewModel, categoeyList!!)
            rv_news_list.adapter = newsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
       // EspressoIdlingResource.decrement()
    }

    private fun navigateToDetailsScreen(navigateEvent: Event<Category>) {
        navigateEvent.getContentIfNotHandled()?.let {
            //startActivity(intentFor<DetailsActivity>(Constants.NEWS_ITEM_KEY to it))


            var categoryId=it.categoryId
            startActivity(Intent(this, ProductsListActivity::class.java)
                    .putExtra(Constants.EXTRAS_CATEGORY_ID,categoryId))

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
       // EspressoIdlingResource.increment()
    }


    private fun showSearchResult(newsItem: NewsItem) {
       // newsListViewModel.openNewsDetails(newsItem)
        pb_loading.toGone()
    }

    private fun noSearchResult(unit: Unit) {
        showSearchError()
        pb_loading.toGone()
    }

    private fun handleNewsList(categoryList: List<Category>) {

        if (categoryList.isNullOrEmpty()){
          //  showDataView(false)
            pb_loading.toGone()
        }else{
            bindListData(categoryList)
        }

    }

    override fun observeViewModel() {
        observe(newsListViewModel.categoryLiveDataIn!!, ::handleNewsList)


    //    observe(newsListViewModel.newsLiveData, ::handleNewsListProgress)
        observe(newsListViewModel.newsSearchFound, ::showSearchResult)
        observe(newsListViewModel.noSearchFound, ::noSearchResult)
        observeEvent(newsListViewModel.openNewsDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(newsListViewModel.showSnackBar)
        observeToast(newsListViewModel.showToast)

    }


}
