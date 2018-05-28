package com.example.harish.grocery.repo.mapper

import com.example.harish.grocery.model.BriefProductInfo
import com.example.harish.grocery.model.ProductInfo
import com.example.harish.grocery.repo.validator.Validator
import org.json.JSONObject

class ResponseBodyToProductInfoMapper(
        private val briefObjectMapper: Mapper<JSONObject, BriefProductInfo>,
        private val validator: Validator<String?>
) : Mapper<String?, ProductInfo?> {
    override fun map(param: String?): ProductInfo? {

        if (validator.validate(param)) {
            val jsonObject = JSONObject(param)

            val productJson = jsonObject.getJSONObject(JSON_PRODUCT)

            val description = productJson.getString(JSON_DESCRIPTION)

            return ProductInfo(briefObjectMapper.map(productJson), description)


        }
        return null
    }
}