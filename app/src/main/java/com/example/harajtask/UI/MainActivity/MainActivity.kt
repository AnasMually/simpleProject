package com.example.harajtask.UI.MainActivity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.harajtask.R
import com.example.harajtask.UI.ShowProductDetailsActivity
import com.example.harajtask.UI.MainActivity.Adapter.ProductsAdapter
import com.example.harajtask.UI.MainActivity.ViewModelWithReposetry.WorkWithJSONArrayViewModel
import com.example.harajtask.databinding.ActivityMainBinding
import com.example.harajtask.databinding.ViewCarsBinding
import org.json.JSONArray
import android.util.Pair as UtilPair


class MainActivity : AppCompatActivity(), ProductsAdapter.OnClickItemListener {
    companion object {
        val DATE_EXTRA = "date"
        val IMAGE_URL_EXTRA = "thumbURL"
        val DETAILS_EXTRA = "body"
        val USERNAME_EXTRA = "username"
        val LOCATION_EXTRA = "city"
        val TITLE_EXTRA = "title"
    }


    private val carsAdapter: ProductsAdapter by lazy { ProductsAdapter(this) }
    private var products: JSONArray? = null
        set(value) {
            field = value
            carsAdapter.array = value
        }

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar as Toolbar)
        binding.recyclerMain.apply {
            this.setHasFixedSize(true)
            this.adapter = carsAdapter.apply {
                onClickItemListener = this@MainActivity
            }
        }
        ViewModelProvider(this).get(WorkWithJSONArrayViewModel::class.java).apply {
            jsonArrayLiveData.observe(this@MainActivity) { products = it }
            requestJSONArray(this@MainActivity)
        }
    }


    override fun onClick(position: Int, binding: ViewCarsBinding) {
        startActivity(
            Intent(this, ShowProductDetailsActivity::class.java).apply {
                products!!.getJSONObject(position).let {
                    putExtra(TITLE_EXTRA, it.getString(TITLE_EXTRA))
                    putExtra(LOCATION_EXTRA, it.getString(LOCATION_EXTRA))
                    putExtra(USERNAME_EXTRA, it.getString(USERNAME_EXTRA))
                    putExtra(DETAILS_EXTRA, it.getString(DETAILS_EXTRA))
                    putExtra(IMAGE_URL_EXTRA, it.getString(IMAGE_URL_EXTRA))
                    putExtra(DATE_EXTRA, it.getString(DATE_EXTRA))
                }
            }, ActivityOptions.makeSceneTransitionAnimation(
                this, UtilPair.create(binding.carImage, getString(R.string.image_transition)),
                UtilPair.create(binding.carName, getString(R.string.name_transition)),
                UtilPair.create(binding.ownerUsername, getString(R.string.username_transition)),
                UtilPair.create(binding.iconUsername, getString(R.string.icon_username_transition)),
                UtilPair.create(binding.iconLocation, getString(R.string.icon_location_transition)),
                UtilPair.create(binding.location, getString(R.string.location_transition)),
            ).toBundle()
        )
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mein_menu, menu)
        return true
    }

}