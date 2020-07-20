<template>
  <div class="pwdbox">
    <h2>Reset password</h2>
    <el-input v-model="input1" placeholder="Input your new password" style="width: 350px; margin-top: 20px" show-password></el-input>
    <el-input v-model="input2" placeholder="Confirm your new password" style="width: 350px; margin-top: 20px" show-password></el-input>
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
  </div>

</template>

<script>
  export default {
    data () {
      return {
        input1: '',
        input2: '',
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