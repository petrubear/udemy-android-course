package emg.example.myquizapp.service.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emg.example.myquizapp.service.QuestionsService
import emg.example.myquizapp.service.impl.FlagsQuestionService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providesQuestionService(): QuestionsService {
        return FlagsQuestionService()
    }
}