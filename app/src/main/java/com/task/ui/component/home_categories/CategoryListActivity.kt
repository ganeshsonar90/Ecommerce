package com.task.ui.component.home_categories

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.data.Resource
import com.task.data.models.db.Category
import com.task.data.remote.dto.EcommResponse
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.home_categories.categoriesAdapter.CategoryAdapter
import com.task.ui.component.products.ProductsListActivity
import com.task.utils.*
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class CategoryListActivity : BaseActivity() {
    @Inject
    lateinit var categoriesListViewModel: CategoriesListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var isNetworkComplete = false

    override val layoutId: Int
        get() = R.layout.home_activity

   /* val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource
*/
    override fun initializeViewModel() {
       categoriesListViewModel = viewModelFactory.create(CategoriesListViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txt_toolbar_title.setText(getString(R.string.category_home))
        ic_toolbar_refresh.setOnClickListener {
            categoriesListViewModel.getCategories()
        }

        btn_search.setOnClickListener {
            if (!(et_search.text?.toString().isNullOrEmpty())) {
                pb_loading.visibility = VISIBLE
                categoriesListViewModel.onSearchClick(et_search.text?.toString()!!)
            }
        }
        val layoutManager = GridLayoutManager(this, 2)
        rv_category_list.layoutManager = layoutManager
        rv_category_list.setHasFixedSize(true)
        categoriesListViewModel.getCategories()
        categoriesListViewModel.categoryLiveDataIn?.observe(this, Observer {
            handleCategoriesList(it)
        })

    }

    private fun bindListData(categoeyList:List<Category>) {
        if (!(categoeyList.isNullOrEmpty())) {
            val categoryAdapter = CategoryAdapter(categoriesListViewModel, categoeyList!!)
            rv_category_list.adapter = categoryAdapter
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
                    .putExtra(Constants.EXTRAS_CATEGORY_ID, categoryId)
                    .putExtra(Constants.EXTRAS_CATEGORY_NAME, it.categoryName)
            )

        }
    }

    private fun observeSnackBarMessages(event: LiveData<Event<Int>>) {
        rl_category_list.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<Event<Any>>) {
        rl_category_list.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showSearchError() {
        categoriesListViewModel.showSnackbarMessage(R.string.search_error)
    }

    private fun showDataView(show: Boolean) {
        tv_no_data.visibility = if (show) GONE else VISIBLE
        rl_category_list.visibility = if (show) VISIBLE else GONE
        pb_loading.toGone()
    }

    private fun showLoadingView() {
        pb_loading.toVisible()
        tv_no_data.toGone()
        rl_category_list.toGone()
       // EspressoIdlingResource.increment()
    }


    private fun showSearchResult(newsItem: Any) {
        /* // newsListViewModel.openNewsDetails(newsItem)
          pb_loading.toGone()*/
    }

    private fun noSearchResult(unit: Unit) {
        showSearchError()
        pb_loading.toGone()
    }

    private fun handleCategoriesList(categoryList: List<Category>) {

        if (categoryList.isNullOrEmpty()){
            if (isNetworkComplete) {
                showDataView(false)
            }
        }else{
            bindListData(categoryList)
        }

    }

    override fun observeViewModel() {
        // observe(categoriesListViewModel.categoryLiveDataIn!!, ::handleNewsList)
        observe(categoriesListViewModel.updateProgressLive, ::progressUpdate)
        // observe(categoriesListViewModel.newsSearchFound, ::showSearchResult)
        observe(categoriesListViewModel.noSearchFound, ::noSearchResult)
        observeEvent(categoriesListViewModel.openNewsDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(categoriesListViewModel.showSnackBar)
        observeToast(categoriesListViewModel.showToast)

    }

    private fun progressUpdate(any: Any) {
        /* if ((any as Int)==Constants.IS_LOADING)
             showLoadingView()*/
        var updateObj = any as Resource<EcommResponse>
        when (updateObj) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                isNetworkComplete = true
                pb_loading.toGone()
            }
            is Resource.DataError -> {
                isNetworkComplete = true
                showDataView(false)
            }

        }


    }


}
