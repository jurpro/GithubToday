package com.ujanglukmanbdg.githubtoday.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    var name: String,
    var location: String,
    var activeSince: String,
    var photo: String,
    var username: String,
    var repository: Int,
    var company: String,
    var followers: Int,
    var following: Int,
    var email: String,
    var about_user: String,
    var website: String,
    var twitter: String,
    var project: String,
    var packageGithub: String,
    var starsGithub: Int,
    var languageGithub: String,
    var achievements: String,
): Parcelable
