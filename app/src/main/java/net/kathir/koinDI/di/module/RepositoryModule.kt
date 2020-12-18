package net.kathir.koinDI.di.module

import net.kathir.koinDI.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}