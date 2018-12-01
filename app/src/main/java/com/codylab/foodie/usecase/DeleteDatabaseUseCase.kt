package com.codylab.foodie.usecase

import com.codylab.foodie.core.repository.DatabaseRepository
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteDatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    operator fun invoke(): Completable {
        return databaseRepository.deleteAll()
    }
}