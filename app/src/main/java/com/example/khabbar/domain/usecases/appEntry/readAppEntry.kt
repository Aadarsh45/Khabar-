package com.example.khabbar.domain.usecases.appEntry

import com.example.khabbar.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class readAppEntry(
    private val localUserManager: LocalUserManager
){
     operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()

    }
}