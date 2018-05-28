package com.example.harish.grocery.repo.mapper

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.validator.Validator
import okhttp3.ResponseBody
import org.json.JSONObject

class ResponseBodyToProductInfoMapper(
        private val briefObjectMapper: Mapper<JSONObject, BriefProductInfo>,
        private val validator: Validator<ResponseBody?>
) : Mapper<ResponseBody?, ProductInfo?> {
    override fun map(param: ResponseBody?): ProductInfo? {

        if (validator.validate(param)) {
            val jsonObject = JSONObject(param?.string())

            val productJson = jsonObject.getJSONObject(JSON_PRODUCT)

            val description = productJson.getString(JSON_DESCRIPTION)

            return ProductInfo(briefObjectMapper.map(productJson), description)


        }
        return null
    }
}