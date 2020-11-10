package com.example.testtask.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSettingsGlobal(
    val client_settings: String
): Parcelable