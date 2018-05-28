package com.example.harish.grocery.model

import android.os.Parcel
import android.os.Parcelable

data class BriefProductInfo(val productId: String, val title: String, val price: String, val currency: String, val imgUrl: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(title)
        parcel.writeString(price)
        parcel.writeString(currency)
        parcel.writeString(imgUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BriefProductInfo> {
        override fun createFromParcel(parcel: Parcel): BriefProductInfo {
            return BriefProductInfo(parcel)
        }

        override fun newArray(size: Int): Array<BriefProductInfo?> {
            return arrayOfNulls(size)
        }
    }


}