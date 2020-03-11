package com.drcom.ListViewDemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String name ;
    private int price ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * writeToParcel
     * 默认生成的模板类的对象只支持为 in 的定向 tag
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.price);
    }
    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * 用 out 或者 inout 来作为它的定向 tag
     * @param dest
     */
    public void readFromParcel(Parcel dest){
        name = dest.readString();
        price = dest.readInt();
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.name = in.readString();
        this.price = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
