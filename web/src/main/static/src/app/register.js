import axios from '../axios'
// import axios from 'axios'
import Vue from 'vue'
import APIS from '../base/apis'

import VeeValidate from 'vee-validate'
Vue.use(VeeValidate)

const loginForm = new Vue({
    el: "#registerForm",
    data: {
        csrfName: "_csrf",
        csrfToken: "",
        captchaImage: "",
        password: "",
        retypePassword: ""
    },
    computed: {
        retypePasswordError: function() {
            return this.errors.has("retypePassword") || this.password != this.retypePassword;
        }
    },
    mounted: function() {
        this.reloadCaptchaImage();
    },
    methods: {
        reloadCaptchaImage: function() {
            const _this = this;
            axios.get(APIS.CAPTCHA).then(function(response) {
                _this.$data.captchaImage = ENV.baseUrl + response.data;
            })
        },
        submitForm: function(event) {
            const _this = this;
            this.$validator.validateAll().then(function(result) {
                if (!result || _this.password != _this.retypePassword) {
                    event.preventDefault();
                }
            })
        }
    }
});

axios.get(APIS.CSRF).then(function(response) {
    loginForm.$data.csrfName = response.data.parameterName;
    loginForm.$data.csrfToken = response.data.token;
});