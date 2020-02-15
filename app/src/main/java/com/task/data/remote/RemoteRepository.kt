package com.task.data.remote

import com.task.App
import com.task.data.Resource
import com.task.data.error.Error.Companion.NETWORK_ERROR
import com.task.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.task.data.localdatabase.AppDBHelper
import com.task.data.models.TaxInfo
import com.task.data.models.VariantInfo
import com.task.data.models.db.*
import com.task.data.remote.dto.EcommResponse
import com.task.data.remote.service.EcommService
import com.task.utils.Constants
import com.task.utils.Network.Utils.isConnected
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator,private val localRepository: AppDBHelper) : RemoteSource {

    override suspend fun requestNews(): Resource<EcommResponse> {
        val newsService = serviceGenerator.createService(EcommService::class.java, Constants.BASE_URL)
        return when (val response = processCall(newsService::fetchEcommCat)) {
            is EcommResponse -> {

                var categoryPresent = localRepository.getAllCategoriesFromDB()

                if (categoryPresent==null ||categoryPresent?.value.isNullOrEmpty()){
                    insertAllDataInDB(response)
                }

                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!isConnected(App.context)) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

    //
   suspend fun insertAllDataInDB(ecommResponse: EcommResponse) {
        var categoryList = ArrayList<Category>()
        var productList =  ArrayList<Product>()
        var category: Category

        for (categoryResponse in ecommResponse.categoryRemotes!!) {
            category = Category()
            category.categoryId = categoryResponse.id!!
            category.categoryName = categoryResponse.name!!

            var product: Product
            for (productResponse in categoryResponse.products!!) {
                product = Product()
                product.categoryId = categoryResponse.id!!
                product.productId = productResponse.id!!
                product.dataAdded = productResponse.dateAdded!!
                product.productName = productResponse.name!!

                var variant: VariantInfo
                var variantInfoList = ArrayList<VariantInfo>()
                for (variantResponse in productResponse.variants!!) {
                    variant = VariantInfo()
                    variant.id = variantResponse.id as Int
                    variant.color = variantResponse.color as String
                    variant.price = variantResponse.price as Int
                    variant.size = if (variantResponse.size == null) "" else variantResponse.size as String

                    variantInfoList.add(variant)
                }

                product.variantInfo = variantInfoList

                var taxInfo = TaxInfo()
                taxInfo.name = productResponse.tax?.name!!
                taxInfo.value = productResponse.tax?.value!!
                product.taxInfo  = taxInfo

                productList.add(product)
            }

            if (productList.size > 0) {
                categoryList.add(category)
                insertAllProductsInDB(productList)
            }
            productList.clear()
        }

        insertCategoriesInDB(categoryList)
        categoryList.clear()
        productList.clear()




    }

    suspend fun insertCategoriesInDB(categoryList: List<Category>) {
        localRepository.insertCategoriesInDB(categoryList)
    }

    suspend fun insertAllProductsInDB(productList: List<Product>) {
        localRepository.insertAllProductsInDB(productList)
    }


}
