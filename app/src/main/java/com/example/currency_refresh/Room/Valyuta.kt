package com.example.currency_refresh.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Valyuta {

    @ColumnInfo(name = "ccy")
    var Ccy: String? = null

    @ColumnInfo(name = "ccynm_english")
    var CcyNm_EN: String? = null

    @ColumnInfo(name = "ccynm_russia")
    var CcyNm_RU: String? = null

    @ColumnInfo(name = "ccynm_uzb")
    var CcyNm_UZ: String? = null

    @ColumnInfo(name = "ccynm_uzc")
    var CcyNm_UZC: String? = null

    @ColumnInfo(name = "code")
    var Code: String? = null

    @ColumnInfo(name = "date")
    var Date: String? = null

    @ColumnInfo(name = "diff")
    var Diff: String? = null

    @ColumnInfo(name = "nominal")
    var Nominal: String? = null

    @ColumnInfo(name = "rate")
    var Rate: String? = null

    @ColumnInfo(name = "soat")
    var Time: String? = null

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null






}