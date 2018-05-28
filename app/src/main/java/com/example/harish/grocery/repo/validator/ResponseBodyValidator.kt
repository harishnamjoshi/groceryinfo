package com.example.harish.grocery.repo.validator

import com.example.harish.grocery.repo.mapper.DATA_NOT_AVAILABLE
import com.example.harish.grocery.repo.mapper.JSON_CODE
import com.example.harish.grocery.repo.mapper.JSON_STATUS
import org.json.JSONObject

class ResponseBodyValidator : Validator<String?> {

    override fun validate(param: String?): Boolean {

        if (param == null) return false

        val code = JSONObject(param).getJSONObject(JSON_STATUS).getInt(JSON_CODE)

        return code != DATA_NOT_AVAILABLE

    }
}