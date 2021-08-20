package com.example.harajtask.UI.MainActivity.ViewModelWithReposetry

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

class WorkWithJSONArrayViewModel : ViewModel() {


    var jsonArrayLiveData = MutableLiveData<JSONArray?>()

    fun requestJSONArray(context: Context) {
        GlobalScope.launch {
            Repository.getInstance(context).getJsonArray().let {
                withContext(Dispatchers.Main) { jsonArrayLiveData.value = it }
            }
        }
    }


}