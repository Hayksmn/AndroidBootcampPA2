package com.self.admin.bootcamp.articleInfo.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.self.admin.bootcamp.R
import com.self.admin.bootcamp.articleInfo.api.ArticleRepositoryImpl
import com.self.admin.bootcamp.articleInfo.api.ArticleService
import com.self.admin.bootcamp.articleInfo.database.ArticleInfoDao
import com.self.admin.bootcamp.databinding.ArticleFragmentBinding
import org.koin.android.ext.android.inject

class ArticleFragment : Fragment() {

    private lateinit var binding: ArticleFragmentBinding
    private lateinit var viewModel: ArticleViewModel
    private val safeArgs: ArticleFragmentArgs by navArgs()
    private val imageLoader: RequestManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            val articleService: ArticleService by inject()
            val dataSource: ArticleInfoDao by inject()
            val viewModelFactory =
                ArticleViewModelFactory(dataSource, ArticleRepositoryImpl(articleService))
            viewModel =
                ViewModelProviders.of(this, viewModelFactory)
                    .get(ArticleViewModel::class.java)
            viewModel.fetchArticle(safeArgs.articleUrl)
        }
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
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.article.observe(this, Observer {
            binding.article = it
            imageLoader.load(it.thumbnail).placeholder(R.drawable.ic_book)
                .into(binding.articleImage)
        })
    }
}