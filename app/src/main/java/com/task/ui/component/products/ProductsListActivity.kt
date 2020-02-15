package com.task.ui.component.products

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.data.models.db.Product
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.products.categoriesAdapter.ProductsAdapter
import com.task.utils.*
import kotlinx.android.synthetic.main.products_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class ProductsListActivity : BaseActivity() {
    private var categoryName: String? = null
    @Inject
    lateinit var productsListViewModel: ProductsListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var categoryId:Int=0

    override val layoutId: Int
        get() = R.layout.products_activity


    override fun initializeViewModel() {
        productsListViewModel = viewModelFactory.create(ProductsListViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = intent.getIntExtra(Constants.EXTRAS_CATEGORY_ID, 0)
        categoryName = intent.getStringExtra(Constants.EXTRAS_CATEGORY_NAME)

        ic_toolbar_refresh.setImageResource(R.drawable.ic_arrow_back)
        ic_toolbar_refresh.setOnClickListener(View.OnClickListener { onBackPressed() })

        categoryName?.let {
            txt_toolbar_title?.setText(categoryName)
        }
        productsListViewModel?.setUp(categoryId)

        productsListViewModel?.productsLiveDataIn?.observe(this, Observer {
            if (it.isNullOrEmpty()){
                //  showDataView(false)
                pb_loading.toGone()
            }else{
                bindListData(it)
            }
        })


        val layoutManager = GridLayoutManager(this, 2)

        rv_category_list.layoutManager = layoutManager
        rv_category_list.setHasFixedSize(true)
      //  productsListViewModel.getNews()
        showLoadingView()

    }

    private fun bindListData(productList:List<Product>) {
        if (!(productList.isNullOrEmpty())) {
            val newsAdapter = ProductsAdapter(productsListViewModel, productList!!)
            rv_category_list.adapter = newsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
       // EspressoIdlingResource.decrement()
    }

    private fun navigateToDetailsScreen(navigateEvent: Event<Product>) {
        navigateEvent.getContentIfNotHandled()?.let {
            var productId=it.productId
            startActivity(Intent(this, DetailsActivity::class.java)
                    .putExtra(Constants.PRODUCT_ITEM_KEY, productId)
                    .putExtra(Constants.PRODUCT_NAME_KEY, it.productName)

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
        productsListViewModel.showSnackbarMessage(R.string.search_error)
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


    override fun observeViewModel() {
       // observe(productsListViewModel.productsLiveDataIn!!, ::handleNewsList)
        observeEvent(productsListViewModel.openNewsDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(productsListViewModel.showSnackBar)
        observeToast(productsListViewModel.showToast)

    }

}
