package com.example.testtask.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.testtask.data.QuwiApiRepository
import com.example.testtask.data.response.Project
import com.example.testtask.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContentViewModel(
    private val quwiApiRepository: QuwiApiRepository
) : BaseViewModel() {
    private val _projects: MutableLiveData<List<Project>> =
        object : MutableLiveData<List<Project>>() {
            override fun onActive() {
                super.onActive()
                updateProjectList()
            }
        }
    val projects: LiveData<List<Project>> = _projects

    private fun updateProjectList(){
        loadProjects {
            _projects.postValue(it ?: listOf())
        }
    }

    fun updateName(id: Long, newName: String, doOnSuccess: () -> Unit, doOnError: () -> Unit) {
        runWithLoadingCoroutine({
            quwiApiRepository.update(id, newName)
        }, onSuccess = { result ->
            updateProjectList()

            withContext(Dispatchers.Main){
                doOnSuccess()
            }
        }, onError = {
            withContext(Dispatchers.Main){
                doOnError()
            }
        })
    }

    private fun loadProjects(onSuccessDo: (List<Project>) -> Unit) {
        runWithLoadingCoroutine(block = {
            quwiApiRepository.getProjects()
        }, onSuccess = {
            onSuccessDo(it.projects ?: listOf())
        })
    }
}