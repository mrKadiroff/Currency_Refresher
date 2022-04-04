package com.example.currency_refresh

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.currency_refresh.Retrofit.ApiClient
import com.example.currency_refresh.Retrofit.ApiService
import com.example.currency_refresh.Room.AppDatabase
import com.example.currency_refresh.Room.Valyuta
import com.example.currency_refresh.models.Currency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

lateinit var appDatabase: AppDatabase
class UploadWork(context: Context,workerParameters: WorkerParameters):
          Worker(context,workerParameters){

    private val TAG = "UploadWork"
    override fun doWork(): Result {

        SetData()

        return Result.success()
    }


    private fun SetData() {
        appDatabase = AppDatabase.getInstance(applicationContext)
                    ApiClient.getRetrofit().create(ApiService::class.java)
                .getCurrency().enqueue(object : Callback<List<Currency>> {
                    override fun onResponse(
                        call: Call<List<Currency>>,
                        response: Response<List<Currency>>
                    ) {
                        if (response.isSuccessful){
                            val body = response.body()
                            val allCurrency = appDatabase.valyutaDao().getAllCurrencysimple()


                            body?.forEach{
                                val format = SimpleDateFormat("yyyyMM_HH:mm:ss", Locale.getDefault()).format(Date())

                                if (allCurrency.isNullOrEmpty()){
                                    appDatabase.valyutaDao().insertCurrency(it)
                                    Log.d(TAG, "onResponse: inserted")
                                }else{
                                    appDatabase.valyutaDao().updateCurrency(it)
                                    Log.d(TAG, "onResponse: updated")
                                }




                            }


                        }
                    }

                    override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
                        Log.d(TAG, "onFailure: ${t.message}")
                    }

                })
    }

}


