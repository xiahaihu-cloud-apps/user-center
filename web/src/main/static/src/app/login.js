import axios from '../axios'
import Vue from 'vue'
import APIS from '../base/apis'
import VeeValidate from 'vee-validate'

Vue.use(VeeValidate)

const loginForm = new Vue({
    el: "#loginForm",
    data: {
        csrfName: "_csrf",
        csrfToken: ""
    },
    methods: {
        submitForm: function(event) {
            this.$validator.validateAll().then(function(result) {
                if (!result) {
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

// function countStrLen(v) {
//     let len = 0;
//     let chineseMatches = v.match(/[\u4E00-\uFA29]/g);
//     len += chineseMatches == null ? 0 : chineseMatches.length * 2;
//     let withoutChinese = v.replace(/[\u4E00-\uFA29]/g, "");
//     len += withoutChinese.length;
//     return len;
// }

// let strlen = countStrLen("2000")
// console.info(strlen)