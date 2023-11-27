package com.app.pexels.data.sharedpreferences

import android.content.SharedPreferences
import com.app.pexels.data.model.PhotoDataDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PhotosSharedPreferencesDelegate @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ReadWriteProperty<Any?, Set<PhotoDataDto>> {

    private var setPhotoData: MutableSet<PhotoDataDto> = getStoredData()

    private fun getStoredData(): MutableSet<PhotoDataDto> {
        val json = sharedPreferences.getString(PHOTO_DATA_KEY, null)
        return if (json != null) {
            Gson().fromJson(json, object : TypeToken<MutableSet<PhotoDataDto>>() {}.type)
        } else {
            mutableSetOf()
        }
    }

    private fun saveDataToSharedPreferences() {
        val json = Gson().toJson(setPhotoData)
        sharedPreferences.edit().putString(PHOTO_DATA_KEY, json).apply()
    }

    private inline fun editPhotoData(block: MutableSet<PhotoDataDto>.() -> Unit) {
        setPhotoData.apply(block)
        saveDataToSharedPreferences()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Set<PhotoDataDto> = setPhotoData

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<PhotoDataDto>) {
        setPhotoData = value.toMutableSet()
        saveDataToSharedPreferences()
    }

    fun savePhotoToFavorites(imageUrl: String, photographer: String) {
        val saveData = PhotoDataDto(imageUrl, photographer)
        editPhotoData { add(saveData) }
    }

    fun removePhotoFromFavorites(imageUrl: String, photographer: String) {
        val remove = PhotoDataDto(imageUrl, photographer)
        editPhotoData { remove(remove) }
    }

    fun checkPhotoInFavorites(imageUrl: String, photographer: String) =
        PhotoDataDto(imageUrl, photographer) in setPhotoData

    fun getBookmarksPhotos(): List<PhotoDataDto> = setPhotoData.toList()

    companion object {

        private const val PHOTO_DATA_KEY = "PhotoUrlKey"
    }
}
