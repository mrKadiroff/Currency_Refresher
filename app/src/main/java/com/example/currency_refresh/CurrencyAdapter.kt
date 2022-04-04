package com.example.currency_refresh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_refresh.databinding.UserlistBinding
import com.example.currency_refresh.models.Currency

class CurrencyAdapter : ListAdapter<Currency, CurrencyAdapter.Vh>(MyDiffUtil()){

    inner class Vh(var userlistBinding: UserlistBinding) : RecyclerView.ViewHolder(userlistBinding.root) {

        fun onBind(currency: Currency) {
            userlistBinding.login.text = currency.CcyNm_UZ
            userlistBinding.narx.text = "${currency.Rate},Sana:${currency.Date}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(UserlistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyDiffUtil: DiffUtil.ItemCallback<Currency>(){
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.equals(newItem)
        }


    }



}