package net.appsynth.basic

import android.os.Parcel
import android.os.Parcelable

class Card() : Parcelable {

    var name: String? = null
    var position: Int? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        position = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(position)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}