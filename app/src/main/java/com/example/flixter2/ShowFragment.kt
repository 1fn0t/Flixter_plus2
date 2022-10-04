package com.example.flixster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixter2.DetailsActivity
import com.example.flixter2.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

const val SHOW_EXTRA = "SHOW_EXTRA"

class ShowFragment : Fragment(), OnListFragmentInteractionListener{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_list, container, false)
        val showsRv = view.findViewById<View>(R.id.showsList) as RecyclerView
        loadShowsAdapter(showsRv)
        showsRv.layoutManager = GridLayoutManager(context, 3)
        return view
    }

    override fun onItemClick(item: Show) {

        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(SHOW_EXTRA, item)
        context?.startActivity(intent)
    }

    private fun loadShowsAdapter(showsRv: RecyclerView) {
        val client = AsyncHttpClient()
        client[
                "https://api.themoviedb.org/3/tv/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1.json",
//                "https://api.themoviedb.org/4/list/{list_id}?page=1&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed.json",
                object : JsonHttpResponseHandler()

                {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        val resultsJSON = json.jsonObject.get("results").toString()

                        val gson = Gson()
                        val arrayShowType = object : TypeToken<List<Show>>() {}.type
                        val models : List<Show> = gson.fromJson(resultsJSON, arrayShowType)
                        showsRv.adapter = ShowsRVA(models, this@ShowFragment)
                        Log.d("ShowFragment", "response successful")
                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        t?.message?.let {
                            Log.e("ShowFragment", errorResponse)
                        }
                    }
                }]
    }

}