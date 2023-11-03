import axios from 'axios'

const baseURL = 'http://localhost:8080/api/v1/'

const instance = axios.create({
    baseURL,
})

export default instance
