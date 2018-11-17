package com.codylab.foodie.core.extension

import android.Manifest
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable

fun FragmentActivity.requestPermission(permission: String): Observable<Boolean> {
    return RxPermissions(this).request(permission)
}

fun FragmentActivity.requestLocationPermission(): Observable<Boolean> {
    return this.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showError(throwable: Throwable) {
    Toast.makeText(this, throwable.toString(), Toast.LENGTH_LONG).show()
}

fun Context.showError(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
