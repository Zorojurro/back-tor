package com.example.domain.di


import com.example.data.di.AppModule
import com.example.domain.core.Logger
import com.example.domain.order.usecase.OrderUseCase
import com.example.domain.users.usecase.UsersUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun createUserUseCase(): UsersUseCase
    fun logger(): Logger
    fun createOrderUseCase(): OrderUseCase
}