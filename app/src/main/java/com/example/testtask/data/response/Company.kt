package com.example.testtask.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    val disc_space: Long,
    val dta_create: String,
    val id: Int,
    val id_user: Int,
    val logo_url: String,
    val max_file_count: Int,
    val max_file_size: Int,
    val name: String,
    val owner_fullname: String,
    val screens_interval: Int,
    val screens_keep_days: Int,
    val screens_month: Int,
    val screens_quality: Int,
    val secret_word: String,
    val spent_sec_daily: Int,
    val tariff: Tariff,
    val timezone: String,
    val timezone_offset: Int,
    val tracked_timer_month: Int,
    val uid: String,
    val updated: Int
): Parcelable