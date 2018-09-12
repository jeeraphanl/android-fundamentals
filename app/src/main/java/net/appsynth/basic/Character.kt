package net.appsynth.basic

import android.os.Parcel
import android.os.Parcelable

class Character() : Parcelable {

    var name: String? = null
    var thumb: Int? = null
    var desc: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        thumb = parcel.readValue(Int::class.java.classLoader) as? Int
        desc = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(thumb)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}