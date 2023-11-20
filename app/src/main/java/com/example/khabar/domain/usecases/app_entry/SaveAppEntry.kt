package com.example.khabar.domain.usecases.app_entry

import com.example.khabar.domain.manager.LocalUserManager

class SaveAppEntry (
    private val localUserManager: LocalUserManager){
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}