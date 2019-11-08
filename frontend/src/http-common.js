import axios from 'axios';


export default axios.create({
    baseUrl : 'http://localhost:8181/',
    timeout : 1000
})