package com.example.currency_refresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.currency_refresh.Room.AppDatabase
import com.example.currency_refresh.databinding.ActivityMainBinding
import com.example.currency_refresh.models.Currency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var appDatabase: AppDatabase
    lateinit var adapter: CurrencyAdapter
    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase = AppDatabase.getInstance(binding.root.context)

        setWork()
        adapter = CurrencyAdapter()

        appDatabase.valyutaDao().getAllCurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Consumer<List<Currency>> {
                override fun accept(t: List<Currency>?) {
                    adapter.submitList(t)
                }

            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {

                }

            })

        binding.rv.adapter = adapter


    }

    private fun setWork() {
        val cosntraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<UploadWork>(
            15,TimeUnit.MINUTES)
            .setConstraints(cosntraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

  
}