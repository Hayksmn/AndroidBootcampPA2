package com.self.admin.bootcamp.articleInfo.infoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.self.admin.bootcamp.R
import com.self.admin.bootcamp.articleInfo.api.ArticleRepositoryImpl
import com.self.admin.bootcamp.articleInfo.api.ArticleService
import com.self.admin.bootcamp.articleInfo.database.ArticleInfoDao
import com.self.admin.bootcamp.databinding.InfoListFragmentBinding
import org.koin.android.ext.android.inject

class InfoListFragment : Fragment() {

    private lateinit var binding: InfoListFragmentBinding
    private lateinit var viewModel: InfoListViewModel
    private lateinit var adapter: InfoListAdapter
    private val imageLoader: RequestManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            val articleService: ArticleService by inject()
            val dataSource: ArticleInfoDao by inject()
            val viewModelFactory =
                InfoListViewModelFactory(dataSource, ArticleRepositoryImpl(articleService))
            viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(InfoListViewModel::class.java)
            viewModel.getListFromNetwork()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.info_list_fragment,
            container,
            false
        )

        binding.infoListViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = InfoListAdapter(imageLoader) {
            findNavController().navigate(
                InfoListFragmentDirections.actionInfoListFragmentToArticleFragment(
                    it
                )
            )
        }
        binding.articleInfoList.adapter = adapter

        viewModel.articleInfoList.observe(this, Observer {
            adapter.submitList(it)
        })

    }
}