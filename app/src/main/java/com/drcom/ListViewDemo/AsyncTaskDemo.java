package com.drcom.ListViewDemo;

import android.os.AsyncTask;

/**
 * Created by Don on 2019/4/18.
 *  SerialExecutor：用于任务的排队，默认是串行的线程池
 *  THREAD_POOL_EXECUTOR：用于真正执行任务。
 *
 */
public class AsyncTaskDemo extends AsyncTask<Void,Integer,Integer> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return null;
    }
}
