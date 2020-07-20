<template>
    <div class="pwdbox">
        <h2>Foget password</h2>
        <el-input v-model="input1" placeholder="Input your E-mail" style="width: 350px" clearable></el-input>
        <el-button style="margin: 20px" type="text" @click="dialogVisible = true" >Send verification</el-button>
        <el-input v-model="input2" placeholder="Input your verification code" style="width: 350px" clearable></el-input>
        <el-input v-model="input3" placeholder="Input your new password" style="width: 350px; margin-top: 20px" show-password clearable></el-input>
        <el-input v-model="input4" placeholder="Confirm your new password" style="width: 350px; margin-top: 20px" show-password clearable></el-input>
        <el-row>
            <el-popconfirm
                    confirmButtonText='Confirm'
                    cancelButtonText='Cancel'
                    icon="el-icon-info"
                    iconColor="red"
                    title="Confirm to change the password？"
            >
                <el-button type="primary" round style="margin-top: 40px; margin:50px" slot="reference">Confirm</el-button>
            </el-popconfirm>
            <el-button round>Cancel</el-button>
        </el-row>
        <el-dialog
                title="Note"
                :visible.sync="dialogVisible"
                width="50%"
                :before-close="handleClose">
            <span>Verification code has been sent to your E-mail</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogVisible = false">Confirm</el-button>
            </span>
        </el-dialog>
    </div>

</template>

<script>
    export default {
        data () {
            return {
                input1: '',
                input2: '',
                input3: '',
                input4: '',
                dialogVisible: false
            }
        },
        methods: {
            handleClose (done) {
            }
        }
    }
</script>

<style scoped>
    .pwdbox{
        width: 600px;
        height: 500px;
        background: #f3f3f3;
        box-shadow: 0px 15px 25px 0 rgba(0,0,0,0.2);
        padding: 40px;
        box-sizing: border-box;
        position: absolute;
        top: 50%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        -moz-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        -o-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
    h2{
        text-align: center;
        color: dimgrey;
        margin-bottom: 30px;
    }
</style>