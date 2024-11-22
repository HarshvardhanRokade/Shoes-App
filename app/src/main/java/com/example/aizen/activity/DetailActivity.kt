package com.example.aizen.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aizen.Adapter.ColorAdapter
import com.example.aizen.Adapter.SizeAdapter
import com.example.aizen.Adapter.SliderAdapter
import com.example.aizen.Model.ItemsModel
import com.example.aizen.Model.SliderModel
import com.example.aizen.R
import com.example.aizen.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart
import java.util.ResourceBundle.getBundle

class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOder=1
    private lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart=ManagmentCart(this)

        getBundle()
        banners()
        initLists()
    }

    private fun initLists() {
        val sizeList=ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }

        binding.sizeList.adapter=SizeAdapter(sizeList)
        binding.sizeList.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }

        binding.colorList.adapter=ColorAdapter(colorList)
        binding.colorList.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }

    private fun banners() {
        val sliderItems=ArrayList<SliderModel>()
        for (imageUrl in item.picUrl){
            sliderItems.add(SliderModel(imageUrl))
        }

        binding.slider.adapter=SliderAdapter(sliderItems,binding.slider)
        binding.slider.clipToPadding=true
        binding.slider.clipChildren=true
        binding.slider.offscreenPageLimit=1


        if (sliderItems.size>1){
            binding.dotindicator.visibility=View.VISIBLE
            binding.dotindicator.attachTo(binding.slider)
        }
    }

    private fun getBundle() {
        item=intent.getParcelableExtra("object")!!

            binding.titleTxt.text=item.title
            binding.descriptionTxt.text=item.description
            binding.priceTxt.text="$"+item.price
            binding.ratingTxt.text="${item.rating} Rating"
            binding.addToCartBtn.setOnClickListener {
                item.numberInCart=numberOder
                managementCart.insertFood(item)
            }
        binding.backBtn.setOnClickListener{finish()}
        binding.cartBtn.setOnClickListener{
        startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }
    }
}