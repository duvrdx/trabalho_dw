import api from '@api'

export async function getItem(id) {
    return await api.get(`/project/${id}/`)
}

export async function register(name) {
    return await api.post('/project/', { name })
}

export async function update(id, name) {
    return await api.put(`/project/${id}/`, { name })
}

export async function list() {
    return await api.get('/project')
}

export async function deleteItem(id) {
    return await api.delete(`/project/${id}/`)
}

export default {
    getItem,
    register,
    update,
    list,
    deleteItem
}
