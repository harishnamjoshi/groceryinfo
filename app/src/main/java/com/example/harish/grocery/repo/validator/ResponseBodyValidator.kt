package com.example.harish.grocery.repo.validator

import com.example.harish.grocery.repo.mapper.JSON_CODE
import com.example.harish.grocery.repo.mapper.JSON_STATUS
import okhttp3.ResponseBody
import org.json.JSONObject

class ResponseBodyValidator : Validator<ResponseBody?> {

    private val CODE = 404

    override fun validate(data: ResponseBody?): Boolean {

        if (data == null) return false

        val code = JSONObject(data.string()).getJSONObject(JSON_STATUS).getInt(JSON_CODE)

        return code != CODE

    }
}