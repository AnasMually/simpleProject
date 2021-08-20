package com.example.harajtask.UI.MainActivity.ViewModelWithReposetry

import android.content.Context
import org.json.JSONArray
import java.io.InputStreamReader
import java.lang.Exception

class Repository(private val context: Context) {

    companion object {
        private var instance: Repository? = null

        @JvmStatic
        fun getInstance(context: Context): Repository =
            instance ?: Repository(context).also { instance = it }
    }


    fun getJsonArray(): JSONArray? = try {
        JSONArray(InputStreamReader(context.assets.open("data.json")).readText())
    } catch (e: Exception) {
        null
    }

}
