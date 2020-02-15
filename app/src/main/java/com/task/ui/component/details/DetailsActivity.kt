package com.task.ui.component.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.models.db.Product
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.utils.Constants
import com.task.utils.toGone
import kotlinx.android.synthetic.main.details_layout.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject



class DetailsActivity : BaseActivity() {

    private var productName: String? = null

    @Inject
    lateinit var detailViewModel: DetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val layoutId: Int
        get() = R.layout.details_layout

    var productId:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = intent.getIntExtra(Constants.PRODUCT_ITEM_KEY,0)
        productName = intent.getStringExtra(Constants.PRODUCT_NAME_KEY)

        ic_toolbar_refresh.setImageResource(R.drawable.ic_arrow_back)
        ic_toolbar_refresh.setOnClickListener(View.OnClickListener { onBackPressed() })

        productName?.let {
            txt_toolbar_title?.setText(it)
        }
        detailViewModel?.setUp(productId)

        detailViewModel?.productsLiveDataIn?.observe(this, Observer {
            if (it==null){
                textView_norecord.visibility = View.VISIBLE
                group_detail.visibility = View.GONE
            }else{
                initializeView(it)
            }

            progressBar.toGone()
        })
    }

    override fun observeViewModel() {
        //observe(viewModel.productItem, ::initializeView)
    }

    override fun initializeViewModel() {
        detailViewModel = viewModelFactory.create(DetailsViewModel::class.java)
    }

    private fun initializeView(product: Product) {
        textView_prd_name?.text = product.productName
        textView_price_value?.text = product?.variantInfo!![0].price.toString()

            Picasso.get().load(R.drawable.product)
                    .into(imageView_prd)

    }
}
