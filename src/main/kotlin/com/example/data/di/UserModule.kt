package com.example.data.di

import com.example.data.infra.DatabaseModule
import com.example.data.order.database.OrderDao
import com.example.data.order.repository.OrderRepositoryImpl
import com.example.data.user.database.UserDao
import com.example.data.user.repository.UserRepositoryImpl
import com.example.domain.core.ConsoleLogger
import com.example.domain.core.Logger
import com.example.domain.order.repository.OrderRepository
import com.example.domain.order.usecase.OrderUseCase
import com.example.domain.users.repository.UsersRepository
import com.example.domain.users.usecase.UsersUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {


    @Provides
    @Singleton
    fun provideDatabaseModule(): DatabaseModule {
        return DatabaseModule()
    }

    @Provides
    @Singleton
    fun provideOrderDao(databaseModule: DatabaseModule): OrderDao {
        return OrderDao(databaseModule)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(orderDao: OrderDao): OrderRepository {
        return OrderRepositoryImpl(orderDao)
    }

    @Provides
    @Singleton
    fun provideCreateOrderUseCase(orderRepository: OrderRepository): OrderUseCase{
        return OrderUseCase(orderRepository)
    }

    @Provides
    @Singleton
    fun provideUserDao(databaseModule: DatabaseModule): UserDao {
        return UserDao(databaseModule)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UsersRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideCreateUserUseCase(userRepository: UsersRepository): UsersUseCase{
        return UsersUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return ConsoleLogger()
    }
}