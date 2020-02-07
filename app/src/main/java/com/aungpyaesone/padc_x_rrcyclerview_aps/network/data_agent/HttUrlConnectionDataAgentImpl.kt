package com.aungpyaesone.padc_x_rrcyclerview_aps.network.data_agent
import android.os.AsyncTask
import android.util.Log
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.BASE_URL
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.END_POINT
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.EN_CONNECTION_ERROR
import com.aungpyaesone.padc_x_rrcyclerview_aps.Utils.PARAM_ACCESS_TOKEN
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.network.responses.GetAllNewsResponse
import com.google.gson.Gson
import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

object HttUrlConnectionDataAgentImpl : NewsDataAgent {
    override fun getAllNews(accessToken: String,
                            onSuccess: (List<NewsVO>)->Unit,
                            onFailure: (String)->Unit)
    {
        GetNewsTask(accessToken = accessToken,
                    onSuccess = onSuccess,
                    onFailure = onFailure).execute()
    }

    class GetNewsTask(val accessToken: String,val onSuccess:(List<NewsVO>)->Unit,
                      val onFailure: (String) -> Unit) : AsyncTask<Void,Void,GetAllNewsResponse?>(){
        override fun doInBackground(vararg params: Void?): GetAllNewsResponse? {
            val url: URL
            var reader : BufferedReader? = null
            val stringBuilder : StringBuilder

            try{
                url = URL(BASE_URL + END_POINT)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"

                connection.readTimeout = 1500
                connection.doInput = true
                connection.doOutput = true

                val param = ArrayList<NameValuePair>()
                param.add(
                    BasicNameValuePair(
                        PARAM_ACCESS_TOKEN,
                        accessToken
                    )
                )

                val outputStream = connection.outputStream
                val writer = BufferedWriter(OutputStreamWriter(outputStream,"UTF-8"))
                writer.write(getQuery(param))
                writer.flush()
                writer.close()
                outputStream.close()
                connection.connect()

                reader = BufferedReader(
                    InputStreamReader(connection.inputStream)
                )
                stringBuilder = StringBuilder()
                for (line in reader.readLines()){
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                return Gson().fromJson(responseString,GetAllNewsResponse::class.java)
            }
            catch(e: Exception) {
                e.printStackTrace()
                Log.e("News Error", e.message ?: "")
            }
            finally {
                if (reader!= null)
                {
                    try {
                        reader.close()
                    }
                    catch (ioe: IOException){
                        ioe.printStackTrace()
                    }
                }
            }
            return null

        }
        @Throws(UnsupportedEncodingException::class)
        private fun getQuery(params:List<NameValuePair>):String{
            val result = StringBuilder()
            var first = true

            for (pair in params)
            {
                if (first)
                    first = false
                else
                    result.append("&")
                result.append(URLEncoder.encode(pair.name,"UTF-8"))
                result.append("=")
                result.append(URLEncoder.encode(pair.value,"UTF-8"))
            }
            return result.toString()
        }

        override fun onPostExecute(result: GetAllNewsResponse?) {
            super.onPostExecute(result)
            if( result != null){
               if(result.isResponseOk()){
                  // onSuccess(result?.data?.toList() ?: arrayListOf())
                   result.data?.let {
                       onSuccess(it.toList())
                   }
               }
                else{
                    onFailure(result.message)
                }

            }else{
                onFailure(EN_CONNECTION_ERROR)
            }
        }

    }
}