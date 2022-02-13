package com.example.newsproject.di

import com.example.newsproject.data.NewsRemoteDataSource
import com.example.newsproject.data.NewsRemoteDataSourceImpl
import com.example.newsproject.data.NewsRepository
import com.example.newsproject.data.NewsRepositoryImpl
import com.example.newsproject.ui.categoryList.CategoryListViewModel
import com.example.newsproject.ui.categoryList.CategoryListViewModelImpl
import com.example.newsproject.ui.news.NewsViewModel
import com.example.newsproject.ui.news.NewsViewModelImpl
import com.example.newsproject.ui.newsList.NewsListViewModel
import com.example.newsproject.ui.newsList.NewsListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindNewsRemoteDataSource(
        newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource

    @Binds
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class FragmentModule {
    @Binds
    abstract fun bindCategoryListViewModel(
        categoryListViewModelImpl: CategoryListViewModelImpl
    ): CategoryListViewModel

    @Binds
    abstract fun bindNewsListViewModel(
        newsListViewModelImpl: NewsListViewModelImpl
    ): NewsListViewModel

    @Binds
    abstract fun bindNewsViewModel(
        newsViewModelImpl: NewsViewModelImpl
    ): NewsViewModel
}