package com.example.khabbar.domain.usecases.appEntry

import com.example.khabbar.domain.manager.LocalUserManager

class saveAppEntry (
    private val localUserManager: LocalUserManager
){
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}