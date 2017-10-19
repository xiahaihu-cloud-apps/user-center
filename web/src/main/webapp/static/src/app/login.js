import axios from '../axios'

axios.get("http://localhost:8080/csrf").then(function(response) {
    console.debug(response);
});