package com.example.harajtask.UI

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.DATE_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.DETAILS_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.IMAGE_URL_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.LOCATION_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.TITLE_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.USERNAME_EXTRA
import com.example.harajtask.databinding.ActivityShowProductBinding
import java.lang.Exception

class ShowProductDetailsActivity : AppCompatActivity() {

    private val productTitle: String? by lazy {
        intent.getStringExtra(TITLE_EXTRA)
    }
    private val binding: ActivityShowProductBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_show_product)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            ViewModelProvider.AndroidViewModelFactory(application)
            setSupportActionBar(binding.toolbar as Toolbar)
            Glide.with(application).load(intent.getStringExtra(IMAGE_URL_EXTRA)).into(carImage)
            carName.text = productTitle
            location.text = intent.getStringExtra(LOCATION_EXTRA)
            ownerUsername.text = intent.getStringExtra(USERNAME_EXTRA)
            carDetails.text = intent.getStringExtra(DETAILS_EXTRA)
            carData.text = intent.getStringExtra(DATE_EXTRA)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.show_product_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        try {
            startActivity(
                Intent(Intent.ACTION_SEND).setType("plan/text")
                    .putExtra(Intent.EXTRA_TEXT, productTitle)
            )
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
        return true
    }

}
