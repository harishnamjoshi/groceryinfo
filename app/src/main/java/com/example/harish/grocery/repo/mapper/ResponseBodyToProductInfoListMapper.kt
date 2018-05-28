package com.example.harish.grocery.repo.mapper

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.repo.validator.Validator
import org.json.JSONObject

class ResponseBodyToProductInfoListMapper(
        private val briefObjectMapper: Mapper<JSONObject, BriefProductInfo>,
        private val validator: Validator<String?>
) : Mapper<String?, ArrayList<BriefProductInfo>> {

    override fun map(param: String?): ArrayList<BriefProductInfo> {
        val productInfos: ArrayList<BriefProductInfo> = ArrayList()

        if (validator.validate(param)) {
            val jsonObject = JSONObject(param)

            val productsArray = jsonObject.getJSONArray(JSON_PRODUCTS)

            for (i in 0 until productsArray.length()) {
                productInfos.add(briefObjectMapper.map(productsArray.getJSONObject(i)))
            }
        }



        return productInfos
    }

}