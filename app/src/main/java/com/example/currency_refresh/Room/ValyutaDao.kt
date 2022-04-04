package com.example.currency_refresh.Room

import androidx.room.*
import com.example.currency_refresh.models.Currency
import io.reactivex.Flowable

@Dao
interface ValyutaDao {




    @Insert
    fun insertCurrency(currency: Currency)

    @Query("select * from currency")
    fun getAllCurrency(): Flowable<List<Currency>>

    @Query("select * from currency")
    fun getAllCurrencysimple(): List<Currency>
//
//    @Delete
//    fun deleteCurrency(valyuta: List<Valyuta>)


    @Update
    fun updateCurrency(currency: Currency)
}