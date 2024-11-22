package com.example.aizen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.aizen.Model.ItemsModel
import com.example.aizen.databinding.ActivityDetailBinding
import com.example.aizen.databinding.ViewholderCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart

class CartAdapter(
    private val listItemSelected:ArrayList<ItemsModel>,
    context:Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?=null

):RecyclerView.Adapter<CartAdapter.Viewholder>(){
    class Viewholder (val binding:ViewholderCartBinding):RecyclerView.ViewHolder(binding.root){

    }
    private val managmentCart=ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
        val binding=
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item=listItemSelected[position]

        holder.binding.titleTxt.text=item.title
        holder.binding.freeEachitem.text="$${item.price}"
        holder.binding.totalEachitem.text="$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberitemTxt.text=item.numberInCart.toString()


        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)

        holder.binding.plusCartBtn.setOnClickListener{
            managmentCart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                }

            })
        }
        holder.binding.minusCartBtn.setOnClickListener{
            managmentCart.minusItem(
                listItemSelected,position,
                object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int=listItemSelected.size
}