package com.self.admin.bootcamp.articleInfo.infoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.self.admin.bootcamp.GlobalInstances
import com.self.admin.bootcamp.R
import com.self.admin.bootcamp.articleInfo.api.ArticleRepositoryImpl
import com.self.admin.bootcamp.articleInfo.api.ArticleService
import com.self.admin.bootcamp.articleInfo.database.ArticleRoomDatabase
import com.self.admin.bootcamp.databinding.InfoListFragmentBinding

class InfoListFragment : Fragment() {

    private lateinit var binding: InfoListFragmentBinding
    private lateinit var viewModel: InfoListViewModel
    private val adapter = InfoListAdapter(GlobalInstances.imageLoader)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            val articleService = GlobalInstances.api.createService(ArticleService::class.java)
            val dataSource = ArticleRoomDatabase.getInstance(it).articleInfoDao
            val viewModelFactory = InfoListViewModelFactory(dataSource, ArticleRepositoryImpl(articleService))
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
        binding.articleInfoList.adapter = adapter

        viewModel.articleInfoList.observe(this, Observer {
            adapter.submitList(it)
        })



    }
}