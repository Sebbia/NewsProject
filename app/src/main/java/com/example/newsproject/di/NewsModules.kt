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
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindModule {
    @Singleton
    @Binds
    abstract fun bindNewsRemoteDataSource(
        newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource

}

@Module
@InstallIn(SingletonComponent::class)
class AppProvideModule {
    @Singleton
    @Provides
    fun provideNewsRepositoryImpl(
        remoteDS: NewsRemoteDataSource
    ): NewsRepository = NewsRepositoryImpl(remoteDS)
}


@Module
@InstallIn(ViewModelComponent::class)
class ViewModelProvideModule {
    @Provides
    fun provideCategoryListViewModelImpl(
        repo: NewsRepository
    ): CategoryListViewModel = CategoryListViewModelImpl(repo)

    @Provides
    fun provideNewsListViewModelImpl(
        repo: NewsRepository
    ): NewsListViewModel = NewsListViewModelImpl(repo)

    @Provides
    fun provideNewsViewModelImpl(
        repo: NewsRepository
    ): NewsViewModel = NewsViewModelImpl(repo)
}