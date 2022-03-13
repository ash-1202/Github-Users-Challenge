package com.jodel.githubusers.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.Intent
import android.net.Uri
import com.jodel.githubusers.R
import com.jodel.githubusers.databinding.SingleUserFragmentBinding
import com.jodel.githubusers.ui.viewmodel.SingleUserViewModel

class SingleUserFragment : Fragment() {

    private val singleUserViewModel: SingleUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SingleUserFragmentBinding =  DataBindingUtil.inflate(inflater, R.layout.single_user_fragment, container, false)
        binding.singleUserViewModel = singleUserViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val username: String = SingleUserFragmentArgs.fromBundle(it) .username
            singleUserViewModel.getUserInfoByUsername(username)
        }

        singleUserViewModel.pageUrl.observe(viewLifecycleOwner, Observer {
            openInBrowser(it)
        })
    }

    private fun openInBrowser(pageUrl: String?) {
        pageUrl?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    object BindingLayoutUtils {
        @JvmStatic
        @BindingAdapter("avatar")
        fun loadAvatar(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view.context)
                    .load(it).apply(RequestOptions().circleCrop())
                    .into(view)
            }
        }
    }
}
