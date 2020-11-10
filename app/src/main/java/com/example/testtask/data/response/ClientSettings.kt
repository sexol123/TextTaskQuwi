package com.example.testtask.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClientSettings(
    val do_not_show_welcome: String,
    val hash: String,
    val hide_archive_cnt: Boolean,
    val is_show_hidden_chats: Boolean,
    val issues_filters: List<IssuesFilter>,
    val show_inline_recognized_text: Boolean,
    val submit_key: String
): Parcelable