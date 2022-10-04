package com.example.flixster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixter2.CastMember
import com.example.flixter2.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

class CastFragment(private val showId: String?) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cast_list, container, false)
        val castRv = view.findViewById<View>(R.id.castList) as RecyclerView
        loadCastAdapter(castRv)
        return view
    }

    private fun loadCastAdapter(castRv: RecyclerView) {
        lateinit var models: List<CastMember>
        val client = AsyncHttpClient()
        client[
                "https://api.themoviedb.org/3/tv/"+ showId + "/credits?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US",
                object : JsonHttpResponseHandler()

                {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        val resultsJSON = json.jsonObject.get("cast").toString()

                        val gson = Gson()
                        val arrayCastType = object : TypeToken<List<CastMember>>() {}.type
                        models = gson.fromJson(resultsJSON, arrayCastType)
                        castRv.adapter = CastRVA(models)
                        Log.d("CastFragment1", "response successful")
                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        t?.message?.let {
                            Log.e("CastFragment1", errorResponse)
                        }
                    }
                }]
//        client[
//                "https://api.themoviedb.org/3/tv/"+ showId + "/credits?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US",
//                object : JsonHttpResponseHandler()
//
//                {
//                    override fun onSuccess(
//                        statusCode: Int,
//                        headers: Headers,
//                        json: JsonHttpResponseHandler.JSON
//                    ) {
//                        val resultsJSON = json.jsonObject.get("cast").toString()
//
//                        val gson = Gson()
//                        val arrayCastType = object : TypeToken<List<CastMember>>() {}.type
//                        models = gson.fromJson(resultsJSON, arrayCastType)
//                        Log.d("CastFragment2", "response successful")
//                    }
//
//                    override fun onFailure(
//                        statusCode: Int,
//                        headers: Headers?,
//                        errorResponse: String,
//                        t: Throwable?
//                    ) {
//                        t?.message?.let {
//                            Log.e("CastFragment2", errorResponse)
//                        }
//                    }
//                }]
//        castRv.adapter = CastRVA(models)
    }

}