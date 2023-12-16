import api from '@api'

export async function getCategories() {
    return await api.get('/categories/userstory')
}

export async function getItem(id) {
    return await api.get(`/userStory/${id}`)
}

export async function register({
    description,
    title,
    relevance,
    epicId,
    userStoryTypeId
}) {
    return await api.post('/userStory/', {
	description,
	title,
	relevance,
	epicId,
	userStoryTypeId
    })
}

export async function update(
    id,
    {
	description,
	title,
	relevance,
	epicId,
	userStoryTypeId
    }
) {
    return await api.put(`/userStory/${id}/`, {
	id,
	description,
	title,
	relevance,
	epicId,
	userStoryTypeId
    })
}

export async function list() {
    return await api.get('/userStory')
}

export async function deleteItem(id) {
    return await api.delete(`/userStory/${id}/`)
}

export async function getType(id) {
    return await api.get(`/userStoryType/${id}`)
}

export async function registerType({
    description,
    epicTypeId
}) {
    return await api.post('/userStoryType/', {
	description,
	epicTypeId
    })
}

export async function updateType(
    id,
    {
	description,
	epicTypeId
    }
) {
    return await api.put(`/userStoryType/${id}/`, {
	description,
    })
}

export async function listTypes() {
    return await api.get(`/userStoryType`)
}

export async function deleteType(id) {
    return await api.delete(`/userStoryType/${id}/`)
}

export default {
    getCategories,
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
