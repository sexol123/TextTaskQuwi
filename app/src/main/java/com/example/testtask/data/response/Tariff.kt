package com.example.testtask.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tariff(
    val disc_space: Long,
    val end_pay: String,
    val is_chat: Boolean,
    val is_free: Boolean,
    val is_instant_screen: Boolean,
    val is_member_area: Boolean,
    val is_timelapse_video: Boolean,
    val name: String,
    val screens_interval: Int,
    val screens_month: Int,
    val screens_quality: String,
    val sname: String,
    val tracked_timer_month: Int
): Parcelable