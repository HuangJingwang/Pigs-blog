import axios from 'axios'
const requests = axios.create({
  baseURL: '',
  timeout: 5000,
})
export default requests