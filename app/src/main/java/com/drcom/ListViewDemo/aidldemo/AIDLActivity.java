package com.drcom.ListViewDemo.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.drcom.ListViewDemo.Book;
import com.drcom.ListViewDemo.BookManager;
import com.drcom.ListViewDemo.R;

import java.util.List;

public class AIDLActivity extends AppCompatActivity {
    private BookManager bookManager = null;
    private boolean mBound = false;
    private List<Book> mBooks;
    private Button btn ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_aidldemo);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook(v);
            }
        });
    }

    public void addBook(View view) {
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bookManager == null)
            return;

        Book book = new Book();
        book.setName("app yanfa ");
        book.setPrice(30);

        try {
            bookManager.addBook(book);
            Log.e(getLocalClassName(), book.toString());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setAction("com.lypeer.aidl");
        intent.setPackage("com.lypeer.ipcserver");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = BookManager.Stub.asInterface(service);
            mBound = true;
            if (bookManager != null) {
                try {
                    mBooks = bookManager.getBooks();
                    Log.e(getLocalClassName(), mBooks.toString());

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(getLocalClassName(), "service disconnected");
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

}
