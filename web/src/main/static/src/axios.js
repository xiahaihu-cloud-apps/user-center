import Axios from 'axios'
export default Axios.create({
    baseURL: 'http://localhost:8080/',
    responseType: 'json',
    headers: {
        'Content-Type': 'application/json',
        'x-requested-with': 'XMLHttpRequest'
    }
})