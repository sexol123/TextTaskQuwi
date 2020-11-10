package com.example.testtask.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthItems(
    val chat: Boolean,
    val coder: Boolean,
    val disc_space: Boolean,
    val instant_screen: Boolean,
    val member_area: Boolean,
    val owner: Boolean,
    val screens_month: Boolean,
    val timelapse_video: Boolean,
    val tracked_timer_month: Boolean
): Parcelable