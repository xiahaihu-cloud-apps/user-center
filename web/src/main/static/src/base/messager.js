import bootstrap from "bootstrap"
import BootstrapDialog from 'bootstrap3-dialog'
import 'bootstrap3-dialog/dist/css/bootstrap-dialog.min.css'

const Messager = {
    info: function(msg) {
        BootstrapDialog.show({
            title: "通知",
            message: msg,
            type: BootstrapDialog.TYPE_INFO,
            size: BootstrapDialog.SIZE_SMALL,
            autodestroy: true
        });
    },
    warn: function(msg) {
        BootstrapDialog.show({
            title: "警告",
            message: msg,
            type: BootstrapDialog.TYPE_WARNING,
            size: BootstrapDialog.SIZE_SMALL
        });
    },
    error: function(msg) {
        BootstrapDialog.show({
            title: "错误",
            message: msg,
            type: BootstrapDialog.TYPE_DANGER,
            size: BootstrapDialog.SIZE_SMALL
        })
    },
    success: function(msg) {
        BootstrapDialog.show({
            title: "错误",
            message: msg,
            type: BootstrapDialog.TYPE_SUCCESS,
            size: BootstrapDialog.SIZE_SMALL
        }) 
    }
}

export default Messager