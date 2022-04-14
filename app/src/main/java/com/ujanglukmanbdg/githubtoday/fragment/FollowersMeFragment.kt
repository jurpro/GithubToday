package com.ujanglukmanbdg.githubtoday.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ujanglukmanbdg.githubtoday.R
import com.ujanglukmanbdg.githubtoday.activity.AboutMeActivity
import com.ujanglukmanbdg.githubtoday.activity.AboutMeActivity.Companion.EXTRA_DETAIL_USERNAME
import com.ujanglukmanbdg.githubtoday.adapter.ListSearchUserAdapter
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.databinding.FragmentFollowersMeBinding
import com.ujanglukmanbdg.githubtoday.helper.Resource
import com.ujanglukmanbdg.githubtoday.helper.Status
import com.ujanglukmanbdg.githubtoday.viewmodel.FollowersViewModel

class FollowersMeFragment : Fragment() {
    private var followersBinding: FragmentFollowersMeBinding? = null
    private val binding get() = followersBinding!!

    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: ListSearchUserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        followersBinding = FragmentFollowersMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val user = activity?.intent?.getParcelableExtra<ItemsItem>(EXTRA_DETAIL_USERNAME)

        initRecyclerView()

        initViewModel()

        if (user != null && index != null) {
            setFollowList(index)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        adapter = ListSearchUserAdapter()
        adapter.notifyDataSetChanged()

        val rvFollowers = binding.rvFollowers

        rvFollowers.layoutManager = LinearLayoutManager(activity)
        rvFollowers.adapter = adapter
        rvFollowers.setHasFixedSize(false)
        adapter.setOnItemClickCallback(object : ListSearchUserAdapter.OnItemClickCallback{
            override fun onItemClicked(user: ItemsItem) {
                AboutMeActivity.start(activity, user)
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowersViewModel::class.java]
    }


    private fun setFollowList(index: Int) {
        when (index) {
            1 -> viewModel.getFollowers(getString(R.string.my_account)).observe(viewLifecycleOwner){ loadDataFollow(it)}
            2 -> viewModel.getFollowing(getString(R.string.my_account)).observe(viewLifecycleOwner){ loadDataFollow(it)}
        }

    }

    private fun loadDataFollow(it: Resource<ArrayList<ItemsItem>>) {
        when (it.status) {
            Status.LOADING -> showLoading(true)
            Status.SUCCESS -> {
                it.data?.let { users -> adapter.setUser(users) }
                showLoading(false)
            }
            Status.ERROR -> {
                showLoading(false)
                Toast.makeText(activity, "Failure: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        binding.apply {
            if (loading) {
                rvFollowers.visibility = View.GONE
            } else {
                rvFollowers.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        followersBinding = null
    }


    companion object {

        const val ARG_SECTION_NUMBER = "section_number"

    }
}