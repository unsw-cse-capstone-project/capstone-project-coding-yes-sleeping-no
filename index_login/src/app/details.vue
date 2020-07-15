<template>
  <div class="contour" style="background-color: white;width: 1280px;text-align: center;margin: 30px auto;">
    <el-row :gutter="20" style="margin: 0px;padding: 10px;background-color: white;color: black;">
      <el-col :span="16" style="padding: 0px;">
        <el-row :gutter="20" style="margin: 0px;">
          <el-col :span="8" style="padding: 0px;">
            <span class="curPoi" style="font-size: 28px;vertical-align: middle;font-style: italic;">CYSN</span>
            <span class="curPoi">
              <img src="../assets/img/location.png" alt="" width="10px;" style="vertical-align: middle;" />
              <span style="font-size: 14px;">Sydney</span>
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
      <el-col :span="8" style="padding: 0px 20px 0px 0px;text-align: right;">
        <div class="curPoi">
          <img src="../assets/img/login.png" alt="" width="40px;" style="vertical-align: middle;" @click="signInVisible = true" />
          <span @click="signInVisible = true">Sign In/Sign Up</span>
        </div>
      </el-col>
    </el-row>

    <el-row style="padding: 30px; height: 666px">
      <el-col :span="8" style="height: 100%; border-radius: 15px; overflow: hidden;">
        <img src="../assets/img/Joker2.png" alt="" width="100%" height="100%" style="display: block;" />
      </el-col>
      <el-col :span="7" :offset="1" style="height: 100%;">
        <el-row style="text-align: left; padding: 5px">
          <span style="font-size: 32px;">Joker (2019)</span>
        </el-row>
        <el-row style="text-align: left;padding: 10px">
          <span>*RATING STAR IMAGES*</span>
        </el-row>
        <el-row style="text-align: left; padding: 10px; color: grey">
          <span>Release Time: 2019</span>
        </el-row>
        <el-row style="text-align: left; padding: 10px;  color: grey">
          <span>Introduction: 2019</span>
        </el-row>
        <el-row style="text-align: left; padding: 10px">
          <el-col :span="6" style="padding: 10px 0px">
            <span>Location: </span>
          </el-col>
          <el-col :span="18">
            <el-select v-model="loc_value" placeholder="Select">
             <el-option
               v-for="item in location_options"
               :key="item.loc_value"
                :label="item.label"
               :value="item.loc_value">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
        <el-row style="text-align: left; padding: 10px">
          <el-col :span="6" style="padding: 10px 0px">
            <span>Date: </span>
          </el-col>
          <el-col :span="18">
            <el-select v-model="value" placeholder="Select">
              <el-option
                v-for="item in date_options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
        <el-row style="text-align: left; padding: 10px">
          <el-col :span="6" style="padding: 10px 0px">
            <span>Time: </span>
          </el-col>
          <el-col :span="18">
            <el-select v-model="time_value" placeholder="Select">
              <el-option
                v-for="item in time_options"
                :key="item.time_value"
                :label="item.label"
                :value="item.time_value">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
        <el-row style="text-align: left; padding: 5px">
          <el-col :span="6" style="padding: 10px 0px">
            <span style="font-size: 24px">Price: </span>
          </el-col>
          <el-col :span="18" style="padding:10px 0px">
            <span style="color: red; font-size: 24px">$200</span>
          </el-col>
        </el-row>
        <el-row style="text-align: left; padding: 5px">
          <el-button type="primary">Book and Pay</el-button>
        </el-row>
      </el-col>
      <el-col :span="8" style="=height: 100%; border-radius: 15px; padding: 15px; background-color: lightgrey">
        <el-row>
          <span style="font-size: 22px; padding-left: 10px; font-style: italic;">Description</span>
        </el-row>
        <el-row style="padding: 15px">
          <span style="font-size: 18px;">This is the introduction of the event details</span>
        </el-row>      
      </el-col>
    </el-row>

    <el-row>
        <el-col :span="18" style="padding: 30px">
          <div style="margin: 15px; text-align: left;">
            <span style="font-size: 24px">Event Images</span>  
          </div>
          <div>
            <el-col style="hieght: 100%; border-radius: 15px; overflow: hidden">
              <img src="../assets/img/StarWars.png" alt="" width="100%" height="100%" style="display: block;">
            </el-col>
          </div>

        </el-col>

        <el-col :span="6" style="padding: 30px">
          <div style="margin: 15px; text-align: left;">
            <span style="font-size: 24px;">Recommended for you</span>
          </div>
          <div style="text-align: left; height: 160px">
            <el-col :span="8" style="height: 100%; border-radius: 5px; overflow: hidden">
              <img src="../assets/img/Joker2.png" alt="" width="100%" height="100%" style="display: block;" />
            </el-col>
            <el-col :span="15" :offset="1">
              <el-row>
                <span style="font-size: 24px">Joker (2019)</span>
              </el-row>
              <el-row>
                <span style="color: grey">Description of event...</span>
              </el-row>
              <el-row>
                <span style="color: grey">Date of the event...</span>
              </el-row>
              <el-row>
                <span style="color: red">Price from $200</span>
              </el-row>

            </el-col>
          </div>
        </el-col>


    </el-row>


    
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

    <el-dialog :visible.sync="signInVisible">

      <el-row style="height: 100%;">
        <el-col :span="11" style="height: 100%;">
          <img src="../assets/img/joker.png" alt="" width="100%" height="100%" />
        </el-col>
        <el-col :span="13">
          <div style="text-align: right; padding: 20px 20px 0px 0px;"><img @click="signInVisible = false"  src="../assets/img/close.png" alt="" width="20px" height="20px" class="curPoi" /></div>
          <div v-if="showLogin">
            <el-row style="padding: 50px 40px;">
              <el-col :span="12" class="curDefault" style="font-size: 22px;font-weight: bold;text-align: left;">Sign In to CYSN</el-col>
              <el-col :span="12" class="curPoi" style="font-size: 14px;color: blue;text-align: right;"><span @click="signUpVisible=true, signInVisible=false">Sign&nbsp;up</span></el-col><!-- @click="checkType" -->
            </el-row>
            <el-input placeholder="Username or Email Address" v-model="account" style="margin: 0px 50px;width: 80%;" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/account.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Password" v-model="pwd" style="margin: 20px 50px 0px;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <button class="curPoi" style="line-height: 44px;width: 80%;margin: 30px auto 0px;font-size: 15px;background-color: black;color: #fff;border-radius: 5px;border: none;">Sign&nbsp;in</button>
            <div class="curPoi" style="text-align: right;padding: 10px 50px 0px 0px;color: #4386F5;">Forget&nbsp;Password?</div>
            <div class="curDefault" style="padding: 40px 0px 20px 50px;font-family: PingFangSC-Regular;font-size: 15px;color: #B3B3B3;text-align: left;">Or</div>
            <button class="curPoi" style="background-color: #4386F5;border: none;border-radius: 4px;font-size: 15px;color: #FFFFFF;line-height:44px;width: 300px;"><img src="../assets/img/google.png" alt="" width="24px;" height="24px" style="vertical-align:middle;" />Sign&nbsp;in&nbsp;with&nbsp;Google</button>
            <button class="curPoi" style="border: none;padding: 12px;margin-left: 10px;border-radius: 4px;"><img src="../assets/img/facebook_login.png" alt="" width="20px" height="20px" /></button>
          </div>
          <div v-if="showRegister">
            123
          </div>
        </el-col>
      </el-row>
    </el-dialog>

    <el-dialog :visible.sync="signUpVisible">

      <el-row style="height: 100%;">
        <el-col :span="11" style="height: 100%;">
          <img src="../assets/img/joker.png" alt="" width="100%" height="100%" />
        </el-col>
        <el-col :span="13">
          <div style="text-align: right; padding: 20px 20px 0px 0px;"><img @click="signUpVisible = false"  src="../assets/img/close.png" alt="" width="20px" height="20px" class="curPoi" /></div>
          <div v-if="showLogin">
            <el-row style="padding: 50px 40px;">
              <el-col :span="12" class="curDefault" style="font-size: 22px;font-weight: bold;text-align: left;">Register for CYSN</el-col>
            </el-row>
            <el-input placeholder="Username" v-model="account" style="margin: 0px 50px 20px;width: 80%;" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/account.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Email Address" v-model="account" style="margin: 0px 50px 20px;width: 80%;" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/mail.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Password" v-model="pwd" style="margin: 0px 50px 20px;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Confirm Password" v-model="pwd" style="margin: 0px 50px;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/confirm.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <button class="curPoi" style="line-height: 44px;width: 80%;margin: 30px auto 0px;font-size: 15px;background-color: black;color: #fff;border-radius: 5px;border: none;">Register&nbsp;</button>
            <div class="curDefault" style="padding: 40px 0px 20px 50px;font-family: PingFangSC-Regular;font-size: 15px;color: #B3B3B3;text-align: left;">Or</div>
            <button class="curPoi" style="background-color: #4386F5;border: none;border-radius: 4px;font-size: 15px;color: #FFFFFF;line-height:44px;width: 300px;"><img src="../assets/img/google.png" alt="" width="24px;" height="24px" style="vertical-align:middle;" />Sign&nbsp;in&nbsp;with&nbsp;Google</button>
            <button class="curPoi" style="border: none;padding: 12px;margin-left: 10px;border-radius: 4px;"><img src="../assets/img/facebook_login.png" alt="" width="20px" height="20px" /></button>
          </div>
          <div v-if="showRegister">
            123
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
      location_options: [{
        loc_value: 'Sydney',
        label: 'Sydney'
      }, {
        loc_value: 'Melbourne',
        label: 'Melbourne'
      }],
      date_options: [{
        value: '19072020',
        label: 'July 19'
      }, {
        value: '20072020',
        label: 'July 20'
      }],
      time_options: [{
        time_value: '1300',
        label: '13:00'
      }, {
        time_value: '1800',
        label: '18:00'
      }],
      loc_value: '',
      value: '',
      time_value: ''
    }
  },



  mounted: function () {},
  methods: {
    // checkType: function() {
    //   this.showLogin = false;
    //   this.showRegister = true;
    // }
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
