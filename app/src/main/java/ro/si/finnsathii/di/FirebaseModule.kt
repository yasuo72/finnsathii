package ro.si.finnsathii.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.si.finnsathii.data.remote.FirebaseDataSource
import ro.si.finnsathii.data.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseDataSource(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): FirebaseDataSource = FirebaseDataSource(auth, firestore)

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseDataSource: FirebaseDataSource
    ): AuthRepository = AuthRepository(firebaseDataSource)
}
