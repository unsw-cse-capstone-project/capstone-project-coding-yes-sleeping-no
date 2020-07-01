<template>
  <div class="login">
    <el-row style="border: 1px solid #e5e5e5;">
      <el-col :span="9" class="login_pic">
        <img src="../assets/login_picture.jpg" alt="picture display failure" width="100%" height="100%">
      </el-col>
      <el-col :span="15" class="login_content">
        <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%)">
          <span class="login_title">
            <h4>Sign in to CYSN</h4>
          </span>
          <div>
            <el-input placeholder="Username or Email Address" prefix-icon="../assets/icon_login_account.png" v-model="name" class="input_style"></el-input>
            <span class="err-msg" v-if="error.name">{{error.name}}</span>
          </div>
          <div>
            <el-input placeholder="Password" prefix-icon="../assets/icon_login_password.png" v-model="password" class="input_style"></el-input>
          </div>
          <div>
            <el-button type="primary" @click="login" class="login_style">Sign in</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      name: '',
      password: '',
      error: {
        name: '',
        pwd: ''
      }
    }
  },
  methods: {
    login () {
      this.$router.push({
        name: 'index',
        params: {
          username: this.name
        }
      })
    }
  }
}
</script>

<style scoped>
  .login {
    display: table-cell;
    text-align: center;
    vertical-align: middle;
    width: 900px;
    height: 600px;
  }
  .login_pic .login_content {
    vertical-align: middle;
  }
  .login_content {
    background-color: white;
  }
  .input_style {
    width: 200px;
    margin-bottom: 10px;
  }
  .login_style {
    width: 200px;
  }
</style>
