import axios from '../axios'
import Vue from 'vue'
import APIS from '../base/apis'
import CONSTANTS from '../base/constants'
// import Messager from '../base/messager'
import Messager from '../base/NotifyMessager'

import VeeValidate from 'vee-validate'
Vue.use(VeeValidate)

const registerForm = new Vue({
    el: "#registerForm",
    data: {
        form: {
            name: "",
            email: "",
            captcha: "",
            password: "",
            retypePassword: ""
        },
        csrf: {
            headerName: "",
            token: ""
        },
        captchaImage: "",
    },
    computed: {
        retypePasswordError: function() {
            return this.errors.has("retypePassword") || this.form.password != this.form.retypePassword;
        }
    },
    mounted: function() {
        this.reloadCaptchaImage();
        this.getToken();
    },
    methods: {
        reloadCaptchaImage: function() {
            const _this = this;
            axios.get(APIS.CAPTCHA).then(function(response) {
                _this.$data.captchaImage = response.data;
                _this.$data.form.captcha = "";
            })
        },
        submitForm: function(event) {
            const _this = this;
            this.$validator.validateAll().then(function(result) {
                if (result && _this.form.password == _this.form.retypePassword) {
                    axios.post(APIS.REGISTER, JSON.stringify(_this.$data.form), {
                        headers: {
                            "X-CSRF-TOKEN": _this.$data.csrf.token
                        }
                    }).then(function(response) {
                        var result = response.data;
                        if (result.code == CONSTANTS.ResponseCode.SUCCESS) {
                            Messager.success("注册成功", function() {
                                window.location.href = result.data;
                            });
                        } else if (result.code == CONSTANTS.ResponseCode.INVALID_CSRF) {
                            Messager.error("页面已经过期，请重新刷新页面", function() {
                                window.location.reload();
                                _this.reloadCaptchaImage();
                                _this.getToken();
                            });
                        } else {
                            Messager.error(result.message);
                            _this.reloadCaptchaImage();
                            _this.getToken();
                        }
                    }).catch(function(error) {
                        console.debug(error);
                        _this.reloadCaptchaImage();
                        _this.getToken();
                    });
                }
                event.preventDefault();
            })
        },
        getToken: function() {
            axios.get(APIS.CSRF).then(function(response) {
                registerForm.$data.csrf.headerName = response.data.headerName;
                registerForm.$data.csrf.token = response.data.token;
            });
        }
    }
});
