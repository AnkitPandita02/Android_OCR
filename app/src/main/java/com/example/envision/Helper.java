package com.example.envision;

import android.os.AsyncTask;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Helper extends AsyncTask<Void,Void, Boolean> {
    interface Consumer{
        void accept(boolean internet);
    }

    Consumer consumer;

    public Helper(Consumer consumer)
    {
        this.consumer=consumer;
        execute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Socket socket=new Socket();
            socket.connect(new InetSocketAddress("google.com",80),1500);
            socket.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        consumer.accept(aBoolean);
    }
}
