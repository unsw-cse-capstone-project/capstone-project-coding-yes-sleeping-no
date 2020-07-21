<template>
    <div class="pwdbox">
        <h2>Foget password</h2>
        <el-input v-model="email" placeholder="Input your E-mail" style="width: 350px" clearable></el-input>
        <el-button style="margin: 20px" type="text" @click="sendEmail" >Send verification</el-button>
        <el-input v-model="verificationCode" placeholder="Input your verification code" style="width: 350px" clearable></el-input>
        <el-input v-model="password" placeholder="Input your new password" style="width: 350px; margin-top: 20px" show-password clearable></el-input>
        <el-input v-model="confirm_password" placeholder="Confirm your new password" style="width: 350px; margin-top: 20px" show-password clearable></el-input>
        <el-row>
            <el-popconfirm
                    confirmButtonText='Confirm'
                    cancelButtonText='Cancel'
                    icon="el-icon-info"
                    iconColor="red"
                    title="Confirm to change the password？"
            >
                <el-button type="primary" round style="margin-top: 40px; margin:50px" slot="reference" @click="upload">Confirm</el-button>
            </el-popconfirm>
            <el-button round>Cancel</el-button>
        </el-row>
    </div>

</template>

<script>
    export default {
        data () {
            return {
                email: '',
                verificationCode: '',
                password: '',
                confirm_password: ''
            }
        },
        methods: {
            handleClose (done) {
            },
            sendEmail(){
                let email = {
                    email: this.email
                };
                this.$http.post("/user/sendEmail", email).then(
                    res=>{
                        if(res.state){
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                        }
                        else {
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            });
                        }
                    })
            },
            upload(){
                if(this.verificationCode !== null && this.verificationCode !== '' && this.verificationCode !== undefined) {
                    if(this.password !== null && this.password !== '' && this.password !== undefined) {
                        if(this.confirm_password !== null && this.confirm_password !== '' && this.confirm_password !== undefined) {
                            if(this.password === this.confirm_password) {
                                let obj = {
                                    verificationCode: this.verificationCode,
                                    password: this.password
                                };
                                this.$http.post("/user/checkVerificationCode", obj).then(
                                    res=>{
                                        if(res.state){
                                            this.$message({
                                                message: res.msg,
                                                type: 'success'
                                            });
                                        }
                                        else {
                                            this.$message({
                                                message: res.msg,
                                                type: 'fail'
                                            });
                                        }
                                    })
                            }
                        }
                    }
                }
                else {
                    this.$message({
                        showClose: true,
                        message: 'Invalid email or password!',
                        type: 'error'
                    })
                }
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