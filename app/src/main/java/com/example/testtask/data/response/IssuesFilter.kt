package com.example.testtask.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IssuesFilter(
    val is_open: Int
): Parcelable