package com.ujanglukmanbdg.githubtoday

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    var name: String,
    var description: String,
    var photo: Int
): Parcelable
