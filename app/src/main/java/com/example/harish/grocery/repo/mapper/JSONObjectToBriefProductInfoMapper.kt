package com.example.harish.grocery.repo.mapper

import com.example.harish.grocery.model.BriefProductInfo
import org.json.JSONObject

class JSONObjectToBriefProductInfoMapper : Mapper<JSONObject, BriefProductInfo> {

    override fun map(param: JSONObject): BriefProductInfo {

        val productId = param.getString(JSON_PRODUCT_ID)

        val title = param.getString(JSON_PRODUCT_TITLE)

        val imageUrl = createImageUrl(param.getJSONObject(JSON_IMAGE_INFO_OBJECT).getString(JSON_IMAGE_NAME))

        val price = param.getJSONObject(JSON_PRICING_INFO_OBJECT).getString(JSON_PRICE)

        val currency = CURRENCY

        return BriefProductInfo(productId, title, price, currency, imageUrl)
    }
}