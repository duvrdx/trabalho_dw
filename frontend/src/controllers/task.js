import api from '@api'

export async function getItem(id) {
    return await api.get(`/task/${id}`)
}

export async function register({
    title,
    description,
    taskTypeId,
    userStoryId,
    dependencies
}) {
    return await api.post('/task/', {
	title,
	description,
	taskTypeId,
	userStoryId,
	dependencies
    })
}

export async function update(
    id,
    {
	title,
	description,
	taskTypeId,
	userStoryId,
	dependencies
    }
) {
    return await api.put(`/task/${id}/`, {
	title,
	description,
	taskTypeId,
	userStoryId,
	dependencies
    })
}

export async function list() {
    return await api.get('/task')
}

export async function deleteItem(id) {
    return await api.delete(`/task/${id}/`)
}

export async function getType(id) {
    return await api.get(`/taskType/${id}`)
}

export async function registerType({
    description,
    userStoryTypeId,
}) {
    return await api.post('/taskType/', {
	description,
	userStoryTypeId,
    })
}

export async function updateType(
    id,
    {
	description,
	userStoryTypeId,
    }
) {
    return await api.put(`/taskType/${id}/`, {
	description,
	userStoryTypeId,
    })
}

export async function listTypes() {
    return await api.get(`/taskType`)
}

export async function deleteType(id) {
    return await api.delete(`/taskType/${id}/`)
}

export default {
    getItem,
    register,
    update,
    list,
    deleteItem,
    getType,
    registerType,
    listTypes,
    updateType,
    deleteType
}
