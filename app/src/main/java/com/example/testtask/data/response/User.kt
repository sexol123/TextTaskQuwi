package com.example.testtask.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val allow_timer_autostop: Boolean,
    val allow_timer_screens: Boolean,
    val avatar_url: String,
    val dta_activity: String,
    val dta_create: String,
    val dta_timer_activity: String,
    val due_penalties: Int,
    val email: String,
    val email_signature: String,
    val id: Int,
    val id_company: Int,
    val is_active: Boolean,
    val is_online: Int,
    val is_shared_for_me: Boolean,
    val is_shared_from_me: Boolean,
    val is_timer_online: Int,
    val name: String,
    val role: String,
    val timer_last: String,
    val timezone_offset: Int
): Parcelable