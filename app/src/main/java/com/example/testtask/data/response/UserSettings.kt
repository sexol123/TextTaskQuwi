package com.example.testtask.data.response

import android.os.Parcelable
import com.example.testtask.data.response.ClientSettings
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSettings(
    val client_settings: ClientSettings,
    val dta_mute_until: String,
    val is_mute_chats: Boolean,
    val is_show_hidden_chats: Boolean,
    val lang: String,
    val mute_until_period: Int,
    val show_inline_recognized_text: Boolean
): Parcelable