package com.example.hwtext

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Person(val name: String, val surname: String) : Parcelable