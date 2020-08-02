package com.fabiano.faoanime.screens.animes.adapter

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.fabiano.faoanime.models.Anime
import com.fabiano.faoanime.requests.SearchRequest

class AnimeDataSource(val stringSearch: String) : PageKeyedDataSource<Int, Anime>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Anime>
    ) {
        val page = 1
        SearchRequest(stringSearch, page) { value, error ->
            if (value != null) callback.onResult(value.results ?: arrayListOf(), null, page)
            if (error != null) { }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Anime>
    ) {
        val page = 1
        SearchRequest(stringSearch, page) { value, error ->
            if (value != null) callback.onResult(value.results ?: arrayListOf(), page)
            if (error != null) {
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Anime>
    ) {
        val page = 1
        SearchRequest(stringSearch, page) { value, error ->
            if (value != null) callback.onResult(value.results ?: arrayListOf(), page)
            if (error != null) {

            }
        }
    }

    companion object {
        class DataSourceFactory(val searchString: String) : DataSource.Factory<Int, Anime>() {
            override fun create(): DataSource<Int, Anime> = AnimeDataSource(searchString)
        }
    }
}
