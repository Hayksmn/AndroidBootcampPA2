package com.self.admin.bootcamp.articleInfo.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.self.admin.bootcamp.R
import com.self.admin.bootcamp.databinding.ArticleFragmentBinding

class ArticleFragment: Fragment() {

    private lateinit var binding: ArticleFragmentBinding
    private lateinit var viewModel: ArticleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.article_fragment,
            container,
            false
        )
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        binding.articleViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}