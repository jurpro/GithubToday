package com.ujanglukmanbdg.githubtoday.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ujanglukmanbdg.githubtoday.R
import com.ujanglukmanbdg.githubtoday.data.GithubUser
import com.ujanglukmanbdg.githubtoday.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var bindingDataUser: ActivityUserDetailBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDataUser = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(bindingDataUser.root)

        supportActionBar?.title = resources.getString(R.string.detail_github_user)

        val dataGithub = intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser

        Glide.with(this)
            .load(dataGithub.photo)
            .circleCrop()
            .into(bindingDataUser.includeHeader.imgPhotoDetail)
        bindingDataUser.includeHeader.textDetailNama.text = dataGithub.name
        bindingDataUser.includeHeader.textDetailFollowers.text = dataGithub.followers.toString()
        bindingDataUser.includeHeader.textDetailFollowing.text = dataGithub.following.toString()
        bindingDataUser.includeHeader.textDetailStars.text = dataGithub.starsGithub.toString()
        bindingDataUser.includeDescription.textUsernameDetail.text = dataGithub.username
        bindingDataUser.includeDescription.textLocationDetail.text = dataGithub.location
        bindingDataUser.includeDescription.textRepositoryDetail.text = dataGithub.repository.toString()
        bindingDataUser.includeDescription.textProjectDetail.text = dataGithub.project
        bindingDataUser.includeDescription.textPackageDetail.text = dataGithub.packageGithub
        bindingDataUser.includeDescription.textCompanyDetail.text = dataGithub.company
        bindingDataUser.includeDescription.textAktifDetail.text = dataGithub.activeSince
        bindingDataUser.includeDescription.textLanguageDetail.text = dataGithub.languageGithub
        bindingDataUser.includeDescription.textAchievementsDetail.text = dataGithub.achievements
        bindingDataUser.includeDescription.textEmailDetail.text = dataGithub.email
        bindingDataUser.includeDescription.textWebsiteDetail.text = dataGithub.website
        bindingDataUser.includeDescription.textTwitterDetail.text = dataGithub.twitter
        bindingDataUser.includeDescription.textAboutUserDetail.text = dataGithub.about_user

        val btnShare: Button = findViewById(R.id.btn_share_detail)
        val btnFavorite: Button = findViewById(R.id.btn_favorite_detail)

        btnShare.setOnClickListener {
            Toast.makeText(this, "Anda telah menyebarkan akun " + dataGithub.name, Toast.LENGTH_SHORT).show()
        }

        btnFavorite.setOnClickListener {
            Toast.makeText(this, "Anda telah menjadikan akun " + dataGithub.name + " sebagai favorit", Toast.LENGTH_SHORT).show()
        }
    }
}