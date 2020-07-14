<template>
  <div class="contour" style="width: 1280px;text-align: center;margin: 0 auto;">
    <el-row :gutter="20" style="margin: 0;padding: 10px;background-color: black;color: white;">
      <el-col :span="3" style="padding: 0 0 0 20px; text-align: left;">
        <span class="curPoi" style="font-size: 28px;vertical-align: middle;font-style: italic;">CYSN</span>
      </el-col>
      <el-col :span="15" style="padding: 0px;">
        <el-row :gutter="20" style="margin: 0px;">
          <el-col :span="8" style="padding: 0px;line-height: 40px;">
            <span class="curPoi">
              <img src="../assets/img/location.png" alt="" width="10px;" style="vertical-align: middle;" />
              <span style="font-size: 14px;">Los Abgeles</span>
              <img src="../assets/img/triangle.png" alt="" width="10px;" style="vertical-align: middle;" />
            </span>
          </el-col>
          <el-col :span="16" style="padding: 0px;line-height: 40px;">
            <div class="flexNowarp" style="font-size: 14px;">
              <div class="flexItem curPoi">Live Concerts</div>
              <div class="flexItem curPoi">Movies</div>
              <div class="flexItem curPoi">Drama</div>
              <div class="flexItem curPoi">Football</div>
              <div class="flexItem curPoi">Basketball</div>
            </div>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="6" style="padding: 0px 20px 0px 0px;text-align: right;">
        <div class="curPoi" v-if="Object.keys(user).length === 0">
          <img src="../assets/img/login.png" alt="" width="40px;" style="vertical-align: middle; margin: 0 10px 0 0" @click="dialogVisible = true" />
          <span @click="dialogVisible = true">Sign In/Sign Up</span>
        </div>
        <div class="curPoi" v-else>
          <router-link id="rlink" to="/myCenter" style="text-decoration: none;">
            <el-avatar src="../assets/img/Jannabi.png" :size="40" alt="" width="40px;" style="vertical-align: middle; margin: 0 10px 0 0"></el-avatar>
            <span style="line-height: 40px;">{{user.user_name}}</span>
          </router-link>
        </div>
      </el-col>
    </el-row>
    <el-carousel indicator-position="none" height="700px" style="background-color: black">
      <el-carousel-item v-for="(item, index) in carouselList" :key="index">
        <img :src="item.img" alt="" height="100%" />
      </el-carousel-item>
    </el-carousel>
    <div>
      <div style="background-color: white;padding: 80px 50px;">
        <div style="text-align: left;">
          <img src="../assets/img/type.png" alt="" width="40px;" />
          <span style="font-size: 22px;padding-left: 10px;font-style:italic">Live Concerts</span>
        </div>
        <el-row style="height: 666px;">
          <el-col :span="14" style="height: 100%;border-radius: 15px;overflow: hidden;">
            <img src="../assets/img/Up&Up.png" alt="" width="100%" height="100%" style="display: block;" />
          </el-col>
          <el-col :span="10" style="padding-left: 26px;">
            <div style="height: 320px;border-radius: 15px;margin-bottom: 26px;overflow: hidden;"><img src="../assets/img/Up&Up.png" alt="" width="100%" height="100%" style="display: block;" /></div>
            <div style="height: 320px;border-radius: 15px;overflow: hidden;"><img src="../assets/img/Up&Up.png" alt="" width="100%" height="100%" style="display: block;" /></div>
          </el-col>
        </el-row>
        <div class="flexNowarp" style="margin-top: 30px;">
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/FindMeSomeBodyToLove.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Find Me Some Body to Love</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/FindMeSomeBodyToLove.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Find Me Some Body to Love</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/FindMeSomeBodyToLove.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Find Me Some Body to Love</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/FindMeSomeBodyToLove.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Find Me Some Body to Love</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
        </div>
        <div style="padding-top: 50px;">
          <button style="background-color: #fff;border: 1px solid #e5e5e5;border-radius: 22px;padding: 15px 25px;cursor: pointer;">Load More</button>
        </div>
      </div>
    </div>
    <div>
      <div style="background-color: white;padding: 80px 50px;">
        <div style="text-align: left;">
          <img src="../assets/img/type.png" alt="" width="40px;" />
          <span style="font-size: 22px;padding-left: 10px;font-style:italic">Movies</span>
        </div>
        <el-row style="height: 666px;">
          <el-col :span="16" style="height: 100%;border-radius: 15px;overflow: hidden;">
            <img src="../assets/img/StarWars.png" alt="" width="100%" height="100%" style="display: block;" />
          </el-col>
          <el-col :span="8" style="padding-left: 26px;">
            <div style="height: 320px;border-radius: 15px;margin-bottom: 26px;overflow: hidden;"><img src="../assets/img/StarWars.png" alt="" width="100%" height="100%" style="display: block;" /></div>
            <div style="height: 320px;border-radius: 15px;overflow: hidden;"><img src="../assets/img/StarWars.png" alt="" width="100%" height="100%" style="display: block;" /></div>
          </el-col>
        </el-row>
        <div class="flexNowarp" style="margin-top: 30px;">
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/joker.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Joker</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/joker.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Joker</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/joker.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Joker</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/joker.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Joker</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
        </div>
        <div style="padding-top: 50px;">
          <button style="background-color: #fff;border: 1px solid #e5e5e5;border-radius: 22px;padding: 15px 25px;cursor: pointer;">Load More</button>
        </div>
      </div>
    </div>
    <div style="margin-top: 80px;padding: 90px 0px;background-color: #f6f6f6;">
      <div style="font-size: 24px;color: #121212;font-family: SourceHanSans-Medium;font-weight: bold;">Subscribe&nbsp;to&nbsp;Out&nbsp;Newsletter</div>
      <div style="font-size: 16px;color: #666666;padding-top: 20px;text-align: center"> <div style="width: 400px; display: inline-block">Get&nbsp;the&nbsp;latest product updates, company news, and special offers delivered right to your inbox.</div> </div>
      <div style="padding-top: 40px;">
        <input type="text" placeholder="Enter your email" style="line-height: 50px;font-size: 16px;border-radius: 25px;border: none;width: 270px;padding-left: 30px;" />
        <button style="background: #1A1A1A;box-shadow: 0 2px 4px 0 rgba(42,42,42,0.20);border-radius: 25px;line-height: 50px;width: 140px;color: #fff;font-size: 16px;border: none;margin-left: 20px;">SUBSCRIBE</button>
      </div>
    </div>
    <div style="margin-top: 50px;padding: 40px 50px;border-top: 1px solid #121212;">
      <el-row>
        <el-col :span="8" style="text-align: left;font-size: 16px;color: #121212;">
          <div class="flexNowarp" style="font-size: 14px;">
            <div class="flexItem curPoi">@CYSN copy @2020</div>
          </div>
        </el-col>
        <el-col :span="8" style="text-align: left;font-size: 16px;color: #121212;">
          <div class="flexNowarp" style="font-size: 14px;">
            <div class="flexItem curPoi">Help</div>
            <div class="flexItem curPoi">Contract</div>
            <div class="flexItem curPoi">About us</div>
          </div>
        </el-col>
        <el-col :span="8" style="text-align: right;">
          <img src="../assets/img/twitter.png" alt="" width="22px" height="20px" style="padding-right: 30px;" />
          <img src="../assets/img/ins.png" alt="" width="20px" height="20px" style="padding-right: 30px;" />
          <img src="../assets/img/facebook.png" alt="" width="20px" height="20px" />
        </el-col>
      </el-row>
    </div>

    <el-dialog :visible.sync="dialogVisible">

      <el-row style="height: 100%;">
        <el-col :span="11" style="height: 100%;">
          <img src="../assets/img/joker.png" alt="" width="100%" height="100%" />
        </el-col>
        <el-col :span="13">
          <div style="text-align: right; padding: 20px 20px 0px 0px;"><img @click="dialogVisible = false"  src="../assets/img/close.png" alt="" width="20px" height="20px" class="curPoi" /></div>
          <div v-if="showLogin">
            <el-row style="padding: 50px 40px;">
              <el-col :span="12" class="curDefault" style="font-size: 22px;font-weight: bold;text-align: left;">Sign In to CYSN</el-col>
              <el-col :span="12" class="curPoi" style="font-size: 14px;color: blue;text-align: right;" @click.native="showLogin=false"><span>Sign&nbsp;up</span></el-col><!-- @click="checkType" -->
            </el-row>
            <el-input placeholder="Email" v-model="user.email" style="margin: 0px 50px;width: 80%;" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/account.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Password" v-model="user.password" style="margin: 20px 50px 0px;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <button class="curPoi" @click="login" style="line-height: 44px;width: 80%;margin: 30px auto 0px;font-size: 15px;background-color: black;color: #fff;border-radius: 5px;border: none;">Sign&nbsp;in</button>
            <div class="curPoi" style="text-align: right;padding: 10px 50px 0px 0px;color: #4386F5;">Forget&nbsp;Password?</div>
            <div class="curDefault" style="padding: 40px 0px 20px 50px;font-family: PingFangSC-Regular;font-size: 15px;color: #B3B3B3;text-align: left;">Or</div>
            <button class="curPoi" style="background-color: #4386F5;border: none;border-radius: 4px;font-size: 15px;color: #FFFFFF;line-height:44px;width: 300px;"><img src="../assets/img/google.png" alt="" width="24px;" height="24px" style="vertical-align:middle;" />Sign&nbsp;in&nbsp;with&nbsp;Google</button>
            <button class="curPoi" style="border: none;padding: 12px;margin-left: 10px;border-radius: 4px;"><img src="../assets/img/facebook_login.png" alt="" width="20px" height="20px" /></button>
          </div>
          <div v-if="!showLogin">
            <el-row style="padding: 50px 40px;">
              <el-col :span="12" class="curDefault" style="font-size: 22px;font-weight: bold;text-align: left;">Sign Up to CYSN</el-col>
            </el-row>
            <el-input placeholder="Username" v-model="register.user_name" style="margin: 0px 50px;width: 80%;" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/account.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Enter email" v-model="register.email" style="margin: 20px 50px 0px;width: 80%;" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Password" v-model="register.password" style="margin: 20px 50px 0px;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Confirm Password" v-model="confirm_password" style="margin: 20px 50px 0px;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <button class="curPoi" style="line-height: 44px;width: 80%;margin: 30px auto 0px;font-size: 15px;background-color: black;color: #fff;border-radius: 5px;border: none;" @click="signup">Create Account</button>
            <div class="curDefault" style="padding: 40px 0px 20px 50px;font-family: PingFangSC-Regular;font-size: 15px;color: #B3B3B3;text-align: left;">Or</div>
            <button class="curPoi" style="background-color: #4386F5;border: none;border-radius: 4px;font-size: 15px;color: #FFFFFF;line-height:44px;width: 300px;"><img src="../assets/img/google.png" alt="" width="24px;" height="24px" style="vertical-align:middle;" />Sign&nbsp;in&nbsp;with&nbsp;Google</button>
            <button class="curPoi" style="border: none;padding: 12px;margin-left: 10px;border-radius: 4px;"><img src="../assets/img/facebook_login.png" alt="" width="20px" height="20px" /></button>
          </div>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      carouselList: [
        {img: require('../assets/img/ColdplayPara.png')},
        {img: require('../assets/img/FindMeSomeBodyToLove.png')},
        {img: require('../assets/img/Joker2.png')}
      ],
      dialogVisible: false,
      user: {},
      register: {},
      showLogin: true,
      confirm_password: null
    }
  },
  mounted: function () {},
  methods: {
    // login
    login() {
      // send request
      this.$http.post("/user/login", this.user).then(
              res=>{
                if(res){
                  this.user = res.user;
                  this.$message({
                    message: 'Login successfully!',
                    type: 'success'
                  });
                  localStorage.setItem("user", JSON.stringify(res));
                  this.dialogVisible = false;
                }
                else {
                  alert(res.msg)
                }
      })
    },
    signup() {
      if(this.register.email !== null && this.register.email !== '' && this.register.email !== undefined) {
        if(this.register.password !== null && this.register.password !== '' && this.register.password !== undefined){
          if(this.confirm_password !== null && this.confirm_password !== '' && this.confirm_password !== undefined){
            if(this.register.password === this.confirm_password) {
              let obj = {
                email: this.register.email,
                user_name: this.register.user_name,
                password: this.register.password
              };
              this.$http.post("/user/register", obj).then(
                      res=>{
                        if(res){
                          this.user = obj;
                          this.$message({
                            message: 'Register successfully!',
                            type: 'success'
                          });
                          localStorage.setItem("user", JSON.stringify(obj));
                          this.dialogVisible = false;
                        }
                        else {
                          alert(res.msg)
                        }
                      }
              )

            }
          }
        }
      } else {
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

<style scope>
  .el-dialog {
    border-radius: 25px;
    overflow: hidden;
    width: 900px;
    height: 600px;
  }

  .el-dialog__header {
    display: none;
  }

  .el-dialog__body {
    padding: 0px;
    height: 100%;
  }
</style>
