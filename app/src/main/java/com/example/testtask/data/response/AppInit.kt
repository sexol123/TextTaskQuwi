package com.example.testtask.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppInit(
    val auth_items: AuthItems,
    val chats_count_unread: Int,
    val companies: List<Company>,
    val has_own_company: Boolean,
    val has_projects: Boolean,
    val id_channel_main: Int,
    val user: User,
    val user_settings: UserSettings,
    val user_settings_global: UserSettingsGlobal
): Parcelable