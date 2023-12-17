import api from '@api'

export async function getCategories() {
    return await api.get('/categories/epic')
}

export async function getItem(id) {
    return await api.get(`/epic/${id}`)
}

export async function register({
    title,
    description,
    relevance,
    epicTypeId,
    projectId,
    category,
    dependencies
}) {
    return await api.post('/epic/', {
	title,
	description,
	relevance,
	epicTypeId,
	projectId,
	category,
	dependencies
    })
}

export async function generate(id) {
    return await api.post(`/epic/generate/${id}`)
}

export async function update(
    id,
    {
	title,
	description,
	relevance,
	epicTypeId,
	projectId,
	category,
	dependencies
    }
) {
    return await api.put(`/epic/${id}/`, {
	title,
	description,
	relevance,
	epicTypeId,
	projectId,
	category,
	dependencies
    })
}

export async function list() {
    return await api.get('/epic')
}

export async function deleteItem(id) {
    return await api.delete(`/epic/${id}/`)
}

export async function getType(id) {
    return await api.get(`/epicType/${id}`)
}

export async function registerType({
    description,
}) {
    return await api.post('/epicType/', {
	description,
    })
}

export async function updateType(
    id,
    {
	description,
    }
) {
    return await api.put(`/epicType/${id}/`, {
	description,
    })
}

export async function listTypes() {
    return await api.get(`/epicType`)
}

export async function deleteType(id) {
    return await api.delete(`/epicType/${id}/`)
}

export default {
    getCategories,
    getItem,
    register,
    generate,
    update,
    list,
    deleteItem,
    getType,
    registerType,
    listTypes,
    updateType,
    deleteType
}
