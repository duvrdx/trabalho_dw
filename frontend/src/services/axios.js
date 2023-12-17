import axios from 'axios'

// const baseURL = process.env.VUE_APP_API_BASE_URL
const baseURL = 'http://localhost:8080/api/v1/'

const instance = axios.create({
    baseURL,
    timeout: 5000,  // Tempo limite de 5 segundos (ajuste conforme necessário)
})

export default instance
