<template>
  <div class="pwdbox">
    <h2>Reset password</h2>
    <el-input v-model="password" placeholder="Input your new password" style="width: 350px; margin-top: 20px" show-password></el-input>
    <el-input v-model="confirm_password" placeholder="Confirm your new password" style="width: 350px; margin-top: 20px" show-password></el-input>
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
      <router-link to="/">
        <el-button round>Cancel</el-button>
      </router-link>
    </el-row>
  </div>

</template>

<script>
  export default {
    data () {
      return {
        password: '',
        confirm_password: ''
      }
    },
    methods: {
      upload() {
        if(this.password !== null && this.password !== '' && this.password !== undefined) {
          if (this.confirm_password !== null && this.confirm_password !== '' && this.confirm_password !== undefined) {
            if (this.password === this.confirm_password) {
              // let obj = {
              //   'oldPassword': this.user.password,
              //   'newPassword': this.password,
              // }
              let obj = {
                newPassword: this.password,
              };

              this.$http.post("/user/resetPassword", obj).then(
                      res=>{
                        if(res.state){
                          this.$message({
                            message: res.msg,
                            type: 'success'
                          });
                          localStorage.setItem("user", JSON.stringify(res.user));
                        }
                        else {
                          this.$message({
                            message: res.msg,
                            type: 'fail'
                          });
                        }
                      });
              location.href = '/';
            }
          }
        }

      }
    }
  }
</script>

<style scoped>
  .pwdbox{
    width: 430px;
    height: 400px;
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