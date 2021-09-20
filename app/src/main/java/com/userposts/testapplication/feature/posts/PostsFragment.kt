package com.userposts.testapplication.feature.posts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.userposts.testapplication.R
import com.userposts.testapplication.app.App
import com.userposts.testapplication.databinding.FragmentPostsBinding
import com.userposts.testapplication.feature.ErrorBottomSheet
import com.userposts.testapplication.feature.ErrorBottomSheet.Companion.BOTTOM_SHEET_KEY
import com.userposts.testapplication.feature.fragmentViewModelProvider
import com.userposts.testapplication.feature.posts.adapter.PostsAdapter

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostViewModel by fragmentViewModelProvider {
        (requireActivity().applicationContext as App).appComponent
            .run {
                PostViewModel(postsInteractor, internetStateObserver)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PostsAdapter()
        binding.postsRecyclerView.adapter = adapter

        binding.swipeLayout.setColorSchemeColors(R.color.green_700)
        binding.swipeLayout.setOnRefreshListener {
            viewModel.refreshPosts()
        }

        setRetryResultListener()

        viewModel.showGeneralErrorEvent.observe(viewLifecycleOwner, {
            binding.swipeLayout.isRefreshing = false
            ErrorBottomSheet().show(childFragmentManager)
        })

        viewModel.postsLiveEvent.observe(viewLifecycleOwner, {
            adapter.setItems(it)
            binding.swipeLayout.isRefreshing = false
        })

        viewModel.progressLiveEvent.observe(viewLifecycleOwner, { shouldShow ->
            binding.postsProgressBar.isVisible = shouldShow
        })
    }

    private fun setRetryResultListener() {
        childFragmentManager.setFragmentResultListener(
            BOTTOM_SHEET_KEY,
            this
        ) { _, _ -> viewModel.refreshPosts() }
    }
}