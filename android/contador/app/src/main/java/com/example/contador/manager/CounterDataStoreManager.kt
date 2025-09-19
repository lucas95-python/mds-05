package com.example.teste.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "click_counter_settings")

class CounterDataStoreManager(private val context: Context) {

    companion object {
        val COUNTER_KEY = intPreferencesKey("click_counter")
    }

    val counterFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }

    suspend fun incrementCounter() {
        context.dataStore.edit { settings ->
            val currentValue = settings[COUNTER_KEY] ?: 0
            settings[COUNTER_KEY] = currentValue + 1
        }
    }
}