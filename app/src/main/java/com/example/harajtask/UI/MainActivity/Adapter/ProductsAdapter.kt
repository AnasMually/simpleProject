package com.example.harajtask.UI.MainActivity.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.DATE_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.IMAGE_URL_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.LOCATION_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.TITLE_EXTRA
import com.example.harajtask.UI.MainActivity.MainActivity.Companion.USERNAME_EXTRA
import com.example.harajtask.databinding.ViewCarsBinding
import org.json.JSONArray

class ProductsAdapter(private val context: Context) :
    RecyclerView.Adapter<ProductsAdapter.CarViewHolder>() {
    var onClickItemListener: OnClickItemListener? = null
    var array: JSONArray? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder(LayoutInflater.from(context).inflate(R.layout.view_cars, parent, false))

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.apply {
            this.root.setOnClickListener { onClickItemListener?.onClick(position, this) }
            array!!.getJSONObject(position).apply {
                //not check the variable in jsonObject is not null because all key at json file is found and not equal null
                // if(this.has(TITLE_EXTRA))
                Glide.with(context).load(getString(IMAGE_URL_EXTRA)).into(carImage)
                carName.text = getString(TITLE_EXTRA)
                carData.text = getString(DATE_EXTRA)
                commentCount.text = getInt("commentCount").toString()
                ownerUsername.text = getString(USERNAME_EXTRA)
                location.text = getString(LOCATION_EXTRA)
            }

        }
    }

    override fun getItemCount(): Int = array?.length() ?: 0


    inner class CarViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val binding: ViewCarsBinding by lazy { DataBindingUtil.bind(view)!! }
    }

    interface OnClickItemListener {
        fun onClick(position: Int, binding: ViewCarsBinding)
    }
}
